package com.hsd.account.finance.service;

import com.hsd.account.finance.api.IAccountTemplateAttributeService;
import com.hsd.account.finance.dao.IAccountTemplateAttributeDao;
import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.account.finance.entity.AccountTemplateAttribute;
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
public class AccountTemplateAttributeService extends BaseService implements IAccountTemplateAttributeService {
    @Autowired
    private IAccountTemplateAttributeDao accountTemplateAttributeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountTemplateAttributeDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountTemplateAttribute entity = copyTo(dto, AccountTemplateAttribute.class);
                //判断数据是否存在
                if (accountTemplateAttributeDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountTemplateAttributeDao.update(entity);
                } else {
                    //新增
                     accountTemplateAttributeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody AccountTemplateAttributeDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountTemplateAttribute entity = copyTo(dto, AccountTemplateAttribute.class);
                if(accountTemplateAttributeDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public List<AccountTemplateAttributeDto> findDataIsList(@RequestBody AccountTemplateAttributeDto dto) {
            List<AccountTemplateAttributeDto>  results = null;
            try {
                AccountTemplateAttribute entity = copyTo(dto, AccountTemplateAttribute.class);
                 results = copyTo(accountTemplateAttributeDao.findDataIsList(entity), AccountTemplateAttributeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountTemplateAttributeDto findDataById(@RequestBody AccountTemplateAttributeDto dto) {
            AccountTemplateAttributeDto result = null;
            try {
                AccountTemplateAttribute entity = copyTo(dto, AccountTemplateAttribute.class);
                result = copyTo(accountTemplateAttributeDao.selectByPrimaryKey(entity),AccountTemplateAttributeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}