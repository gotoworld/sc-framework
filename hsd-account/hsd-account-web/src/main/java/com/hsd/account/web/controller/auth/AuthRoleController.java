/*	
 * 权限_角色信息  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.hsd.account.web.controller.auth;

import com.github.pagehelper.PageInfo;
import com.hsd.account.api.auth.IAuthPermService;
import com.hsd.account.api.auth.IAuthRoleService;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.account.vo.auth.AuthRole;
import com.hsd.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>权限_角色信息  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class AuthRoleController extends BaseController {

    private static final long serialVersionUID = -250454793558646875L;
    @Autowired
    private IAuthRoleService authRoleService;
    @Autowired
    private IAuthPermService authPermService;

    //权限_角色信息 管理
    private static final String acPrefix = "/auth/role/";
    private static final String init = "admin/auth/auth_role";
    private static final String edit = "admin/auth/auth_role_edit";
    private static final String list = "admin/auth/auth_role_list";
    private static final String success = "redirect:/h" + acPrefix + "init";

    /**
     * <p> 初始化处理。
     */
    @RequiresPermissions("authRole:menu")
    @RequestMapping(method = {RequestMethod.GET}, value = acPrefix + "init")
    public String init() {
        log.info("AuthRoleController init.........");
        return init;
    }

    /**
     * <p> 信息列表 (未删除)。
     */
    @RequiresPermissions("authRole:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    public String list(AuthRole bean) {
        log.info("AuthRoleController list.........");
        if (bean == null) {
            bean = new AuthRole();
        }
        //--超级管理员标记
        bean.setIsSuper("1".equals(SecurityUtils.getSubject().getSession().getAttribute("isSuper")) ? 1 : 0);
        //信息列表
        PageInfo<?> page = new PageInfo<>(authRoleService.findDataIsPage(bean));
        request.setAttribute("beans", page.getList());
        //分页对象
        request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY, page);
        return list;
    }

    /**
     * <p> 编辑。
     */
    @RequiresPermissions("authRole:edit")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "edit/{id}")
    public String edit(AuthRole bean, @PathVariable("id") Long id) {
        log.info("AuthRoleController edit.........");
        int pageNum= getPageNum(bean);
        if (0 != id) {
            AuthRole bean1 = new AuthRole();
            bean1.setId(id);//角色ID
            bean = authRoleService.findDataById(bean1);
            if (bean != null) {
                //获取当前角色所有的功能
                Map xdto = new HashMap();
                xdto.put("roleId", bean.getId());
                request.setAttribute("permBeans", authPermService.findPermDataIsListByRoleId(xdto));
            }
        }
        if (bean == null || 0 == id) {
            bean = new AuthRole();
            bean.setNewFlag(1);
        }
        bean.setPageNum(pageNum);
        request.setAttribute("bean", bean);
        //权限信息树
        request.setAttribute("permTree", authPermService.findDataTree(null));
        return edit;
    }

    /**
     * <li>逻辑删除。
     */
    @RequiresPermissions("authRole:del")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "角色信息")
    public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
        log.info("AuthRoleController del.........");
        Response result = new Response();
        try {
            AuthRole bean1 = new AuthRole();
            bean1.setId(id);//角色ID
            result.message = authRoleService.deleteDataById(bean1);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        modelMap.addFlashAttribute("result", result);
        return success;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"authRole:add", "authRole:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "角色信息")
    public String save(@Valid AuthRole bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
        log.info("AuthRoleController save.........");
        Response result = new Response();
        if (bean != null) {
            try {
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
                    result = authRoleService.saveOrUpdateData(bean);
                    request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
                }
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("信息保存失败!");
        }
        modelMap.addFlashAttribute("result", result);
        return success;
    }
}