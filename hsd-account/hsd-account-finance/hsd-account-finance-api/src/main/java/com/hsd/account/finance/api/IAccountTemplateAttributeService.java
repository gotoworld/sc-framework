package com.hsd.account.finance.api;

import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
/**
 * <p>账户模板属性 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.finance}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IAccountTemplateAttributeService {
    String acPrefix = "/feign/account/IAccountTemplateAttributeService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(AccountTemplateAttributeDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    String deleteData(AccountTemplateAttributeDto dto) throws Exception;




    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<AccountTemplateAttributeDto> findDataIsList(AccountTemplateAttributeDto dto) throws Exception;


}