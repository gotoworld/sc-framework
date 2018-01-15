package com.hsd.account.finance.api;

import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.dto.AccountSubLoanDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>子账户-P2P网贷 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.finance}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IAccountSubLoanService {
    String acPrefix = "/feign/account/IAccountSubLoanService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(AccountSubLoanDto dto) throws Exception;




    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(AccountSubLoanDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<AccountSubLoanDto> findDataIsList(AccountSubLoanDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    AccountSubLoanDto findDataById(AccountSubLoanDto dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByUserId")
    AccountSubLoanDto findDataByUserId(AccountSubLoanDto dto) throws Exception;

    /**
     * <p>开户。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/open")
    Response open(AccountSubLoanDto dto) throws Exception;
}