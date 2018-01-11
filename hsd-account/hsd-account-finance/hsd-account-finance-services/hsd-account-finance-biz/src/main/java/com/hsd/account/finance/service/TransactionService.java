package com.hsd.account.finance.service;

import com.hsd.account.finance.api.ITransactionService;
import com.hsd.account.finance.dao.IAccountBindThirdpartyDao;
import com.hsd.account.finance.dao.IAccountDao;
import com.hsd.account.finance.dao.IAccountLogDao;
import com.hsd.account.finance.dao.IAccountLogFreezeDao;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.dto.AccountLogDto;
import com.hsd.account.finance.dto.DeductMoneyDto;
import com.hsd.account.finance.entity.Account;
import com.hsd.account.finance.entity.AccountBindThirdparty;
import com.hsd.account.finance.entity.AccountLog;
import com.hsd.account.finance.entity.AccountLogFreeze;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.cache.redis.RedisLock;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.lock.Lock;
import com.hsd.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Response deduct(@RequestBody DeductMoneyDto dto) throws Exception {
        Response result = new Response(0,"success");
        Long accountId = dto.getAccountId();
        Long userId = dto.getAppUserId();
        String cardNo = dto.getCardNo();
        BigDecimal amount = dto.getDeductMoney();
        if(userId == null || accountId == null || StringUtils.isEmpty(cardNo) || StringUtils.isEmpty(cardNo) || amount == null || amount.compareTo(BigDecimal.ZERO)< 0){
            result = Response.error("输入信息缺失!");
            return result;
        }

        Lock lock = new RedisLock("lock:account-deduct:"+accountId, 60 * 1000);
        try{
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
        }catch (Exception e){
            log.error("账户[扣款]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        finally {
            lock.unlock();//释放锁
        }
        return result;
    }


    @Override
    public Response amountFrozen(@RequestBody AccountDto dto) throws Exception {
        Response result = new Response(0,"success");
        Long accountId = dto.getId();
        Long userId = dto.getAppUserId();
        BigDecimal amount = dto.getFreezeMoney();
        Lock lock = new RedisLock("lock:account-amountFrozen:"+accountId, 60 * 1000);
        try{
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
        }catch (Exception e){
            log.error("账户资金[冻结]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        finally {
            lock.unlock();//释放锁
        }
        return result;
    }


    @Override
    public Response amountUnfreeze(@RequestBody AccountDto dto) throws Exception {
        Response result = new Response(0,"success");
        Long accountId = dto.getId();
        Long userId = dto.getAppUserId();
        BigDecimal amount = dto.getFreezeMoney();
        Lock lock = new RedisLock("lock:account-amountUnfreeze:"+accountId, 60 * 1000);
        try{
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
        }catch (Exception e){
            log.error("账户资金[解冻]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        finally {
            lock.unlock();//释放锁
        }
        return result;
    }
}
