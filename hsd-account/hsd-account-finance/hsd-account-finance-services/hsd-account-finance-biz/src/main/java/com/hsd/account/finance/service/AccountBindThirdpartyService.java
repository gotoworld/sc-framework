package com.hsd.account.finance.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.finance.api.IAccountBindThirdpartyService;
import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dao.IAccountBindThirdpartyDao;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.entity.AccountBindThirdparty;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@FeignService
@Slf4j
public class AccountBindThirdpartyService extends FinanceBaseService implements IAccountBindThirdpartyService {
    @Autowired
    private IAccountBindThirdpartyDao accountBindThirdpartyDao;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private RequestThreeService requestThreeService;

    @Autowired
    private IIdentityService identityService;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody AccountBindThirdpartyDto dto) {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
                //判断数据是否存在
                if (accountBindThirdpartyDao.isDataYN(entity) != 0) {
                    //数据存在
                    accountBindThirdpartyDao.update(entity);
                } else {
                    //新增
                     accountBindThirdpartyDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }



        @Override
        public PageInfo findDataIsPage(@RequestBody AccountBindThirdpartyDto dto) {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = accountBindThirdpartyDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), AccountBindThirdpartyDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<AccountBindThirdpartyDto> findDataIsList(@RequestBody AccountBindThirdpartyDto dto) {
            List<AccountBindThirdpartyDto>  results = null;
            try {
                AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
                 results = copyTo(accountBindThirdpartyDao.findDataIsList(entity), AccountBindThirdpartyDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public AccountBindThirdpartyDto findDataById(@RequestBody AccountBindThirdpartyDto dto) {
            AccountBindThirdpartyDto result = null;
            try {
                AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
                result = copyTo(accountBindThirdpartyDao.selectByPrimaryKey(entity),AccountBindThirdpartyDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

    @Override
    public List<AccountBindThirdpartyDto> findDataByUserId(AccountBindThirdpartyDto dto) {
        List<AccountBindThirdpartyDto> result = null;
        try {
            AccountBindThirdparty entity = copyTo(dto, AccountBindThirdparty.class);
            result = copyTo(accountBindThirdpartyDao.selectBindThirdparty(entity),AccountBindThirdpartyDto.class);
        } catch (Exception e) {
            log.error("信息[详情]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
        public Response bindCard(@RequestBody AccountBindThirdpartyDto dto) {
            Response result = new Response(0,"success");
            String name = dto.getRealName();
            String cardNo = dto.getThirdpartyAccount();
            String certNo = dto.getCardNo();
            Long userId = dto.getAppUserId();
            Long accountId = dto.getAccountId();
            String phone = dto.getCellphone();
            if(StringUtils.isAnyEmpty(name,cardNo,certNo,phone)){
                result = Response.error("绑卡信息缺失!");
                return result;
            }
            try {
                AccountBindThirdparty accountBindThirdparty = new AccountBindThirdparty(){{
                    setAppUserId(userId);
                    setThirdpartyAccount(cardNo);
                    setRealName(name);
                    setThirdpartyType(0);
                    setCardNo(certNo);
                }};
                List<AccountBindThirdparty> accountBindThirdpartys = accountBindThirdpartyDao.selectBindThirdparty(accountBindThirdparty);
                if(accountBindThirdpartys != null){
                    for(AccountBindThirdparty temp : accountBindThirdpartys){
                        Integer stateTemp = temp.getState();
                        if(stateTemp != null && stateTemp == 0 && accountId == temp.getAccountId()){
                            result = new Response(1,"此卡已被绑定!");
                            return result;
                        }
                        else if(stateTemp != null && stateTemp == 1 && accountId == temp.getAccountId()){
                            temp.setDateSuccess(new Date());
                            temp.setDateBind(new Date());
                            temp.setMemo("重新绑定");
                            accountBindThirdpartyDao.update(temp);
                            return result;
                        }
                    }
                }

                AccountDto accountDto = new AccountDto(){{
                    setId(accountId);
                    setAppUserId(userId);
                }};
                //获取账户信息
                List<AccountDto> userAccount = accountService.findAccount(accountDto);
                if(CollectionUtils.isEmpty(userAccount)){
                    result = Response.error("账户不存在!");
                    return result;
                }

                //获取实名信息
                IdentityDto identityDto = new IdentityDto();
                identityDto.setUserId(userId);
                IdentityDto userIdentity =  identityService.findDataById(identityDto);
                if(userIdentity == null){
                    result = Response.error("未找到用户实名认证信息!");
                    return result;
                }
               Integer identityState = userIdentity.getState();
                if(identityState == null || identityState.intValue() != 1){
                    result = Response.error("实名认证未成功!");
                    return result;
                }
                Integer identityCertType = userIdentity.getCredentialType();
                String identityCertNo = userIdentity.getCredentialNumber();
                String identityName = userIdentity.getRealName();
                if(!(name.equals(identityName) && certNo.equals(identityCertNo) && identityCertType != null && identityCertType.intValue() == 0)){
                    result = Response.error("信息不正确!");
                    return result;
                }

                /**
                 * 卡三要素认证
                 */
                JSONObject responseJson = requestThreeService.cardFourElement(name,certNo,cardNo,phone);
                if(responseJson == null){
                    result = Response.error("银行卡验证错误!");
                    return result;
                }
                int errorCode = responseJson.getIntValue("errorCode");
                JSONObject  data = responseJson.getJSONObject("data");
                if(errorCode != 200){
                    String  errorMsg = responseJson.getString("errorMsg");
                    result.setCode(errorCode);
                    result.setMessage(errorMsg);
                    result.setData(data);
                    return result;
                }

                String verifyResult = data.getString("verifyResult");

                if(!"S".equalsIgnoreCase(verifyResult)){
                    result = Response.error("信息不一致!");
                    return result;
                }
                accountBindThirdparty.setMemo("首次绑定");
                accountBindThirdparty.setId(idGenerator.nextId());
                accountBindThirdparty.setState(0);
                accountBindThirdparty.setDateBind(new Date());
                accountBindThirdparty.setDateSuccess(new Date());
                accountBindThirdparty.setAccountId(accountId);
                accountBindThirdpartyDao.insert(accountBindThirdparty);
                result.setData(accountBindThirdparty);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public Response unbindCard(@RequestBody AccountBindThirdpartyDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                Long userId = dto.getAppUserId();
                String cardNo =  dto.getThirdpartyAccount();
                Long accountId = dto.getAccountId();
                AccountBindThirdparty accountBindThirdparty = new AccountBindThirdparty(){{
                    setAppUserId(userId);
                    setThirdpartyAccount(cardNo);
                    setAccountId(accountId);
                    setMemo("解绑");
                    setDateUnbind(new Date());
                    setState(1);
                }};
                int updateCount = accountBindThirdpartyDao.updateByAccountAndCard(accountBindThirdparty);
                if(updateCount < 0){
                    List<AccountBindThirdparty> accountBindThirdpartys = accountBindThirdpartyDao.selectBindThirdparty(accountBindThirdparty);
                    if(accountBindThirdpartys.size() < 0){
                        result = new Response(1,"没有相关绑卡信息!");
                    }
                }
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
}