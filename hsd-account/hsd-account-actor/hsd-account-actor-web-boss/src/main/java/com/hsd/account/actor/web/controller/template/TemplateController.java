package com.hsd.account.actor.web.controller.template;

import com.hsd.account.actor.api.template.ITemplateService;
import com.hsd.account.actor.dto.template.TemplateAttributeDto;
import com.hsd.account.actor.dto.template.TemplateDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "档案模板")
@RestController
@Slf4j
public class TemplateController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ITemplateService templateService;
    private static final String acPrefix = "/boss/account/actor/template/template/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("template:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  TemplateDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("TemplateController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new TemplateDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            result.data = PageUtil.copy(templateService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("template:menu")
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
    @RequiresPermissions("template:info")
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

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("template:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "档案模板-逻辑删除")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("TemplateController del.........");
        Response result = new Response();
        try {
            if (id==null) {throw new RuntimeException("参数异常!");};
            TemplateDto dto = new TemplateDto(){{
                setId(id);
            }};
            result.message = templateService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"template:add", "template:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "档案模板")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute TemplateDto dto, BindingResult bindingResult) {
        log.info("TemplateController save.........");
        Response result = new Response();
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + dto.getToken()))) {
                throw new RuntimeException("请不要重复提交!");
            }
            if (bindingResult.hasErrors()) {
                String errorMsg = "";
                List<ObjectError> errorList = bindingResult.getAllErrors();
                for (ObjectError error : errorList) {
                    errorMsg += (error.getDefaultMessage()) + ";";
                }
                result = Response.error(errorMsg);
            } else {
                if(dto.getAttrName()!=null){
                    dto.getAttrName().forEach(attrName->{
                        TemplateAttributeDto attributeDto=new TemplateAttributeDto();
                        attributeDto.setAttributeCode(request.getParameter("attributeCode"+attrName));
                        attributeDto.setAttributeName(request.getParameter("attributeName"+attrName));
                        attributeDto.setInputType(request.getParameter("inputType"+attrName));
                        attributeDto.setOptionValues(request.getParameter("optionValues"+attrName));
                        attributeDto.setRequired(ValidatorUtil.notEmpty(request.getParameter("required"+attrName))?1:0);
                        if(dto.getAttributes()==null) dto.setAttributes(new ArrayList<>());
                        dto.getAttributes().add(attributeDto);
                    });
                }
                result = templateService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}