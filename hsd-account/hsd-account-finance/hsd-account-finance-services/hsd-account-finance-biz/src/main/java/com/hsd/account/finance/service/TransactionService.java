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
public class TransactionService extends FinanceBaseService implements ITransactionService {

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

        Lock lock = new RedisLock(redisTemplate,"lock:account-deduct:"+accountId, 60 * 1000);
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
            userAccount.setTotalMoney(userAccount.getTotalMoney().divide(amount));
            userAccount.setAvailableMoney(userAccount.getAvailableMoney().divide(amount));
            accountDao.update(userAccount);
            Long id = idGenerator.nextId();
            AccountLog accountLog = new AccountLog();
            accountLog.setId(id);
            accountLog.setSerialNumber("DT"+id);
            accountLog.setAccountId(accountId);
            accountLog.setAmount(amount);
            accountLog.setCurrency(0);
            accountLog.setOpAppUserId(userId);
            accountLog.setState(0);
            accountLogDao.insert(accountLog);
        }catch (Exception e){
            log.error("账户[扣款]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        finally {
            lock.unlock();//释放锁
        }
        return result;
    }
}
