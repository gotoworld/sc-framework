package com.hsd.account.finance.api;

import com.hsd.account.finance.dto.AccountLogFreezeDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
/**
 * <p>账户-日志-资金冻结记录 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IAccountLogFreezeService {
    String acPrefix = "/feign/account/IAccountLogFreezeService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(AccountLogFreezeDto dto) throws Exception;




    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(AccountLogFreezeDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<AccountLogFreezeDto> findDataIsList(AccountLogFreezeDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    AccountLogFreezeDto findDataById(AccountLogFreezeDto dto) throws Exception;
}