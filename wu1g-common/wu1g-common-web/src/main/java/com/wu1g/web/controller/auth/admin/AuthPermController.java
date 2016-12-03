/*	
 * 权限_权限信息  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.auth.admin;

import com.wu1g.auth.api.IAuthPermService;
import com.wu1g.auth.vo.AuthPerm;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

/**
 * <p>权限_权限信息  ACTION类。
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
public class AuthPermController extends BaseController {
	private static final long serialVersionUID = -255059907001339225L;
	/**权限_权限信息 业务处理*/
	@Autowired
	private IAuthPermService authPermService;
	
	//权限_权限信息 管理
	private static final String acPrefix="/auth02/";
	private static final String init = "admin/auth/auth02";
	private static final String edit = "admin/auth/auth02_01";
	private static final String list = "admin/auth/auth02_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	/**
	 * <p> 初始化处理。
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authPerm:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("Auth02Action init.........");
		//信息列表
		List<AuthPerm> beans=authPermService.findDataTree(null);
		request.setAttribute( "beans", beans );
		return init;
	}
	/**
	 * <p> 编辑。
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authPerm:edit")
	@RequestMapping(value = acPrefix+"edit/{id}")
	public String edit( AuthPerm bean,@PathVariable("id") String id) {
		log.info("Auth02Action edit.........");
		if(ValidatorUtil.notEmpty(id)){
			AuthPerm bean1=new AuthPerm();
			bean1.setId(id);//权限id
			bean=authPermService.findDataById(bean1);
		}
		if(bean==null){
			bean=new AuthPerm();
			bean.setId(IdUtil.createUUID(22));//权限id
		}
		request.setAttribute( "bean", bean );
		//信息列表
		List<AuthPerm> beans=authPermService.findDataTree(null);
		request.setAttribute( "beans", beans );
		return edit;
	}
	/**
	 * <p> 删除。
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authPerm:del")
	@RequestMapping(value = acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="权限信息")
	public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
		log.info("Auth02Action del.........");
		Response result = new Response();
		try {
			AuthPerm bean1=new AuthPerm();
			bean1.setId(id);//权限id
			result.message=authPermService.deleteDataById(bean1);
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
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"authPerm:edit","authPerm:add"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	public String save(@Validated  AuthPerm bean, RedirectAttributesModelMap modelMap, BindingResult bindingResult) {
		log.info("Auth02Action save.........");
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
		modelMap.addFlashAttribute("msg", result);
		return success;
	}
}