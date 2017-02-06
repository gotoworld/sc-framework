/*	
 * 组织架构_用户  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.admin.org;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wu1g.api.auth.IAuthRoleService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.api.org.IOrgDepartmentService;
import com.wu1g.api.org.IOrgUserService;
import com.wu1g.vo.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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

	private static final long serialVersionUID = -847589285730427889L;
	/**组织架构_用户 业务处理*/
	@Autowired
	private IOrgUserService orgUserService;
	/**权限_角色信息 业务处理*/
	@Autowired
	private IAuthRoleService authRoleService;
	/**组织架构_部门 业务处理*/
	@Autowired
	private IOrgDepartmentService orgDepartmentService;
	
	//组织架构_用户 管理
	private static final String acPrefix="/org/user/";
	private static final String init = "admin/org/org_user";
	private static final String edit = "admin/org/org_user_edit";
	private static final String list = "admin/org/org_user_list";
	private static final String editUser = "admin/org/org_user_myinfo";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("OrgUserController init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。
	 * <ol>
	 * [功能概要] 
	 * <li>信息列表。
	 * </ol>
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(value=acPrefix+"list")
	public String list( OrgUser bean) {
		log.info("OrgUserController list.........");
		if(bean==null){
			bean = new OrgUser();
		}
		bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
		//信息列表
		PageInfo<?> page=new PageInfo<>(orgUserService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return list;
	}
	/**
	 * <p> 编辑。
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgUser:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit( OrgUser bean,@PathVariable("id") String id) {
		log.info("OrgUserController edit.........");
		int pageNum = 0;
		if(bean!=null && bean.getPageNum()!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			OrgUser bean1=new OrgUser();
			bean1.setId(id);
			bean=orgUserService.findDataById(bean1);
			if(bean!=null){
				if(!"admin".equals(bean.getUserid())){
					//获取当前用户的角色集合
					request.setAttribute("myRoleBeans",orgUserService.findRoleDataIsList(bean));
				}
				//获取用户所在部门集合
				request.setAttribute("deptBeans",orgUserService.findDeptDataIsList(bean));
			}
		}
		if(bean==null||"add".equals(id)){
			bean=new OrgUser();
			bean.setId(IdUtil.createUUID(32));
            bean.setNewFlag("1");
		}
		bean.setPageNum(pageNum);
		request.setAttribute( "bean", bean );
		if(!"admin".equals(bean.getUserid())){
			//所有角色集合
			request.setAttribute("roleBeans", authRoleService.findDataIsList(null));
		}
		//所以部门集合
		request.setAttribute("deptTree", orgDepartmentService.findDataTree(null));
		return edit;
	}
	/**
	 * <p> 编辑。
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequestMapping(value=acPrefix+"editUser/{id}")
	public String editUser(@PathVariable("id") String id) {
		log.info("OrgUserController editUser.........");
		if(ValidatorUtil.notEmpty(id)){
			OrgUser bean=new OrgUser();
			bean.setId(id);
			bean=orgUserService.findDataById(bean);
			request.setAttribute( "bean", bean );
		}
		return editUser;
	}
	/**
	 * <p> 删除。
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgUser:del")
	@RequestMapping(value=acPrefix+"del/{id}")
	public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
		log.info("OrgUserController del.........");
		Response result = new Response();
		try {
			OrgUser user = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
			if(user.getId().equals(id)){
				throw new RuntimeException("不能删除自己!");
			}
			OrgUser bean1=new OrgUser();
			bean1.setId(id);
			result.message=orgUserService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("msg", result);
		return success;
	}
	/**判断用户id是否存在
	 * @throws IOException */
	@RequestMapping(value=acPrefix+"isUidYN/{uid}")
	public String isUidYN(@PathVariable("uid") String uid) throws IOException{
		String msg="true";
		JSONObject json = new JSONObject();
	    PrintWriter out=response.getWriter();
	    response.setContentType("application/json");
		try {
			msg=orgUserService.isUidYN(uid);
		} catch (Exception e) {
			//msg=e.getMessage();
		}
		 if("0".equals(msg)){
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
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"orgUser:add","orgUser:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	public String save(@Validated OrgUser bean,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
		log.info("OrgUserController save.........");
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
					OrgUser user = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
					if(user!=null){
						if(ValidatorUtil.isEmpty(bean.getUserid())){
							bean.setUserid(user.getUserid());
						}
					}
					result.message=orgUserService.saveOrUpdateData(bean);
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
	/**
	 * <p> 信息保存
	 * <ol>
	 * [功能概要] 
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 * @throws IOException 
	 */
	@RequestMapping(value=acPrefix+"update")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户信息")
	public String update(@Validated  OrgUser bean,BindingResult bindingResult,RedirectAttributesModelMap modelMap) throws IOException {
		log.info("OrgUserController save.........");
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
					OrgUser user = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
					if(user!=null){
						if(ValidatorUtil.isEmpty(bean.getUserid())){
							bean.setUserid(user.getUserid());
						}
					}
					result.message=orgUserService.updateData(bean);
					request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
				}
			} catch (Exception e) {
				result = Response.error(e.getMessage());
			}
		} else {
			result = Response.error("信息保存失败!");
		}
		modelMap.addFlashAttribute("msg", result);
		response.getWriter().print(result);
		return null;
	}
}