package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dao.IAccountDao;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.entity.Account;
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
public class AccountService extends BaseService implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                Account entity = copyTo(dto, Account.class);
                //判断数据是否存在
                if (accountDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountDao.update(entity);
                } else {
                    //新增
                     accountDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               Account entity = copyTo(dto, Account.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountDto> findDataIsList(@RequestBody AccountDto dto) {
            List<AccountDto>  results = null;
            try {
                Account entity = copyTo(dto, Account.class);
                 results = copyTo(accountDao.findDataIsList(entity), AccountDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountDto findDataById(@RequestBody AccountDto dto) {
            AccountDto result = null;
            try {
                Account entity = copyTo(dto, Account.class);
                result = copyTo(accountDao.selectByPrimaryKey(entity),AccountDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public AccountDto findDataByUserId(@RequestBody AccountDto dto) {
            AccountDto result = null;
            try {
                Account entity = copyTo(dto, Account.class);
                result = copyTo(accountDao.selectByUserId(entity),AccountDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

    @Override
    public Response updateState(Long userId, Long accountId, Integer state) throws Exception {
        Response result = new Response(0,"success");
        Account account = new Account(){{setId(accountId);}};
        Account  userAccount = (Account)accountDao.selectByPrimaryKey(account);
        if(userAccount == null){
            result = Response.error("账户不存在!");
            return result;
        }
        if(userId != userAccount.getAppUserId()){
            result = Response.error("用户账户不存在!");
            return result;
        }
        userAccount.setState(state);
        accountDao.update(userAccount);
        return result;
    }
}