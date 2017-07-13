/*	
 * 组织架构_用户  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.hsd.account.web.controller.org;

import com.github.pagehelper.PageInfo;
import com.hsd.account.api.auth.IAuthRoleService;
import com.hsd.account.api.org.IOrgDeptService;
import com.hsd.account.api.org.IOrgUserService;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.vo.org.OrgUser;
import com.hsd.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.util.List;

@Api(description = "组织架构_用户")
@RestController
@Slf4j
public class OrgUserController extends BaseController {
	private static final String acPrefix="/boss/account/org/user/";

	@Autowired
	private IOrgUserService orgUserService;
	@Autowired
	private IAuthRoleService authRoleService;

	@Autowired
	private IOrgDeptService orgDepartmentService;

	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page")
	@ApiOperation(value = "信息分页")
	public Response page(@RequestBody OrgUser bean) {
		log.info("OrgUserController page.........");
		Response result = new Response();
		try {
			if (bean == null)throw new RuntimeException("参数异常");
			PageInfo<?> page=new PageInfo<>(orgUserService.findDataIsPage(bean));
			result.data=getPageDto(page);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 获取当前用户的角色集合。
	 */
	@RequiresPermissions("orgUser:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"myRoles/{id}")
	@ApiOperation(value = "获取当前用户的角色集合")
	public Response myRoles(@PathVariable("id") Long id) {
		log.info("OrgUserController myRoles.........");
		Response result = new Response();
		try {
			OrgUser bean=new OrgUser();
			bean.setId(id);
			bean=orgUserService.findDataById(bean);
			if(bean==null)throw new RuntimeException("用户不存在!");
			result.data=orgUserService.findRoleDataIsList(bean);
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
		log.info("OrgUserController info.........");
		Response result = new Response();
		try {
			OrgUser bean=new OrgUser();
			bean.setId(id);
			result.data=orgUserService.findDataById(bean);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("orgUser:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ApiOperation(value = "逻辑删除")
	public Response del(@PathVariable("id") Long id) {
		log.info("OrgUserController del.........");
		Response result = new Response();
		try {
			OrgUser orgUser = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
			if(orgUser.getId().equals(id)){
				throw new RuntimeException("不能删除自己!");
			}
			OrgUser bean1=new OrgUser();
			bean1.setId(id);
			result.message=orgUserService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**判断用户id是否存在 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"isUidYN/{uid}")
	@ApiOperation(value = "判断用户id是否存在")
	public Response isUidYN(@PathVariable("uid") String uid) throws IOException{
		Response result = new Response();
		try {
			result.data=orgUserService.isUidYN(uid);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"orgUser:add","orgUser:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	@ApiOperation(value = "信息保存")
	public Response save(@Validated OrgUser bean, BindingResult bindingResult) {
		log.info("OrgUserController save.........");
		Response result = new Response();
		try {
			if (bean == null)throw new RuntimeException("参数异常");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + bean.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			if (bindingResult.hasErrors()) {
				String errorresult = "";
				List<ObjectError> errorList = bindingResult.getAllErrors();
				for (ObjectError error : errorList) {
					errorresult += (error.getDefaultMessage()) + ";";
				}
				result = Response.error(errorresult);
			}else{
				OrgUser orgUser = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
				if(orgUser !=null){
					if(ValidatorUtil.isEmpty(bean.getAccid())){
						bean.setAccid(orgUser.getAccid());
					}
				}
				if(null==bean.getEnable()) bean.setEnable(0);
				result=orgUserService.saveOrUpdateData(bean);
				try {
					if(bean.getId()==getUser().getId()) {
						getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER, bean);
						getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER_ADMIN, bean);
					}
				} catch (InvalidSessionException e) {
				}
				request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
			}
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息修改
	 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"update")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户信息")
	@ApiOperation(value = "信息修改")
	public Response update(@Validated OrgUser bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) throws IOException {
		log.info("OrgUserController update.........");
		Response result = new Response();
		try {
			if (bean == null)throw new RuntimeException("参数异常");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + bean.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			if (bindingResult.hasErrors()) {
				String errorresult = "";
				List<ObjectError> errorList = bindingResult.getAllErrors();
				for (ObjectError error : errorList) {
					errorresult += (error.getDefaultMessage()) + ";";
				}
				result = Response.error(errorresult);
			}else{
				OrgUser orgUser = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
				if(orgUser !=null){
					if(ValidatorUtil.isEmpty(bean.getAccid())){
						bean.setAccid(orgUser.getAccid());
					}
				}
				result.message=orgUserService.updateData(bean);
				try {
					OrgUser newOrgUser=orgUserService.findDataById(bean);
					getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER,newOrgUser);
					getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER_ADMIN,newOrgUser);
				} catch (InvalidSessionException e) {
				}
				request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
			}
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 密码修改
	 */
	@RequiresPermissions(value={"orgUser:editPwd"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"updatePwd")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户密码")
	@ResponseBody
	@ApiOperation(value = "密码修改")
	public Response updatePwd(@Validated OrgUser bean) throws IOException {
		log.info("OrgUserController updatePwd.........");
		Response result = new Response();
		try {
			if(bean==null||ValidatorUtil.isNullEmpty(bean.getOldpwd())||ValidatorUtil.isNullEmpty(bean.getNewpwd())||ValidatorUtil.isNullEmpty(bean.getConfirmpwd())) return Response.error("参数异常!");
			if(!bean.getNewpwd().equals(bean.getConfirmpwd()))throw new RuntimeException("两次密码不一致!");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "updatePwd." + bean.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			OrgUser orgUser = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
			if(orgUser ==null){
				throw new RuntimeException("登陆超时!");
			}
			result.message=orgUserService.updatePwd(bean);
			request.getSession().setAttribute(acPrefix + "updatePwd." + bean.getToken(), "1");
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
}