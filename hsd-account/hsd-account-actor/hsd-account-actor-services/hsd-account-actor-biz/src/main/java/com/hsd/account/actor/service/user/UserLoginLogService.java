package com.hsd.account.actor.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.user.IUserLoginLogService;
import com.hsd.account.actor.dao.user.IUserLoginLogDao;
import com.hsd.account.actor.dto.user.UserLoginLogDto;
import com.hsd.account.actor.entity.user.UserLoginLog;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class UserLoginLogService extends BaseService implements IUserLoginLogService {
    @Autowired
    private IUserLoginLogDao userLoginLogDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody UserLoginLogDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            UserLoginLog entity = copyTo(dto, UserLoginLog.class);
            //判断数据是否存在
            if (userLoginLogDao.isDataYN(entity) != 0) {
                //数据存在

            } else {
                //新增
                userLoginLogDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteData(@RequestBody UserLoginLogDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            UserLoginLog entity = copyTo(dto, UserLoginLog.class);
            if(userLoginLogDao.deleteByPrimaryKey(entity)==0){
                throw new RuntimeException("数据不存在!");
            }
        } catch (Exception e) {
            log.error("物理删除异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


    @Override
    public PageInfo findDataIsPage(@RequestBody UserLoginLogDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            UserLoginLog entity = copyTo(dto, UserLoginLog.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = userLoginLogDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), UserLoginLogDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }


    @Override
    public UserLoginLogDto findDataById(@RequestBody UserLoginLogDto dto) throws Exception {
        UserLoginLogDto result = null;
        try {
            UserLoginLog entity = copyTo(dto, UserLoginLog.class);
            result = copyTo(userLoginLogDao.selectByPrimaryKey(entity),UserLoginLogDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


}