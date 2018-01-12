package com.hsd.account.finance.api;

import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.dto.op.AccountFreezeDto;
import com.hsd.account.finance.dto.op.AccountRechargeDto;
import com.hsd.account.finance.dto.op.AccountReverseDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>支付账户 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.finance}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IAccountService {
    String acPrefix = "/feign/account/IAccountService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(AccountDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(AccountDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<AccountDto> findDataIsList(AccountDto dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    AccountDto findDataById(AccountDto dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByUserId")
    List<AccountDto> findAccount(AccountDto dto) throws Exception;

    /**
     * 状态变更
     */
    @RequestMapping(method={RequestMethod.PUT},value = acPrefix + "updateState")
    Response updateState(AccountDto dto) throws Exception;

    /** <p>冲正/抵扣 */
    @RequestMapping(method={RequestMethod.PUT},value = acPrefix + "reverse")
    Response reverse(AccountReverseDto dto) throws Exception;

    /** <p>冻结/解冻 */
    @RequestMapping(method={RequestMethod.PUT},value = acPrefix + "freeze")
    Response freeze(AccountFreezeDto dto) throws Exception;

    /** <p>充值 */
    @RequestMapping(method={RequestMethod.PUT},value = acPrefix + "recharge")
    Response recharge(AccountRechargeDto dto) throws Exception;
}