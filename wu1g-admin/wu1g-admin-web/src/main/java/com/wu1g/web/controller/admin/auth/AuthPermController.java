/*	
 * 权限_权限信息  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.web.controller.admin.auth;

import com.wu1g.api.auth.IAuthPermService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.vo.auth.AuthPerm;
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

import javax.validation.Valid;
import java.util.List;

/**
 * <p>权限_权限信息  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class AuthPermController extends BaseController {
	private static final long serialVersionUID = -255059907001339225L;
	/**权限_权限信息 业务处理*/
	@Autowired
	private IAuthPermService authPermService;
	
	//权限_权限信息 管理
	private static final String acPrefix="/auth/perm/";
	private static final String init = "admin/auth/auth_perm";
	private static final String edit = "admin/auth/auth_perm_edit";
	private static final String success = "redirect:/h"+acPrefix+"init";
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("authPerm:menu")
	@RequestMapping(method={RequestMethod.GET},value=acPrefix+"init")
	public String init() {
		log.info("AuthPermController init.........");
		//信息列表
		List<AuthPerm> beans=authPermService.findDataTree(null);
		request.setAttribute( "beans", beans );
		return init;
	}
	/**
	 * <p> 信息树json。
	 */
	@RequiresPermissions("authPerm:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
	@ResponseBody
	public Response jsonTree() {
		log.info("AuthPermController jsonTree.........");
		Response result=new Response();
		try {
			result.data=authPermService.findDataTree(null);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 编辑。
	 */
	@RequiresPermissions("authPerm:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix+"edit/{id}")
	public String edit( AuthPerm bean,@PathVariable("id") String id) {
		log.info("AuthPermController edit.........");
		if(ValidatorUtil.notEmpty(id)){
			AuthPerm bean1=new AuthPerm();
			bean1.setId(id);//权限id
			bean=authPermService.findDataById(bean1);
		}
		if(bean==null||"0".equals(id)){
			bean=new AuthPerm();
			bean.setId(IdUtil.createUUID(22));//权限id
			bean.setNewFlag(1);
		}
		request.setAttribute( "bean", bean );
		//信息列表
		List<AuthPerm> beans=authPermService.findDataTree(null);
		request.setAttribute( "beans", beans );
		return edit;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("authPerm:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="权限信息")
	public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
		log.info("AuthPermController del.........");
		Response result = new Response();
		try {
			AuthPerm bean1=new AuthPerm();
			bean1.setId(id);//权限id
			result.message=authPermService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("result", result);
		
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"authPerm:edit","authPerm:add"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	public String save(@Valid AuthPerm bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
		log.info("AuthPermController save.........");
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
					result.message=authPermService.saveOrUpdateData(bean);
					result.data = bean.getId();
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