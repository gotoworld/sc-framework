package com.hsd.account.finance.service;

import com.hsd.account.finance.api.ITransactionService;
import com.hsd.account.finance.dao.IAccountBindThirdpartyDao;
import com.hsd.account.finance.dao.IAccountDao;
import com.hsd.account.finance.dao.IAccountLogDao;
import com.hsd.account.finance.dao.IAccountLogFreezeDao;
import com.hsd.account.finance.dto.AccountLogDto;
import com.hsd.account.finance.entity.Account;
import com.hsd.account.finance.entity.AccountBindThirdparty;
import com.hsd.account.finance.entity.AccountLog;
import com.hsd.account.finance.entity.AccountLogFreeze;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hsd7 on 2018/1/4.
 */
@FeignService
@Slf4j
public class TransactionService extends BaseService implements ITransactionService {

    @Autowired
    private IAccountBindThirdpartyDao accountBindThirdpartyDao;

    @Autowired
    private IAccountLogDao accountLogDao;

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IAccountLogFreezeDao accountLogFreezeDao;

    @Override
    public Response deduct(Long userId, Long accountId, String cardNo, BigDecimal amount) throws Exception {
        Response result = new Response(0,"success");
        AccountBindThirdparty accountBindThirdparty = new AccountBindThirdparty(){{
            setAppUserId(userId);
            setThirdpartyAccount(cardNo);
            setThirdpartyType(0);
            setAccountId(accountId);
        }};
        List<AccountBindThirdparty> accountBindThirdpartys = accountBindThirdpartyDao.selectBindThirdparty(accountBindThirdparty);
        if(CollectionUtils.isEmpty(accountBindThirdpartys)){
            result = Response.error("账户未绑定!");
            return result;
        }

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

        if(userAccount.getState() == null){
            result = Response.error("账户状态不正常!");
            return result;
        }

        if(userAccount.getState().intValue() != 0){
            result = Response.error("此账户状态不能进行扣款操作！!");
            return result;
        }

        Long id = idGenerator.nextId();
        AccountLog accountLog = new AccountLog();
        accountLog.setId(id);
        accountLog.setSerialNumber("DT"+id);
        accountLog.setAccountId(accountId);
        accountLog.setAmount(amount);
        accountLog.setCurrency(0);
        accountLog.setOpAppUserId(userId);
        accountLog.setState(2);
        accountLogDao.insert(accountLog);



        try{
            //扣款
            userAccount.setTotalMoney(userAccount.getTotalMoney().add(amount));
            userAccount.setAvailableMoney(userAccount.getAvailableMoney().add(amount));
            accountDao.update(userAccount);

            accountLog.setState(0);

        }catch (Exception e){
            log.error("信息[详情]查询异常!", e);
            accountLog.setState(3);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        finally {
            accountLogDao.update(accountLog);
        }
        return result;
    }


    @Override
    public Response frozen(Long userId, Long accountId, BigDecimal amount) throws Exception {
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

        if(userAccount.getState() == null){
            result = Response.error("账户状态不正常!");
            return result;
        }

        if(userAccount.getState().intValue() == 1){
            result = Response.error("账户被冻结!");
            return result;
        }

        if(userAccount.getState().intValue() == 2){
            result = Response.error("账户被注销!");
            return result;
        }

        if(userAccount.getAvailableMoney() == null || userAccount.getAvailableMoney().compareTo(amount) < 0){
            result = Response.error("可用余额不足!");
            return result;
        }
        userAccount.setAvailableMoney(userAccount.getAvailableMoney().divide(amount));
        userAccount.setFreezeMoney(userAccount.getFreezeMoney().add(amount));
        accountDao.update(userAccount);


        AccountLogFreeze accountLogFreeze = new AccountLogFreeze();
        accountLogFreeze.setId(idGenerator.nextId());
        accountLogFreeze.setAppUserId(userId);
        accountLogFreeze.setAccountId(accountId);
        accountLogFreeze.setAmount(amount);
        accountLogFreezeDao.insert(accountLogFreeze);
        return result;
    }


    @Override
    public Response unfreeze(Long userId, Long accountId, BigDecimal amount) throws Exception {
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

        if(userAccount.getState() == null){
            result = Response.error("账户状态不正常!");
            return result;
        }

        if(userAccount.getState().intValue() == 1){
            result = Response.error("账户被冻结!");
            return result;
        }

        if(userAccount.getState().intValue() == 2){
            result = Response.error("账户被注销!");
            return result;
        }

        if(userAccount.getFreezeMoney() == null || userAccount.getFreezeMoney().compareTo(amount) < 0){
            result = Response.error("冻结金额不正确!");
            return result;
        }
        userAccount.setAvailableMoney(userAccount.getAvailableMoney().add(amount));
        userAccount.setFreezeMoney(userAccount.getFreezeMoney().divide(amount));
        accountDao.update(userAccount);
        return result;
    }
}
