/*	
 * 全景_类目 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.web.controller.admin;

import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.api.pano.IPanoCategoryService;
import com.wu1g.vo.pano.PanoCategory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;


/**
 * <p>全景_类目 ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class PanoCategoryController extends BaseController {

    private static final long serialVersionUID = -344702788488841783L;

    @Autowired
    private IPanoCategoryService panoCategoryService;

    private static final String acPrefix = "/pano/category/";
    private static final String init = "admin/pano/pano_category";
    private static final String edit = "admin/pano/pano_category_edit";
    private static final String list = "admin/pano/pano_category_list";
    private static final String success = "redirect:/h" + acPrefix + "init";

    /**
     * <p>初始化处理。
     */
    @RequiresPermissions("panoCat:menu")
    @RequestMapping(value = acPrefix + "init")
    public String init() {
        log.info("PanoCategoryController init.........");
        //获取类目列表
        request.setAttribute("categoryBeans", panoCategoryService.findDataTree(null));
        return init;
    }

    /**
     * <p>编辑。
     */
    @RequiresPermissions("panoCat:edit")
    @RequestMapping(value = acPrefix + "edit/{id}")
    public String edit(PanoCategory bean, @PathVariable("id") String id) {
        log.info("PanoCategoryController edit.........");
        int pageNum = 1;
        if (bean != null && bean.getPageNum() != null) {
            pageNum = bean.getPageNum();
        }
        if ("add".equals(id)) {
            id = null;
            bean = null;
        }
        if (ValidatorUtil.notEmpty(id)) {
            if (bean == null) {
                bean = new PanoCategory();
            }
            bean.setId(id);// ID
            bean = panoCategoryService.findDataById(bean);
        }
        if (bean == null||"add".equals(id)) {
            bean = new PanoCategory();
            bean.setId(IdUtil.createUUID(32));
            bean.setNewFlag("1");
        }
        bean.setPageNum(pageNum);
        bean.setToken(IdUtil.createUUID(32));
        request.setAttribute("bean", bean);
        return edit;
    }

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("panoCat:del")
    @RequestMapping(value = acPrefix + "del/{id}")
    @ALogOperation(type="删除",desc="全景类目信息")
    public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
        log.info("PanoCategoryController del.........");
        Response result = new Response();
        try {
            PanoCategory bean = new PanoCategory();
            bean.setId(id);// ID
            result.message = panoCategoryService.deleteDataById(bean);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }

    /**
     * <p>
     * 信息保存
     * <ol>
     * [功能概要]
     * <li>新增。
     * <li>修改。
     */
    @RequiresPermissions(value = {"panoCat:add", "panoCat:edit"}, logical = Logical.OR)
    @RequestMapping(value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type="修改",desc="全景类目信息")
    public String save(@Validated PanoCategory bean,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
        log.info("PanoCategoryController save.........");
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
                    result.message = panoCategoryService.saveOrUpdateData(bean);
                    result.data = bean.getId();

                    request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
                }
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("表单获取失败!");
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }
}