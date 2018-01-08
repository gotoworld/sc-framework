package com.hsd.account.finance.api;

import com.github.pagehelper.PageInfo;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * <p>支付账户与三方账户绑定 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.finance}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IAccountBindThirdpartyService {
    String acPrefix = "/feign/account/IAccountBindThirdpartyService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(AccountBindThirdpartyDto dto) throws Exception;




    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(AccountBindThirdpartyDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<AccountBindThirdpartyDto> findDataIsList(AccountBindThirdpartyDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    AccountBindThirdpartyDto findDataById(AccountBindThirdpartyDto dto) throws Exception;

    /**
     * <p>员工信息。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/bindCard")
    Response bindCard(AccountBindThirdpartyDto dto)  throws Exception;

    /**
     * <p>员工信息。
     */
    @RequestMapping(method = {RequestMethod.PUT},value = acPrefix + "/unbindCard")
    Response unbindCard(AccountBindThirdpartyDto dto)  throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByUserId")
    List<AccountBindThirdpartyDto> findDataByUserId(AccountBindThirdpartyDto dto) throws Exception;

}