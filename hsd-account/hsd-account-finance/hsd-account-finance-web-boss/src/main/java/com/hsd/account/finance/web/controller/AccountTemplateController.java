package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountTemplateService;
import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.account.finance.dto.AccountTemplateDto;
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

@Api(description = "账户模板")
@RestController
@Slf4j
public class AccountTemplateController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountTemplateService accountTemplateService;
    private static final String acPrefix = "/boss/account/finance/accountTemplate/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountTemplate:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountTemplateDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountTemplateController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountTemplateDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            result.data = PageUtil.copy(accountTemplateService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>回收站 (已删除)。
     */
    @RequiresPermissions("accountTemplate:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "recycle/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response recycle(@ModelAttribute  AccountTemplateDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountTemplateController recycle.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountTemplateDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(1);
            result.data = PageUtil.copy(accountTemplateService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountTemplate:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountTemplateController info.........");
        Response result = new Response();
        try {
            AccountTemplateDto dto = new AccountTemplateDto(){{
                setId(id);
              setDelFlag(0);
            }};
            result.data = accountTemplateService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("accountTemplate:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "账户模板-逻辑删除")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("AccountTemplateController del.........");
        Response result = new Response(0,"success");
        try {
            if (id==null) {throw new RuntimeException("参数异常!");}
            AccountTemplateDto dto = new AccountTemplateDto(){{
                setId(id);
            }};
            result.message = accountTemplateService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <li>恢复。
     */
    @RequiresPermissions("accountTemplate:recovery")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recovery/{id}")
    @ALogOperation(type = "恢复", desc = "账户模板-恢复数据")
    @ApiOperation(value = "恢复")
    public Response recovery(@PathVariable("id") Long id) {
        log.info("AccountTemplateController recovery.........");
        Response result = new Response(0,"success");
        try {
            result.message=accountTemplateService.recoveryDataById(new AccountTemplateDto(){{setId(id);}});
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>物理删除。
     */
    @RequiresPermissions("accountTemplate:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "账户模板-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") Long id) {
        log.info("AccountTemplateController phydel.........");
        Response result = new Response(0,"success");
        try {
            if (id==null) {throw new RuntimeException("参数异常!");}
            AccountTemplateDto dto = new AccountTemplateDto(){{
                setId(id);
            }};
            result.message = accountTemplateService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"accountTemplate:add", "accountTemplate:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "账户模板")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute AccountTemplateDto dto, BindingResult bindingResult) {
        log.info("AccountTemplateController save.........");
        Response result = new Response(0,"success");
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
                        AccountTemplateAttributeDto attributeDto=new AccountTemplateAttributeDto();
                        attributeDto.setAttributeCode(request.getParameter("attributeCode"+attrName));
                        attributeDto.setAttributeName(request.getParameter("attributeName"+attrName));
                        attributeDto.setInputType(request.getParameter("inputType"+attrName));
                        attributeDto.setOptionValues(request.getParameter("optionValues"+attrName));
                        attributeDto.setRequired(ValidatorUtil.notEmpty(request.getParameter("required"+attrName))?1:0);
                        if(dto.getAttributes()==null) dto.setAttributes(new ArrayList<>());
                        dto.getAttributes().add(attributeDto);
                    });
                }
                result = accountTemplateService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}