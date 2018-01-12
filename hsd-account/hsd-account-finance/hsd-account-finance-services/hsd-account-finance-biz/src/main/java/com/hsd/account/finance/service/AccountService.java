package com.hsd.account.finance.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogOperationalService;
import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dao.*;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.dto.AccountLogDto;
import com.hsd.account.finance.dto.op.*;
import com.hsd.account.finance.entity.*;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.cache.redis.RedisLock;
import com.hsd.framework.config.AppConfig;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.lock.Lock;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.ArithUtil;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@FeignService
@Slf4j
public class AccountService extends BaseService implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private IAccountSubGoldDao accountSubGoldDao;
    @Autowired
    private IAccountSubLoanDao accountSubLoanDao;
    @Autowired
    private IAccountLogFreezeDao logFreezeDao;
    @Autowired
    private IAccountLogOperationalDao logOperationalDao;
    @Autowired
    private IAccountLogRechargeDao logRechargeDao;
    @Autowired
    private IAccountLogWithdrawalDao logWithdrawalDao;
    @Autowired
    private IAccountLogDao logAccountDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody AccountDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Account entity = copyTo(dto, Account.class);
            //判断数据是否存在
            if (accountDao.isDataYN(entity) != 0) {
                //数据存在
                accountDao.update(entity);
            } else {
                //新增
                accountDao.insert(entity);
                result.data = entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }


    @Override
    public PageInfo findDataIsPage(@RequestBody AccountDto dto) {
        PageInfo pageInfo = null;
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Account entity = copyTo(dto, Account.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = accountDao.findDataIsPage(entity);
            pageInfo = new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), AccountDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return pageInfo;
    }

    @Override
    public List<AccountDto> findDataIsList(@RequestBody AccountDto dto) {
        List<AccountDto> results = null;
        try {
            Account entity = copyTo(dto, Account.class);
            results = copyTo(accountDao.findDataIsList(entity), AccountDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return results;
    }

    @Override
    public AccountDto findDataById(@RequestBody AccountDto dto) {
        AccountDto result = null;
        try {
            Account entity = copyTo(dto, Account.class);
            result = copyTo(accountDao.selectByPrimaryKey(entity), AccountDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public List<AccountDto> findAccount(@RequestBody AccountDto dto) {
        List<AccountDto> result = null;
        try {
            Account entity = copyTo(dto, Account.class);
            result = copyTo(accountDao.selectAccount(entity), AccountDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public Response updateState(@RequestBody AccountDto dto) throws Exception {
        Response result = new Response(0,"success");
        Long accountId = dto.getId();
        Long userId = dto.getAppUserId();
        Integer state = dto.getState();
        Account account = new Account() {{
            setId(accountId);
        }};
        Account userAccount = (Account) accountDao.selectByPrimaryKey(account);
        if (userAccount == null) {
            result = Response.error("账户不存在!");
            return result;
        }
        if (userId != userAccount.getAppUserId()) {
            result = Response.error("用户账户不存在!");
            return result;
        }
        userAccount.setState(state);
        accountDao.update(userAccount);

        //.记录操作日志
        AccountLogOperational logOperational=copyTo(dto,AccountLogOperational.class);
        logOperational.setId(idGenerator.nextId());
        logOperational.setData(JSON.toJSONString(dto));
        logOperational.setType(3);
        logOperational.setMemo("状态变更"+dto.getState());
        logOperational.setUserName(dto.getStaffName());
        logOperational.setCreateId(dto.getCreateId());
        logOperational.setCreateIp(dto.getIp());
        logOperationalDao.insert(logOperational);

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response reverse(@RequestBody AccountReverseDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            //1.判断操作类型 冲正/抵扣
            //2.记录操作日志
            Account entity = copyTo(dto, Account.class);
            accountDao.reverse(entity);
        } catch (Exception e) {
            log.error("账户[冲正/抵扣]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response freeze(@RequestBody AccountFreezeDto dto) throws Exception {
        Response result = new Response(0,"success");
        //加分布式锁 同一个账户相同时间
        Lock lock = new RedisLock("lock:account-freeze:"+dto.getId(), 60 * 1000);
        try {
            //.判断业务账户种类0资金账户1黄金账户2网贷账户
            if("#0#1#2#".indexOf("#"+dto.getBizAccountType()+"#")==-1){
                return Response.error("业务账户未知");
            }
            //.判断操作类型 冻结/解冻
            if("#0#1#".indexOf("#"+dto.getActionType()+"#")==-1){
                return Response.error("操作类型未知");
            }
            if(lock.tryLock(50*1000)) {//加锁块
                //TODO 获取用户资金账户绑定银行卡
                //TODO 调用接口执行转账

                //系统账户快照更新-获取指定账户信息
                switch (dto.getBizAccountType()) {//业务账户
                    case 0: { //0资金账户
                        //获取原始账户信息
                        Account entity = (Account) accountDao.selectByPrimaryKey(new Account() {{
                            setId(dto.getId());
                        }});
                        entity.setFreezeMoney(dto.getAmount());
                        if (dto.getActionType() == 0) {//冻结 原始冻结金额+本次冻结金额
                            if (accountDao.freeze(entity) == 0) return Response.error("可用金额不足");
                        } else {//解冻 原始冻结金额-本次冻结金额
                            if (accountDao.unfreeze(entity) == 0) return Response.error("可解冻金额不足");
                        }
                        break;
                    }
                    case 1: {//1黄金账户
                        //AccountSubGold entity= (AccountSubGold) accountSubGoldDao.selectByPrimaryKey(new AccountSubGold(){{setId(dto.getId());}});
                        //entity.setFreezeMoney(freezeAccount(dto.getActionType(),entity.getFreezeMoney(),dto.getAmount()));
                        //accountSubGoldDao.freeze(entity);
                        return Response.error("黄金账户,暂无[冻结/解冻]功能");
                        // break;
                    }
                    case 2: { //2网贷账户
                        AccountSubLoan entity = (AccountSubLoan) accountSubLoanDao.selectByPrimaryKey(new AccountSubLoan() {{
                            setId(dto.getId());
                        }});
                        entity.setFreezeMoney(dto.getAmount());
                        if (dto.getActionType() == 0) {//冻结 原始冻结金额+本次冻结金额
                            if (accountSubLoanDao.freeze(entity) == 0) return Response.error("可用金额不足");
                        } else {//解冻 原始冻结金额-本次冻结金额
                            if (accountSubLoanDao.unfreeze(entity) == 0) return Response.error("可解冻金额不足");
                        }
                        break;
                    }
                }
                //.记录冻结日志
                AccountLogFreeze logFreezeEntity = copyTo(dto, AccountLogFreeze.class);
                logFreezeEntity.setId(idGenerator.nextId());
                logFreezeDao.insert(logFreezeEntity);

                //.记录操作日志
                AccountLogOperational logOperational = copyTo(dto, AccountLogOperational.class);
                logOperational.setId(idGenerator.nextId());
                logOperational.setData(JSON.toJSONString(dto));
                logOperational.setType(dto.getActionType() == 0 ? 1 : 2);//1冻结,2解冻
                logOperational.setMemo("" + (dto.getActionType() == 0 ? "[冻结]" : "[解冻]") + dto.getAmount());
                logOperational.setUserName(dto.getStaffName());
                logOperational.setCreateId(dto.getCreateId());
                logOperational.setCreateIp(dto.getIp());
                logOperationalDao.insert(logOperational);
            }
        } catch (Exception e) {
            log.error("账户[冻结/解冻]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }finally {
            lock.unlock();//释放锁
        }
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response recharge(@RequestBody AccountRechargeDto dto) throws Exception {
        Response result = new Response(0, "success");
        //加分布式锁 同一个账户相同时间
        Lock lock = new RedisLock("lock:account-recharge:"+dto.getId(), 60 * 1000);
        try {
            //.判断业务账户种类0资金账户1黄金账户2网贷账户
            if("#0#1#2#".indexOf("#"+dto.getBizAccountType()+"#")==-1){
                return Response.error("业务账户未知");
            }
            if(lock.tryLock(50*1000)) {//加锁块
                //获取指定账户信息
                switch (dto.getBizAccountType()) {//业务账户
                    case 0: { //0资金账户
                        return Response.error("资金账户,暂无[充值]功能");
                    }
                    case 1: {//1黄金账户
                        AccountSubGold entity = (AccountSubGold) accountSubGoldDao.selectByPrimaryKey(new AccountSubGold() {{
                            setId(dto.getId());
                        }});
                        if (entity == null) {
                            return Response.error("未找到黄金账户！");
                        }
                        if (entity.getState() != 0) {
                            return Response.error("黄金账户不正常！");
                        }
                        entity.setTotalGold(dto.getAccountMoney());
                        if (accountSubGoldDao.recharge(entity) == 0) return Response.error("未找到黄金账户或账户状态不正常!");
                        break;
                    }
                    case 2: { //2网贷账户
                        AccountSubLoan entity = (AccountSubLoan) accountSubLoanDao.selectByPrimaryKey(new AccountSubLoan() {{
                            setId(dto.getId());
                        }});
                        if (entity == null) {
                            return Response.error("未找到网贷账户！");
                        }
                        if (entity.getState() != 0) {
                            return Response.error("网贷账户不正常！");
                        }
                        entity.setAvailableMoney(dto.getAccountMoney());
                        entity.setTotalMoney(dto.getAccountMoney());
                        if (accountSubLoanDao.recharge(entity) == 0) return Response.error("未找到网贷账户或账户状态不正常!");
                        break;
                    }
                }
                //.记录充值日志
                AccountLogRecharge logRechargeEntity = copyTo(dto, AccountLogRecharge.class);
                logRechargeEntity.setId(idGenerator.nextId());
                logRechargeDao.insert(logRechargeEntity);
            }
        } catch (Exception e) {
            log.error("账户[充值]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }finally {
            lock.unlock();//释放锁
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response withdrawal(AccountWithdrawalDto dto) throws Exception {
        Response result = new Response(0, "success");
        //加分布式锁 同一个账户相同时间
        Lock lock = new RedisLock("lock:account-withdrawal:"+dto.getId(), 60 * 1000);
        try {
            //.判断业务账户种类0资金账户1黄金账户2网贷账户
            if("#0#1#2#".indexOf("#"+dto.getBizAccountType()+"#")==-1){
                return Response.error("业务账户未知");
            }
            if(lock.tryLock(50*1000)) {//加锁块
                //获取指定账户信息
                switch (dto.getBizAccountType()) {//业务账户
                    case 0: { //0资金账户
                        return Response.error("资金账户,暂无[提现]功能");
                    }
                    case 1: {//1黄金账户
                        AccountSubGold entity = (AccountSubGold) accountSubGoldDao.selectByPrimaryKey(new AccountSubGold() {{
                            setId(dto.getId());
                        }});
                        if (entity == null) {
                            return Response.error("未找到黄金账户！");
                        }
                        if (entity.getState() != 0) {
                            return Response.error("黄金账户不正常！");
                        }
                        entity.setTotalGold(dto.getMoney());
                        if (accountSubGoldDao.withdrawal(entity) == 0) return Response.error("未找到黄金账户或账户状态不正常!");
                        break;
                    }
                    case 2: { //2网贷账户
                        AccountSubLoan entity = (AccountSubLoan) accountSubLoanDao.selectByPrimaryKey(new AccountSubLoan() {{
                            setId(dto.getId());
                        }});
                        if (entity == null) {
                            return Response.error("未找到网贷账户！");
                        }
                        if (entity.getState() != 0) {
                            return Response.error("网贷账户不正常！");
                        }
                        entity.setAvailableMoney(dto.getMoney());
                        entity.setTotalMoney(dto.getMoney());
                        if (accountSubLoanDao.withdrawal(entity) == 0) return Response.error("未找到网贷账户或账户状态不正常!");
                        break;
                    }
                }
                //.记录提现日志
                AccountLogWithdrawal logWithdrawalEntity = copyTo(dto, AccountLogWithdrawal.class);
                logWithdrawalEntity.setId(idGenerator.nextId());
                logWithdrawalDao.insert(logWithdrawalEntity);
            }
        } catch (Exception e) {
            log.error("账户[提现]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }finally {
            lock.unlock();//释放锁
        }
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response transfer(AccountTransferDto dto)  throws Exception {
        Response result = new Response(0, "success");
        //.记录提现日志
        AccountLog accountLog=copyTo(dto,AccountLog.class);
        accountLog.setId(idGenerator.nextId());

        //加分布式锁 同一个账户相同时间
        Lock lock = new RedisLock("lock:account-transfer:"+dto.getId(), 60 * 1000);
        try {
            //.判断业务账户种类0资金账户1黄金账户2网贷账户
            if("#0#1#2#".indexOf("#"+dto.getBizAccountType()+"#")==-1){
                return Response.error("业务账户未知");
            }
            accountLog.setState(1);
            //获取指定账户信息
            switch (dto.getBizAccountType()){//业务账户
                case 0: { //0资金账户
                    return Response.error("资金账户,暂无[转账]功能");
                }
                case 1: {//1黄金账户
                    AccountSubGold entityOut= (AccountSubGold) accountSubGoldDao.selectByPrimaryKey(new AccountSubGold(){{setId(dto.getOpAccountId());}});
                    if(entityOut == null){
                        accountLog.setMemo("未找到转出黄金账户！");
                        return Response.error("未找到转出黄金账户！");
                    }
                    if(entityOut.getState() != 0){
                        accountLog.setMemo("转出黄金账户状态不正常！");
                        return Response.error("转出黄金账户状态不正常！");
                    }

                    AccountSubGold entityIn= (AccountSubGold) accountSubGoldDao.selectByPrimaryKey(new AccountSubGold(){{setId(dto.getOtherAccountId());}});
                    if(entityIn == null){
                        accountLog.setMemo("未找到转出黄金账户！");
                        return Response.error("未找到转出黄金账户！");
                    }
                    if(entityIn.getState() != 0){
                        accountLog.setMemo("转出黄金账户状态不正常！");
                        return Response.error("转出黄金账户状态不正常！");
                    }

                    if (accountSubGoldDao.withdrawal(entityOut) == 0){
                        accountLog.setMemo("黄金账户余额不足或账户状态不正常！");
                        throw new Exception("黄金账户余额不足或账户状态不正常！");
                    }

                    if(accountSubGoldDao.recharge(entityIn) == 0){
                        accountLog.setMemo("转入账户状态不正常！");
                        throw new Exception("转入账户状态不正常！");
                    }
                    accountLog.setState(0);
                    break;
                }
                case 2:{ //2网贷账户
                    return Response.error("网贷账户,暂无[转账]功能");
                }
            }
            accountLog.setState(0);
        } catch (Exception e) {
            log.error("账户[转账]异常!", e);
            accountLog.setState(1);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }finally {
            logAccountDao.insert(accountLog);
            lock.unlock();//释放锁
        }
        return result;
    }
}