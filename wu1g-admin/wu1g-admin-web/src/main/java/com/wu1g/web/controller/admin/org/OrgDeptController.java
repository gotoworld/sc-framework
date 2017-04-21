package com.wu1g.web.controller.admin.org;

import com.wu1g.api.org.IOrgDeptService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.vo.org.OrgDept;
import com.wu1g.web.controller.BaseController;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

/**
 * <p>组织架构_部门  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class OrgDeptController extends BaseController {

    private static final long serialVersionUID = -905929872130603565L;
    /**
     * 组织架构_部门 业务处理
     */
    @Autowired
    private IOrgDeptService orgDeptService;

    //组织架构_部门 管理
    private static final String acPrefix = "/org/dept/";
    private static final String init = "admin/org/org_dept";
    private static final String edit = "admin/org/org_dept_edit";
    private static final String success = "redirect:/h" + acPrefix + "init";

    /**
     * <p> 初始化处理。
     */
    @RequiresPermissions("orgDept:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "init")
    public String init() {
        log.info("OrgDepartmentController init.........");
        Object x = orgDeptService.findDataTree(null);
        request.setAttribute("beans", x);
        return init;
    }
    /**
     * <p> 信息树json。
     */
    @RequiresPermissions("orgDept:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
    @ResponseBody
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
     * <p> 编辑。
     */
    @RequiresPermissions("orgDept:edit")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "edit/{id}")
    public String edit(OrgDept bean, @PathVariable("id") Long id) {
        log.info("OrgDepartmentController edit.........");
        int pageNum= getPageNum(bean);
        if (0!=id) {
            OrgDept bean1 = new OrgDept();
            bean1.setId(id);
            bean = orgDeptService.findDataById(bean1);
        }
        if (bean == null || 0==id) {
            bean = new OrgDept();
            bean.setNewFlag(1);
        }
        bean.setPageNum(pageNum);
        request.setAttribute("bean", bean);
        //--部门树
        request.setAttribute("beans", orgDeptService.findDataTree(null));
        return edit;
    }

    /**
     * <p> 删除。
     */
    @RequiresPermissions("orgDept:del")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "部门信息")
    public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
        log.info("OrgDepartmentController del.........");
        Response result = new Response();
        try {
            OrgDept bean1 = new OrgDept();
            bean1.setId(id);
            result.message = orgDeptService.deleteDataById(bean1);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"orgDept:add", "orgDept:edit"}, logical = Logical.OR)
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "部门信息")
    public String save(@Validated OrgDept bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
        log.info("OrgDepartmentController save.........");
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
                    result.message = orgDeptService.saveOrUpdateData(bean);
                    result.data = bean.getId();
                    request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
                }
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("信息保存失败!");
        }
        modelMap.addFlashAttribute("msg", result);
        return success;
    }
}