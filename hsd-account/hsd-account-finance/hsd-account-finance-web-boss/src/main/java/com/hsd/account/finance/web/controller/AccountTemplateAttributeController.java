package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountTemplateAttributeService;
import com.hsd.account.finance.dto.AccountTemplateAttributeDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "账户模板属性")
@RestController
@Slf4j
public class AccountTemplateAttributeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountTemplateAttributeService accountTemplateAttributeService;
    private static final String acPrefix = "/boss/account/finance/accountTemplateAttribute/";





    /**
     * <p>物理删除。
     */
    @RequiresPermissions("accountTemplateAttribute:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "账户模板属性-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") Long id) {
        log.info("AccountTemplateAttributeController phydel.........");
        Response result = new Response();
        try {
           if (id==null) {throw new RuntimeException("参数异常!");}
            AccountTemplateAttributeDto dto = new AccountTemplateAttributeDto(){{
            setId(id);
           }};
            result.message = accountTemplateAttributeService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"accountTemplateAttribute:add", "accountTemplateAttribute:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "账户模板属性")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute AccountTemplateAttributeDto dto, BindingResult bindingResult) {
        log.info("AccountTemplateAttributeController save.........");
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
                result = accountTemplateAttributeService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}