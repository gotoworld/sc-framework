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
package com.wu1g.web.controller.org.admin;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wu1g.account.api.IAuthRoleService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.api.IOrgDepartmentService;
import com.wu1g.org.api.IOrgUserService;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>组织架构_用户  ACTION类。
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
	private static final String acPrefix="/org02.";
	private static final String init = "admin/org/org02";
	private static final String edit = "admin/org/org02_01";
	private static final String infoList = "admin/org/org02_list";
	private static final String editUser = "admin/org/org02_04";
	private static final String success = "redirect:/h"+acPrefix+init;
	
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
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(value=acPrefix+"infoList")
	public String infoList( OrgUser bean) {
		log.info("OrgUserController infoList.........");
		if(bean==null){
			bean = new OrgUser();
		}
		bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
		//信息列表
		PageInfo<?> page=new PageInfo<>(orgUserService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象-JSP标签使用-
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return infoList;
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
			bean1.setId(id);//ID
			bean=orgUserService.findDataById(bean1);
			if(bean!=null){
				if(!"admin".equals(bean.getUserid())){
					//获取当前用户的角色集合
					request.setAttribute("myRoleBeans",orgUserService.findRoleDataIsList(bean));
				}
				//获取用户所在部门集合
				request.setAttribute("myDeptBeans",orgUserService.findDeptDataIsList(bean));
			}
		}
		if(bean==null){
			bean=new OrgUser();
			bean.setId(IdUtil.createUUID(32));//ID
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
			bean.setId(id);//ID
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
	public String del(@PathVariable("id") String id) {
		log.info("OrgUserController del.........");
		OrgUser bean1=new OrgUser();
		bean1.setId(id);//ID
		String msg="1";
		try {
			msg=orgUserService.deleteDataById(bean1);
		} catch (Exception e) {
			msg=e.getMessage();
		}
		request.setAttribute("msg",msg);
		
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
	public String save( OrgUser bean) {
		log.info("OrgUserController save.........");
		if(bean!=null){
			String msg="1";
			try {
				if(ValidatorUtil.isEmpty(bean.getName())){
					msg="保存失败!信息为空!";
				}else{
					OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
					if(user!=null){
						if(ValidatorUtil.isEmpty(bean.getUserid())){
							bean.setUserid(user.getUserid());
						}
						bean.setCreateIp(getIpAddr());
						bean.setCreateId(user.getId());
						bean.setUpdateIp(getIpAddr());
						bean.setUpdateId(user.getId());
					}
					msg=orgUserService.saveOrUpdateData(bean);
				}
			} catch (Exception e) {
				msg=e.getMessage();
			}
			request.setAttribute("msg",msg);
		}else{
			request.setAttribute("msg", "信息保存失败!");
		}
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
	public String update( OrgUser bean) throws IOException {
		log.info("OrgUserController save.........");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String msg="1";
		if(bean!=null){
			try {
				if(ValidatorUtil.isEmpty(bean.getName())){
					msg="保存失败!信息为空!";
				}else{
					OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
					if(user!=null){
						if(ValidatorUtil.isEmpty(bean.getUserid())){
							bean.setUserid(user.getUserid());
						}
						bean.setCreateIp(getIpAddr());
						bean.setCreateId(user.getId());
						bean.setUpdateIp(getIpAddr());
						bean.setUpdateId(user.getId());
					}
					msg=orgUserService.updateData(bean);
				}
			} catch (Exception e) {
				msg=e.getMessage();
			}
		}else{
			msg="信息保存失败!";
		}
		response.getWriter().print(msg);
		return null;
	}
}