package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountTypeService;
import com.hsd.account.finance.dao.IAccountTypeDao;
import com.hsd.account.finance.dto.AccountTypeDto;
import com.hsd.account.finance.entity.AccountType;
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
public class AccountTypeService extends FinanceBaseService implements IAccountTypeService {
    @Autowired
    private IAccountTypeDao accountTypeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountTypeDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountType entity = copyTo(dto, AccountType.class);
                //判断数据是否存在
                if (accountTypeDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountTypeDao.update(entity);
                } else {
                    //新增
                     accountTypeDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody AccountTypeDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountType entity = copyTo(dto, AccountType.class);
                if(accountTypeDao.deleteByPrimaryKey(entity)==0){
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
        public String deleteDataById(@RequestBody AccountTypeDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountType entity = copyTo(dto, AccountType.class);
                if(accountTypeDao.deleteById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("逻辑删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public PageInfo findDataIsPage(@RequestBody AccountTypeDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountType entity = copyTo(dto, AccountType.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountTypeDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountTypeDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountTypeDto> findDataIsList(@RequestBody AccountTypeDto dto) {
            List<AccountTypeDto>  results = null;
            try {
                AccountType entity = copyTo(dto, AccountType.class);
                 results = copyTo(accountTypeDao.findDataIsList(entity), AccountTypeDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountTypeDto findDataById(@RequestBody AccountTypeDto dto) {
            AccountTypeDto result = null;
            try {
                AccountType entity = copyTo(dto, AccountType.class);
                result = copyTo(accountTypeDao.selectByPrimaryKey(entity),AccountTypeDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String recoveryDataById(@RequestBody AccountTypeDto dto) {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountType entity = copyTo(dto, AccountType.class);
                if(accountTypeDao.recoveryDataById(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("数据恢复异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

}