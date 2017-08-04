package com.hsd.account.web.controller.auth;

import com.github.pagehelper.PageInfo;
import com.hsd.account.api.auth.IAuthPermService;
import com.hsd.account.api.auth.IAuthRoleService;
import com.hsd.dto.auth.AuthPermDto;
import com.hsd.dto.auth.AuthRoleDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "权限_角色信息")
@RestController
@Slf4j
public class AuthRoleController extends BaseController {
    private static final String acPrefix = "/api/account/auth/role/";

    @Autowired
    private IAuthRoleService authRoleService;
    @Autowired
    private IAuthPermService authPermService;

    /**
     * <p> 信息分页 (未删除)。
     */
    @RequiresPermissions("authRole:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page")
    @ApiOperation(value = "信息分页")
    public Response page(AuthRoleDto dto) {
        log.info("AuthRoleController page.........");
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null)throw new RuntimeException("参数异常");
            //--超级管理员标记
            dto.setIsSuper("1".equals(SecurityUtils.getSubject().getSession().getAttribute("isSuper")) ? 1 : 0);
            PageInfo<?> page = new PageInfo<>(authRoleService.findDataIsPage(dto));
            result.data=getPageDto(page);
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
        log.info("AuthRoleController info.........");
        Response result = new Response();
        try {
            AuthRoleDto dto=new AuthRoleDto();
            dto.setId(id);
            result.data=authRoleService.findDataById(dto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 当前角色已有权限。
     */
    @RequiresPermissions("authRole:edit")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "authPerm/{id}")
    @ApiOperation(value = "当前角色已有权限")
    public Response authPerm(@PathVariable("id") Long id) {
        log.info("AuthRoleController authPerm.........");
        Response result = new Response();
        try {
            Map xdto = new HashMap();
            xdto.put("roleId", id);
            result.data=authPermService.findPermDataIsListByRoleId(xdto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <li>权限信息树。
     */
    @RequiresPermissions("authRole:del")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "permTree")
    public Response permTree() {
        log.info("AuthRoleController permTree.........");
        Response result = new Response();
        try {
            result.data = authPermService.findDataIsTree(new AuthPermDto());
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <li>逻辑删除。
     */
    @RequiresPermissions("authRole:del")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "角色信息")
    @ApiOperation(value = "逻辑删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("AuthRoleController del.........");
        Response result = new Response();
        try {
            AuthRoleDto dto = new AuthRoleDto();
            dto.setId(id);//角色ID
            result.message = authRoleService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"authRole:add", "authRole:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "角色信息")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @RequestBody AuthRoleDto dto, BindingResult bindingResult) {
        log.info("AuthRoleController save.........");
        Response result = new Response();
        try {
            if (dto == null)throw new RuntimeException("参数异常");
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
                result = authRoleService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}