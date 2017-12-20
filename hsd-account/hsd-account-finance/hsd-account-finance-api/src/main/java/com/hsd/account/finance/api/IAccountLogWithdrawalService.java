package com.hsd.account.finance.api;

import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.dto.AccountLogWithdrawalDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>账户-日志-提现流水 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.finance}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IAccountLogWithdrawalService {
    String acPrefix = "/feign/account/IAccountLogWithdrawalService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(AccountLogWithdrawalDto dto) throws Exception;




    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(AccountLogWithdrawalDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<AccountLogWithdrawalDto> findDataIsList(AccountLogWithdrawalDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    AccountLogWithdrawalDto findDataById(AccountLogWithdrawalDto dto) throws Exception;
}