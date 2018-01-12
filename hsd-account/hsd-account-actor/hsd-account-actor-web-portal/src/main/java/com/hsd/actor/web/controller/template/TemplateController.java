package com.hsd.actor.web.controller.template;

import com.hsd.account.actor.api.template.ITemplateService;
import com.hsd.account.actor.dto.template.TemplateDto;
import com.hsd.framework.Response;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "档案模板")
@RestController
@Slf4j
public class TemplateController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ITemplateService templateService;
    private static final String acPrefix = "/api/account/actor/template/template/";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list( @RequestParam("userType") Integer userType) {
        log.info("TemplateController list.........");
        Response result = new Response();
        try {
            TemplateDto dto = new TemplateDto(){{ setUserType(userType); }};
            result.data = templateService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("TemplateController info.........");
        Response result = new Response();
        try {
            if (id==null) {throw new RuntimeException("参数异常!");};
            TemplateDto dto = new TemplateDto(){{
                setId(id);
                setDelFlag(0);
            }};
            result.data = templateService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}