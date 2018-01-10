package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.api.IAccountLogOperationalService;
import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dao.*;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.dto.op.AccountFreezeDto;
import com.hsd.account.finance.dto.op.AccountReverseDto;
import com.hsd.account.finance.entity.*;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody AccountDto dto) {
        Response result = new Response(0, "success");
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
    public AccountDto findDataByUserId(@RequestBody AccountDto dto) {
        AccountDto result = null;
        try {
            Account entity = copyTo(dto, Account.class);
            result = copyTo(accountDao.selectByUserId(entity), AccountDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public Response updateState(@RequestBody AccountDto dto) throws Exception {
        Response result = new Response(0, "success");
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
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response reverse(@RequestBody AccountReverseDto dto) throws Exception {
        Response result = new Response("success");
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
        Response result = new Response("success");
        try {
            //.判断操作类型 冻结/解冻
            //.判断冻结账户种类
            //.执行[冻结/解冻]金额/数量 update 账户 set available_money=,freeze_money=,freezeTotalAmount= where id=
            //.记录冻结日志
            //.记录操作日志

            //获取指定账户信息
            switch (dto.getBizAccountType()){//业务账户0资金账户1黄金账户2网贷账户
                case 0: {
                    Account accountEntity = copyTo(dto, Account.class);
                    accountDao.freeze(accountEntity);
                    break;
                }
                case 1: {
                    AccountSubGold accountSubGoldEntity = copyTo(dto, AccountSubGold.class);
                    accountSubGoldDao.freeze(accountSubGoldEntity);
                    break;
                }
                case 2: {
                    AccountSubLoan accountSubLoanEntity = copyTo(dto, AccountSubLoan.class);
                    accountSubGoldDao.freeze(accountSubLoanEntity);
                    break;
                }
                default:{
                    return Response.error("业务账户未知");
                }
            }


            //获取
            AccountLogFreeze logFreezeEntity=copyTo(dto,AccountLogFreeze.class);
            logFreezeDao.insert(logFreezeEntity);

            AccountLogOperational logOperational=copyTo(dto,AccountLogOperational.class);
            logOperational.setData(dto.toString());
            logOperational.setType(dto.getActionType()==0?1:2);//1冻结,2解冻
            logOperational.setMemo("管理员操作"+(dto.getActionType()==0?"[冻结]":"[解冻]"));
            logOperational.setUserName(dto.getStaffName());
            logOperational.setCreateId(dto.getCreateId());
            logOperational.setCreateIp(dto.getIp());
            logOperationalDao.insert(logOperational);

        } catch (Exception e) {
            log.error("账户[冻结/解冻]异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
}