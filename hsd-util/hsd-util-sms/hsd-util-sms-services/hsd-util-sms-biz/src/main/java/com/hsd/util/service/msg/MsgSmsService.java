package com.hsd.util.service.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.util.api.msg.IMsgSmsService;
import com.hsd.util.dao.msg.IMsgSmsDao;
import com.hsd.util.dto.msg.MsgSmsDto;
import com.hsd.util.entity.msg.MsgSms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class MsgSmsService extends BaseService implements IMsgSmsService {
    @Autowired
    private IMsgSmsDao msgSmsDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody MsgSmsDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                MsgSms entity = copyTo(dto, MsgSms.class);
                //判断数据是否存在
                if (msgSmsDao.isDataYN(entity) != 0) {
                    //数据存在
                    msgSmsDao.update(entity);
                } else {
                    //新增
                     msgSmsDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody MsgSmsDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                MsgSms entity = copyTo(dto, MsgSms.class);
                if(msgSmsDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody MsgSmsDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               MsgSms entity = copyTo(dto, MsgSms.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = msgSmsDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), MsgSmsDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<MsgSmsDto> findDataIsList(@RequestBody MsgSmsDto dto) throws Exception {
            List<MsgSmsDto>  results = null;
            try {
                MsgSms entity = copyTo(dto, MsgSms.class);
                 results = copyTo(msgSmsDao.findDataIsList(entity), MsgSmsDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public MsgSmsDto findDataById(@RequestBody MsgSmsDto dto) throws Exception {
            MsgSmsDto result = null;
            try {
                MsgSms entity = copyTo(dto, MsgSms.class);
                result = copyTo(msgSmsDao.selectByPrimaryKey(entity),MsgSmsDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}