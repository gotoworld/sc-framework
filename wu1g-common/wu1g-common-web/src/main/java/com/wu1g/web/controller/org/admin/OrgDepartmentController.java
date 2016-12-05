/*	
 * 组织架构_部门  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.org.admin;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.api.IOrgDepartmentService;
import com.wu1g.org.vo.OrgDepartment;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

/**
 * <p>组织架构_部门  ACTION类。
 * <ol>[功能概要] 
 * <li>初始化。 
 * <li>信息列表(未删除)。 
 * <li>编辑页面(页面)(新增or修改)。 
 * <li>信息保存(功能)(新增or修改)。 
 *</ol> 
 * @author easycode
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class OrgDepartmentController extends BaseController {

	private static final long serialVersionUID = -905929872130603565L;
	/**组织架构_部门 业务处理*/
	@Autowired
	private IOrgDepartmentService orgDepartmentService;
	
	//组织架构_部门 管理
	private static final String acPrefix="/org/dept/";
	private static final String init = "admin/org/org_dept";
	private static final String edit = "admin/org/org_dept_edit";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgDept:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("OrgDepartmentController init.........");
		Object x=orgDepartmentService.findDataTree(null);
		request.setAttribute( "beans", x );
		return init;
	}
	/**
	 * <p> 编辑。
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 */
	@RequiresPermissions("orgDept:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit(OrgDepartment bean, @PathVariable("id") String id) {
		log.info("OrgDepartmentController edit.........");
		int pageNum = 1;
		if(bean!=null && bean.getPageNum()!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			OrgDepartment bean1=new OrgDepartment();
			bean1.setId(id);
			bean=orgDepartmentService.findDataById(bean1);
		}
		if(bean==null||"add".equals(id)){
			bean=new OrgDepartment();
			bean.setId(IdUtil.createUUID(32));
			bean.setNewFlag("1");
		}
		bean.setPageNum(pageNum);
		bean.setToken(IdUtil.createUUID(32));
		request.setAttribute( "bean", bean );
		//--部门树
		request.setAttribute( "beans", orgDepartmentService.findDataTree(null) );
		return edit;
	}
	/**
	 * <p> 删除。
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("orgDept:del")
	@RequestMapping(value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="部门信息")
	public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
		log.info("OrgDepartmentController del.........");
		Response result = new Response();
		try {
			OrgDepartment bean1=new OrgDepartment();
			bean1.setId(id);
			result.message=orgDepartmentService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("msg", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 */
	@RequiresPermissions(value={"orgDept:add","orgDept:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="部门信息")
	public String save(@Validated OrgDepartment bean,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
		log.info("OrgDepartmentController save.........");
		Response result = new Response();
		if(bean!=null){
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
				}else{
					result.message=orgDepartmentService.saveOrUpdateData(bean);
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