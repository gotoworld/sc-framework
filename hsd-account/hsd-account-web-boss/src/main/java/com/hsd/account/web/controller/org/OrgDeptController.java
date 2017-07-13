package com.hsd.account.web.controller.org;

import com.hsd.account.api.org.IOrgDeptService;
import com.hsd.account.vo.org.OrgDept;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "组织架构_部门")
@RestController
@Slf4j
public class OrgDeptController extends BaseController {
    private static final String acPrefix = "/boss/account/org/dept/";

    @Autowired
    private IOrgDeptService orgDeptService;

    /**
     * <p> 信息树json。
     */
    @RequiresPermissions("orgDept:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
    @ResponseBody
    @ApiOperation(value = "信息树")
    public Response jsonTree() {
        log.info("OrgDepartmentController jsonTree.........");
        Response result=new Response();
        try {
            result.data=orgDeptService.findDataTree(null);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 详情。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"info/{id}")
    @ApiOperation(value = "详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("OrgDepartmentController info.........");
        Response result = new Response();
        try {
            OrgDept bean=new OrgDept();
            bean.setId(id);
            result.data=orgDeptService.findDataById(bean);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 删除。
     */
    @RequiresPermissions("orgDept:del")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "部门信息")
    @ApiOperation(value = "删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("OrgDepartmentController del.........");
        Response result = new Response();
        try {
            OrgDept bean = new OrgDept();
            bean.setId(id);
            result.message = orgDeptService.deleteDataById(bean);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"orgDept:add", "orgDept:edit"}, logical = Logical.OR)
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "部门信息")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated OrgDept bean, BindingResult bindingResult) {
        log.info("OrgDepartmentController save.........");
        Response result = new Response();
        try {
            if (bean == null)throw new RuntimeException("参数异常");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + bean.getToken()))) {
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
                result = orgDeptService.saveOrUpdateData(bean);
                request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}