package com.hsd.util.service.msg;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.util.api.msg.IMsgEmailService;
import com.hsd.util.dao.msg.IMsgEmailDao;
import com.hsd.util.dto.msg.MsgEmailDto;
import com.hsd.util.entity.msg.MsgEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class MsgEmailService extends BaseService implements IMsgEmailService {
    @Autowired
    private IMsgEmailDao msgEmailDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody MsgEmailDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                MsgEmail entity = copyTo(dto, MsgEmail.class);
                //判断数据是否存在
                if (msgEmailDao.isDataYN(entity) != 0) {
                    //数据存在
                    msgEmailDao.update(entity);
                } else {
                    //新增
                     msgEmailDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody MsgEmailDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                MsgEmail entity = copyTo(dto, MsgEmail.class);
                if(msgEmailDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody MsgEmailDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               MsgEmail entity = copyTo(dto, MsgEmail.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = msgEmailDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), MsgEmailDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<MsgEmailDto> findDataIsList(@RequestBody MsgEmailDto dto) throws Exception {
            List<MsgEmailDto>  results = null;
            try {
                MsgEmail entity = copyTo(dto, MsgEmail.class);
                 results = copyTo(msgEmailDao.findDataIsList(entity), MsgEmailDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public MsgEmailDto findDataById(@RequestBody MsgEmailDto dto) throws Exception {
            MsgEmailDto result = null;
            try {
                MsgEmail entity = copyTo(dto, MsgEmail.class);
                result = copyTo(msgEmailDao.selectByPrimaryKey(entity),MsgEmailDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}