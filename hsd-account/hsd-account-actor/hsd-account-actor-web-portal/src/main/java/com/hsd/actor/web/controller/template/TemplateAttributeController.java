package com.hsd.actor.web.controller.template;

import com.hsd.account.actor.api.template.ITemplateAttributeService;
import com.hsd.account.actor.dto.template.TemplateAttributeDto;
import com.hsd.framework.Response;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "模板属性")
@RestController
@Slf4j
public class TemplateAttributeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ITemplateAttributeService templateAttributeService;
    private static final String acPrefix = "/api/account/actor/template/templateAttribute/";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@RequestParam("templateId") Long templateId) {
        log.info("TemplateAttributeController list.........");
        Response result = new Response(0, "success");
        try {
            TemplateAttributeDto dto = new TemplateAttributeDto(){{ setTemplateId(templateId); }};
            result.data = templateAttributeService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}