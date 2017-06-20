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
package com.vr.web.controller.admin.org;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.vr.api.auth.IAuthRoleService;
import com.vr.api.org.IOrgDeptService;
import com.vr.api.org.IOrgUserService;
import com.vr.framework.Response;
import com.vr.framework.annotation.ALogOperation;
import com.vr.framework.annotation.RfAccount2Bean;
import com.vr.framework.util.CommonConstant;
import com.vr.framework.util.ValidatorUtil;
import com.vr.vo.org.OrgUser;
import com.vr.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.InvalidSessionException;
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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>组织架构_用户  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class OrgUserController extends BaseController {
	@Autowired
	private IOrgUserService orgUserService;
	@Autowired
	private IAuthRoleService authRoleService;
	@Autowired
	private IOrgDeptService orgDepartmentService;
	
	//组织架构_用户 管理
	private static final String acPrefix="/org/user/";
	private static final String init = "admin/org/org_user";
	private static final String edit = "admin/org/org_user_edit";
	private static final String list = "admin/org/org_user_list";
	private static final String editUser = "admin/org/org_user_myinfo";
	private static final String editPwd = "admin/org/org_user_editpwd";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("OrgUserController init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"list")
	public String list( OrgUser bean) {
		log.info("OrgUserController list.........");
		if(bean==null){
			bean = new OrgUser();
		}
		//信息列表
		PageInfo<?> page=new PageInfo<>(orgUserService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return list;
	}
	/**
	 * <p> 编辑。
	 */
	@RequiresPermissions("orgUser:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(OrgUser bean, @PathVariable("id") Long id) {
		log.info("OrgUserController edit.........");
		int pageNum= getPageNum(bean);
		if(0!=id){
			OrgUser bean1=new OrgUser();
			bean1.setId(id);
			bean=orgUserService.findDataById(bean1);
			if(bean!=null){
				if(!"admin".equals(bean.getAccid())){
					//获取当前用户的角色集合
					request.setAttribute("myRoleBeans",orgUserService.findRoleDataIsList(bean));
				}
				//获取用户所在部门集合
				request.setAttribute("deptBeans",orgUserService.findDeptDataIsList(bean));
			}
		}
		if(bean==null||0==id){
			bean=new OrgUser();
            bean.setNewFlag(1);
		}
		bean.setPageNum(pageNum);
		request.setAttribute( "bean", bean );
		if(!"1".equals(getAuth().getSession().getAttribute("isSuper"))){
			//所有角色集合
			request.setAttribute("roleBeans", authRoleService.findDataIsList(null));
		}
		//所以部门集合
		request.setAttribute("deptTree", orgDepartmentService.findDataTree(null));
		return edit;
	}
	/**
	 * <p> 编辑。
	 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"editUser/{id}")
	public String editUser(@PathVariable("id") Long id) {
		log.info("OrgUserController editUser.........");
		if(0!=id){
			OrgUser bean=new OrgUser();
			bean.setId(id);
			bean=orgUserService.findDataById(bean);
			request.setAttribute( "bean", bean );
		}
		return editUser;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("orgUser:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
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
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**判断用户id是否存在
	 * @throws IOException */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"isUidYN/{uid}")
	public String isUidYN(@PathVariable("uid") String uid) throws IOException{
		String result="true";
		JSONObject json = new JSONObject();
	    PrintWriter out=response.getWriter();
	    response.setContentType("application/json");
		try {
			result=orgUserService.isUidYN(uid);
		} catch (Exception e) {
			//result=e.getMessage();
		}
		 if("0".equals(result)){
			 try {
	            json.put("valid",true);
	         } catch (JSONException e) {}
	    }else {
	        try {
	            json.put("valid",false);
	        } catch (JSONException e) {}
	    }
	    out.print(json);
		return null;
	}
	
	
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"orgUser:add","orgUser:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	public String save(@Validated OrgUser bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
		log.info("OrgUserController save.........");
		Response result = new Response();
		if(bean!=null){
			try {
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
					result.message=orgUserService.saveOrUpdateData(bean);
					result.data = bean.getId();
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
		} else {
			result = Response.error("信息保存失败!");
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
//	@RequiresPermissions(value={"orgUser:edit"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"update")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户信息")
	public String update(@Validated OrgUser bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) throws IOException {
		log.info("OrgUserController save.........");
		Response result = new Response();
		if(bean!=null){
			try {
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
		} else {
			result = Response.error("信息保存失败!");
		}
		modelMap.addFlashAttribute("result", result);
		response.getWriter().print(result);
		return null;
	}
	/**
	 * <p> 密码修改。
	 */
	@RequiresPermissions(value={"orgUser:editPwd"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"editPwd/{id}")
	public String editPwd(@PathVariable("id") Long id) {
		log.info("OrgUserController editPwd.........");
		if(0!=id){
			OrgUser bean=new OrgUser();
			bean.setId(id);
			bean=orgUserService.findDataById(bean);
			request.setAttribute( "bean", bean );
		}
		return editPwd;
	}
	/**
	 * <p> 密码修改
	 */
	@RequiresPermissions(value={"orgUser:editPwd"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"updatePwd")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户密码")
	@ResponseBody
	public Response updatePwd(@Validated OrgUser bean) throws IOException {
		log.info("OrgUserController updatePwd.........");
		if(bean==null||ValidatorUtil.isNullEmpty(bean.getOldpwd())||ValidatorUtil.isNullEmpty(bean.getNewpwd())||ValidatorUtil.isNullEmpty(bean.getConfirmpwd())) return Response.error("参数异常!");

		Response result = new Response();
		try {
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