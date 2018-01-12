package com.hsd.msg.service.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.cache.util.RedisHelper;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.msg.api.msg.IMsgVerifyService;
import com.hsd.msg.dao.msg.IMsgVerifyDao;
import com.hsd.msg.dto.msg.MsgVerifyDto;
import com.hsd.msg.entity.msg.MsgVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class MsgVerifyService extends BaseService implements IMsgVerifyService {
    @Autowired
    private IMsgVerifyDao msgVerifyDao;
    public static final String prefix = "verify:msg:";
    @Autowired
    private RedisHelper redisHelper;

    @Override
    public PageInfo findDataIsPage(@RequestBody MsgVerifyDto dto) {
        PageInfo pageInfo = null;
        try {
            if (dto == null) throw new RuntimeException("参数有误!");
            MsgVerify entity = copyTo(dto, MsgVerify.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = msgVerifyDao.findDataIsPage(entity);
            pageInfo = new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), MsgVerifyDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public MsgVerifyDto findDataById(@RequestBody MsgVerifyDto dto) {
        MsgVerifyDto result = null;
        try {
            MsgVerify entity = copyTo(dto, MsgVerify.class);
            result = copyTo(msgVerifyDao.selectByPrimaryKey(entity), MsgVerifyDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public boolean verifyImgCode(@RequestBody MsgVerifyDto dto) {
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getImgCaptchaId()) || ValidatorUtil.isEmpty(dto.getImgCaptchaCode()))
                throw new RuntimeException("认证码不能为空!");
            if (dto.getImgCaptchaCode().equalsIgnoreCase("" + redisHelper.get("verify:img:"+dto.getImgCaptchaId()))){
                if(dto.isImgCaptchaDel()){
                    redisHelper.del("verify:img:"+dto.getImgCaptchaId());
                }
                return true;
            }else{
                throw new RuntimeException("认证码输入错误!");
            }
        } catch (Exception e) {
            log.error("图片认证码校验-异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
    }

    @Override
    public Response pushVerifyCode(@RequestBody MsgVerifyDto dto) {
        Response result = new Response();
        try {
            //检查图片验证码是否正确//限制恶意刷短信
            dto.setImgCaptchaDel(true);
            verifyImgCode(dto);
            MsgVerify msgVerify = copyTo(dto, MsgVerify.class);
            msgVerify.setDataExpire(60*10);//有效时长(秒)
            //msgVerify.setIsUsed(0);//是否使用0否1是

            //跨域请求限制: 进一步限制恶意刷短信
            //检查1分钟内 是否有未使用的短讯 //防止短信轰炸
            if(msgVerifyDao.isNotUsedYN(msgVerify)>0) throw new RuntimeException("请一分钟后重试!");
            //IP次数限制: 防止恶意刷手机验证码短信
            if(msgVerifyDao.isIpExceedYN(msgVerify)>100) throw new RuntimeException("已超过当日最大信息数!");

            msgVerify.setVerifyCode("" + ((int) (Math.random() * 9000) + 1000));//验证码
            msgVerifyDao.insert(msgVerify);
        } catch (Exception e) {
            log.error("短讯验证码推送信息入库,异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    public Response checkVerifyCode(@RequestBody MsgVerifyDto dto) {
        Response result = new Response();
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getSmsAddress())) throw new RuntimeException("参数有误!");
            MsgVerify msgVerify = copyTo(dto, MsgVerify.class);
//            使用次数限制: 1次 || 时效限制: [5-10min]
            if(msgVerifyDao.useVerifyCode(msgVerify)==0){
                throw new RuntimeException("校验失败,验证码错误或已过期!");
            }
        } catch (Exception e) {
            log.error("验证码校验异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
}