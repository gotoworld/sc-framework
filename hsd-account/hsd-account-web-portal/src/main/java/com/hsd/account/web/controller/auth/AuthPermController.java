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
package com.hsd.account.web.controller.auth;

import com.hsd.account.api.auth.IAuthPermService;
import com.hsd.account.vo.auth.AuthPerm;
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

@Api(description = "权限_权限信息")
@RestController
@Slf4j
public class AuthPermController extends BaseController {

	private static final String acPrefix="/api/account/auth/perm/";

	@Autowired
	private IAuthPermService authPermService;
	/**
	 * <p> 信息树json。
	 */
	@RequiresPermissions("authPerm:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
	@ApiOperation(value = "信息树")
	@ResponseBody
	public Response jsonTree() {
		log.info("AuthPermController jsonTree.........");
		Response result = new Response(0,"seccuss");
		try {
			result.data=authPermService.findDataTree(null);
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
	public Response info(@PathVariable("id") String id) {
		log.info("AuthPermController info.........");
		Response result = new Response();
		try {
			AuthPerm bean=new AuthPerm();
			bean.setId(id);
			result.data=authPermService.findDataById(bean);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("authPerm:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="权限信息")
	@ApiOperation(value = "逻辑删除")
	public Response del(@PathVariable("id") String id) {
		log.info("AuthPermController del.........");
		Response result = new Response(0,"seccuss");
		try {
			AuthPerm bean1=new AuthPerm();
			bean1.setId(id);//权限id
			result.message=authPermService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"authPerm:edit","authPerm:add"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	@ApiOperation(value = "信息保存")
	public Response save(@Validated AuthPerm bean, BindingResult bindingResult) {
		log.info("AuthPermController save.........");
		Response result = null;
		if(bean==null) result = Response.error("参数异常!");
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
				result=authPermService.saveOrUpdateData(bean);
				request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
			}
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
}