package com.hsd.util.service.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.util.api.msg.IMsgVerifyService;
import com.hsd.util.dao.msg.IMsgVerifyDao;
import com.hsd.util.dto.msg.MsgVerifyDto;
import com.hsd.util.entity.msg.MsgVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class MsgVerifyService extends BaseService implements IMsgVerifyService {
    @Autowired
    private IMsgVerifyDao msgVerifyDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody MsgVerifyDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                MsgVerify entity = copyTo(dto, MsgVerify.class);
                //判断数据是否存在
                if (msgVerifyDao.isDataYN(entity) != 0) {
                    //数据存在
                    msgVerifyDao.update(entity);
                } else {
                    //新增
                     msgVerifyDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody MsgVerifyDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                MsgVerify entity = copyTo(dto, MsgVerify.class);
                if(msgVerifyDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody MsgVerifyDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               MsgVerify entity = copyTo(dto, MsgVerify.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = msgVerifyDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), MsgVerifyDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<MsgVerifyDto> findDataIsList(@RequestBody MsgVerifyDto dto) throws Exception {
            List<MsgVerifyDto>  results = null;
            try {
                MsgVerify entity = copyTo(dto, MsgVerify.class);
                 results = copyTo(msgVerifyDao.findDataIsList(entity), MsgVerifyDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public MsgVerifyDto findDataById(@RequestBody MsgVerifyDto dto) throws Exception {
            MsgVerifyDto result = null;
            try {
                MsgVerify entity = copyTo(dto, MsgVerify.class);
                result = copyTo(msgVerifyDao.selectByPrimaryKey(entity),MsgVerifyDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}