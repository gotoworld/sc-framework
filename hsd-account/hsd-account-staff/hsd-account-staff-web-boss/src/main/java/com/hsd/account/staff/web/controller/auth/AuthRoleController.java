package com.hsd.account.staff.web.controller.auth;

import com.hsd.account.staff.api.auth.IAuthRoleService;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "权限_角色信息")
@RestController
@Slf4j
public class AuthRoleController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAuthRoleService authRoleService;
    private static final String acPrefix = "/boss/account/staff/auth/authRole/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("authRole:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute AuthRoleDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AuthRoleController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
               dto = new AuthRoleDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(authRoleService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("authRole:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息分页")
    public Response list(@ModelAttribute AuthRoleDto dto) {
        log.info("AuthRoleController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
               dto = new AuthRoleDto();
            }
            dto.setDelFlag(0);
            // 信息列表
            result.data = authRoleService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("authRole:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AuthRoleController info.........");
        Response result = new Response(0, "success");
        try {
            AuthRoleDto dto = new AuthRoleDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = authRoleService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>删除。
     */
    @RequiresPermissions("authRole:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "权限_角色信息")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("AuthRoleController del.........");
        Response result = new Response(0, "success");
        try {
            AuthRoleDto dto = new AuthRoleDto();
            dto.setId(id);
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
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "权限_角色信息")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute AuthRoleDto dto, BindingResult bindingResult) {
        log.info("AuthRoleController save.........");
        Response result = new Response(0, "success");
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
                result = authRoleService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequiresPermissions(value = {"authRole:menu"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "perm")
    @ApiOperation(value = "获取当前角色已有(功能/权限)")
    public Response perm(@RequestParam(name = "roleId") Long roleId) {
        log.info("AuthRoleController perm.........");
        Response result = new Response(0, "success");
        try {
            AuthRoleDto dto=new AuthRoleDto();
            dto.setId(roleId);
            result.data = authRoleService.findPermIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions(value = {"authRole:menu"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "menu")
    @ApiOperation(value = "获取当前角色已有(菜单)")
    public Response menu(@RequestParam(name = "roleId") Long roleId) {
        log.info("AuthRoleController menu.........");
        Response result = new Response(0, "success");
        try {
            AuthRoleDto dto=new AuthRoleDto();
            dto.setId(roleId);
            result.data = authRoleService.findMenuIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions(value = {"authRole:menu"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "app")
    @ApiOperation(value = "获取当前角色已有(应用)")
    public Response app(@RequestParam(name = "roleId") Long roleId) {
        log.info("AuthRoleController app.........");
        Response result = new Response(0, "success");
        try {
            AuthRoleDto dto=new AuthRoleDto();
            dto.setId(roleId);
            result.data = authRoleService.findAppIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}