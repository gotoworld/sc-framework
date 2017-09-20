package com.hsd.account.actor.service.actor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.actor.IBusinessTypeService;
import com.hsd.account.actor.dao.actor.IBusinessTypeDao;
import com.hsd.account.actor.dto.actor.BusinessTypeDto;
import com.hsd.account.actor.entity.actor.BusinessType;
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
public class BusinessTypeService extends BaseService implements IBusinessTypeService {
    @Autowired
    private IBusinessTypeDao businessTypeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody BusinessTypeDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                BusinessType entity = copyTo(dto, BusinessType.class);
                //判断数据是否存在
                if (businessTypeDao.isDataYN(entity) != 0) {
                    //数据存在
                    businessTypeDao.update(entity);
                } else {
                    //新增
                     businessTypeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody BusinessTypeDto dto) throws Exception {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                BusinessType entity = copyTo(dto, BusinessType.class);
                if(businessTypeDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public String deleteDataById(@RequestBody BusinessTypeDto dto) throws Exception {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                BusinessType entity = copyTo(dto, BusinessType.class);
                if(businessTypeDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody BusinessTypeDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               BusinessType entity = copyTo(dto, BusinessType.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = businessTypeDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), BusinessTypeDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<BusinessTypeDto> findDataIsList(@RequestBody BusinessTypeDto dto) throws Exception {
            List<BusinessTypeDto>  results = null;
            try {
                BusinessType entity = copyTo(dto, BusinessType.class);
                 results = copyTo(businessTypeDao.findDataIsList(entity), BusinessTypeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public BusinessTypeDto findDataById(@RequestBody BusinessTypeDto dto) throws Exception {
            BusinessTypeDto result = null;
            try {
                BusinessType entity = copyTo(dto, BusinessType.class);
                result = copyTo(businessTypeDao.selectByPrimaryKey(entity),BusinessTypeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}