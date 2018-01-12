package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountTemplateAttributeService;
import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "账户模板属性")
@RestController
@Slf4j
public class AccountTemplateAttributeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountTemplateAttributeService accountTemplateAttributeService;
    private static final String acPrefix = "/boss/account/finance/accountTemplateAttribute/";

    @RequiresPermissions("accountTemplateAttribute:menu")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@RequestParam("templateId") Long templateId) {
        log.info("AccountTemplateAttributeController list.........");
        Response result = new Response(0, "success");
        try {
            result.data = accountTemplateAttributeService.findDataIsList(new AccountTemplateAttributeDto(){{setTemplateId(templateId);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}