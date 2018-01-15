package com.hsd.account.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.finance.api.IAccountSubGoldService;
import com.hsd.account.finance.dao.IAccountDao;
import com.hsd.account.finance.dao.IAccountSubGoldDao;
import com.hsd.account.finance.dto.AccountSubGoldDto;
import com.hsd.account.finance.entity.Account;
import com.hsd.account.finance.entity.AccountSubGold;
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

import java.util.Date;
import java.util.List;

@FeignService
@Slf4j
public class AccountSubGoldService extends BaseService implements IAccountSubGoldService {
    @Autowired
    private IAccountSubGoldDao accountSubGoldDao;

    @Autowired
    private IIdentityService identityService;

    @Autowired
    private IAccountDao accountDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountSubGoldDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                //判断数据是否存在
                if (accountSubGoldDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountSubGoldDao.update(entity);
                } else {
                    //新增
                     accountSubGoldDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountSubGoldDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountSubGold entity = copyTo(dto, AccountSubGold.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountSubGoldDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountSubGoldDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountSubGoldDto> findDataIsList(@RequestBody AccountSubGoldDto dto) {
            List<AccountSubGoldDto>  results = null;
            try {
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                 results = copyTo(accountSubGoldDao.findDataIsList(entity), AccountSubGoldDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountSubGoldDto findDataById(@RequestBody AccountSubGoldDto dto) {
            AccountSubGoldDto result = null;
            try {
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                result = copyTo(accountSubGoldDao.selectByPrimaryKey(entity),AccountSubGoldDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public AccountSubGoldDto findDataByUserId(AccountSubGoldDto dto) throws Exception {
            AccountSubGoldDto result = null;
            try {
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                result = copyTo(accountSubGoldDao.selectByUserId(entity),AccountSubGoldDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response open(AccountSubGoldDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountSubGold entity = copyTo(dto, AccountSubGold.class);
                Long type = entity.getType();
                Long userId = entity.getAppUserId();
                if(type == null || userId == null){
                    throw new RuntimeException("参数异常!");
                }
                AccountType accountType = new AccountType(){{setId(type);}};
                AccountType accountTypeS = (AccountType)accountDao.selectByPrimaryKey(entity);
                if(accountTypeS == null){
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
                //判断数据是否存在
                if (accountSubGoldDao.isDataYN(entity) != 0) {
                    result = Response.error("黄金账户已开通,每个用户只能开通一个黄金账户!");
                    return result;
                }

                Long id = idGenerator.nextId();
                entity.setId(id);

                //设置资金账户
                Long accountId = idGenerator.nextId();
                entity.setAccountId(accountId);

                entity.setState(0);
                entity.setDateOpen(new Date());
                //新增
                accountSubGoldDao.insert(entity);

                //默认开通资金账户
                Account account = copyTo(entity, Account.class);
                account.setId(accountId);
                //需要默认开得资金账户类型
                account.setAccountType(type); //待定
                account.setAliasName(accountTypeS.getName());
                account.setCurrency(0);
                account.setState(0);
                account.setBiUpdateTs(new Date());
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