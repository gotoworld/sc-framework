package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.finance.api.IAccountSubLoanService;
import com.hsd.account.finance.dao.IAccountDao;
import com.hsd.account.finance.dao.IAccountSubLoanDao;
import com.hsd.account.finance.dao.IAccountTypeDao;
import com.hsd.account.finance.dto.AccountSubLoanDto;
import com.hsd.account.finance.entity.Account;
import com.hsd.account.finance.entity.AccountSubLoan;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@FeignService
@Slf4j
public class AccountSubLoanService extends FinanceBaseService implements IAccountSubLoanService {
    @Autowired
    private IAccountSubLoanDao accountSubLoanDao;

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IIdentityService identityService;

    @Autowired
    private IAccountTypeDao accountTypeDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountSubLoanDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                //判断数据是否存在
                if (accountSubLoanDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountSubLoanDao.update(entity);
                } else {
                    //新增
                     accountSubLoanDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountSubLoanDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountSubLoanDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountSubLoanDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountSubLoanDto> findDataIsList(@RequestBody AccountSubLoanDto dto) {
            List<AccountSubLoanDto>  results = null;
            try {
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                 results = copyTo(accountSubLoanDao.findDataIsList(entity), AccountSubLoanDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountSubLoanDto findDataById(@RequestBody AccountSubLoanDto dto) {
            AccountSubLoanDto result = null;
            try {
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                result = copyTo(accountSubLoanDao.selectByPrimaryKey(entity),AccountSubLoanDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public AccountSubLoanDto findDataByUserId(AccountSubLoanDto dto) throws Exception {
            AccountSubLoanDto result = null;
            try {
                AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
                result = copyTo(accountSubLoanDao.selectByUserId(entity),AccountSubLoanDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response open(@RequestBody AccountSubLoanDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            AccountSubLoan entity = copyTo(dto, AccountSubLoan.class);
            Long type = entity.getAccountType();
            Long userId = entity.getAppUserId();
            if(type == null || userId == null){
                throw new RuntimeException("参数异常!");
            }
            AccountType accountType = new AccountType(){{setId(type);}};
            AccountType accountTypeS = (AccountType)accountTypeDao.selectByPrimaryKey(accountType);
            if(accountTypeS == null || 0 != accountTypeS.getDelFlag()){
                result = Response.error("要开通的账户类型不存在!");
                return result;
            }
            entity.setAliasName(accountTypeS.getName());


            //获取实名信息
            IdentityDto identityDto = new IdentityDto();
            identityDto.setUserId(userId);
            IdentityDto userIdentity =  identityService.findDataById(identityDto);
            if(userIdentity == null){
                result = Response.error("未找到用户实名认证信息,请先实名认证!");
                return result;
            }

            AccountSubLoan accountSubLoan = accountSubLoanDao.selectByUserId(entity);
            if(accountSubLoan != null){
                result = Response.error("网贷账户已开通,每个用户只能开通一个网贷账户!");
                return result;
            }

            Long id = idGenerator.nextId();
            entity.setId(id);

            //设置资金账户
            Long accountId = idGenerator.nextId();
            entity.setAccountId(accountId);

            entity.setState(0);
            entity.setDateOpen(new Date());
            entity.setAccountTemplateId(1l);
            entity.setCurrency(0);
            entity.setTotalMoney(BigDecimal.ZERO);
            entity.setAvailableMoney(BigDecimal.ZERO);
            entity.setFreezeMoney(BigDecimal.ZERO);
            entity.setDueIn(BigDecimal.ZERO);
            entity.setStayStill(BigDecimal.ZERO);
            entity.setStayInterest(BigDecimal.ZERO);
            entity.setMakeInterest(BigDecimal.ZERO);
            entity.setMakeReward(BigDecimal.ZERO);
            entity.setOverdue(BigDecimal.ZERO);
            entity.setAccountTemplateId(1l);
            //新增
            accountSubLoanDao.insert(entity);

            //默认开通资金账户
            Account account = copyTo(entity, Account.class);
            account.setId(accountId);
            //需要默认开得资金账户类型
            account.setAccountType(type); //待定
            account.setAliasName(accountTypeS.getName());
            account.setCurrency(0);
            account.setState(0);
            account.setTotalMoney(BigDecimal.ZERO);
            account.setAvailableMoney(BigDecimal.ZERO);
            account.setFreezeMoney(BigDecimal.ZERO);
            account.setInFloatMoney(BigDecimal.ZERO);
            account.setAccountTemplateId(1l);
            //新增
            accountDao.insert(account);
            result.data=entity.getId();
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
}