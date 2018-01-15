package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountTemplateService;
import com.hsd.account.finance.dao.IAccountTemplateAttributeDao;
import com.hsd.account.finance.dao.IAccountTemplateDao;
import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.account.finance.dto.AccountTemplateDto;
import com.hsd.account.finance.entity.AccountTemplate;
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
public class AccountTemplateService extends FinanceBaseService implements IAccountTemplateService {
    @Autowired
    private IAccountTemplateDao accountTemplateDao;
    @Autowired
    private IAccountTemplateAttributeDao accountTemplateAttributeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountTemplateDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountTemplate entity = copyTo(dto, AccountTemplate.class);
                //判断数据是否存在
                if (accountTemplateDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountTemplateDao.update(entity);
                } else {
                    //新增
                     accountTemplateDao.insert(entity);
                     result.data=entity.getId();
                }
                //删除已有
                AccountTemplateAttribute tempAttr=new AccountTemplateAttribute();
                tempAttr.setTemplateId(entity.getId());
                accountTemplateDao.deleteByTemplateId(tempAttr);

                if(dto.getAttributes()!=null){
                    for(AccountTemplateAttributeDto accountTemplateAttributeDto:dto.getAttributes()) {
                        accountTemplateAttributeDto.setCreateId(entity.getCreateId());
                        accountTemplateAttributeDto.setTemplateId(entity.getId());
                        accountTemplateAttributeDto.setType(entity.getType());
                        accountTemplateAttributeDao.insert(copyTo(accountTemplateAttributeDto, AccountTemplateAttribute.class));
                    }
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody AccountTemplateDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountTemplate entity = copyTo(dto, AccountTemplate.class);
                if(accountTemplateDao.deleteByPrimaryKey(entity)==0){
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
        public String deleteDataById(@RequestBody AccountTemplateDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountTemplate entity = copyTo(dto, AccountTemplate.class);
                if(accountTemplateDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody AccountTemplateDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountTemplate entity = copyTo(dto, AccountTemplate.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountTemplateDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountTemplateDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountTemplateDto> findDataIsList(@RequestBody AccountTemplateDto dto) {
            List<AccountTemplateDto>  results = null;
            try {
                AccountTemplate entity = copyTo(dto, AccountTemplate.class);
                 results = copyTo(accountTemplateDao.findDataIsList(entity), AccountTemplateDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountTemplateDto findDataById(@RequestBody AccountTemplateDto dto) {
            AccountTemplateDto result = null;
            try {
                AccountTemplate entity = copyTo(dto, AccountTemplate.class);
                result = copyTo(accountTemplateDao.selectByPrimaryKey(entity),AccountTemplateDto.class);
                if(result!=null){
                    AccountTemplateAttribute attribute=new AccountTemplateAttribute();
                    attribute.setTemplateId(result.getId());
                    result.setAttributes(copyTo(accountTemplateAttributeDao.findDataIsList(attribute),AccountTemplateAttributeDto.class));
                }
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String recoveryDataById(@RequestBody AccountTemplateDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountTemplate entity = copyTo(dto, AccountTemplate.class);
                if(accountTemplateDao.recoveryDataById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("数据恢复异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

}