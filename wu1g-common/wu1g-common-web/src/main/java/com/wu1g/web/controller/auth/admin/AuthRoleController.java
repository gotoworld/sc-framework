/*	
 * 权限_角色信息  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.auth.admin;

import com.github.pagehelper.PageInfo;
import com.wu1g.account.api.IAuthPermService;
import com.wu1g.account.api.IAuthRoleService;
import com.wu1g.account.vo.AuthRole;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>权限_角色信息  ACTION类。
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
public class AuthRoleController extends BaseController {

	private static final long serialVersionUID = -250454793558646875L;
	/**权限_角色信息 业务处理*/
	@Autowired
	private IAuthRoleService authRoleService;
	/**权限_权限信息 业务处理*/
	@Autowired
	private IAuthPermService authPermService;
	
	//权限_角色信息 管理
	private static final String acPrefix="/auth03.";
	private static final String init = "admin/auth/auth03";
	private static final String edit = "admin/auth/auth03_01";
	private static final String infoList = "admin/auth/auth03_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	/**
	 * <p> 初始化处理。
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authRole:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("AuthRoleController init.........");
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
	@RequiresPermissions("authRole:menu")
	@RequestMapping(value=acPrefix+"infoList")
	public String infoList(AuthRole bean) {
		log.info("AuthRoleController infoList.........");
		if(bean==null){
			bean = new AuthRole();
		}
		//每页显示条数
		bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
		//信息列表
		PageInfo<?> page =new PageInfo<>(authRoleService.findDataIsPage(bean));
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
	@RequiresPermissions("authRole:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit(AuthRole bean,@PathVariable("id") String id){
		log.info("AuthRoleController edit.........");
		int pageNum = 0;
		if(bean!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			AuthRole bean1=new AuthRole();
			bean1.setId(id);//角色ID
			bean=authRoleService.findDataById(bean1);
			if(bean!=null){
				//获取当前角色所有的功能
				Map xdto=new HashMap();
				xdto.put("roleId", bean.getId());
				request.setAttribute("myPermList", authPermService.findPermDataIsListByRoleId(xdto));
			}
		}
		if(bean==null){
			bean=new AuthRole();
			bean.setId(IdUtil.createUUID(22));//角色ID
		}
		bean.setPageNum( pageNum );
		
		request.setAttribute("bean",bean);
		//权限信息树
		request.setAttribute("permTree",authPermService.findDataTree(null));
		
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
	@RequiresPermissions("authRole:del")
	public String del(@PathVariable("id") String id) {
		log.info("AuthRoleController del.........");
		AuthRole bean1=new AuthRole();
		bean1.setId(id);//角色ID
		String msg="1";
		try {
			msg=authRoleService.deleteDataById(bean1);
		} catch (Exception e) {
			msg=e.getMessage();
		}
		request.setAttribute("msg",msg);
		
		return success;
	}
//	/**
//	 * <p> 删除。
//	 * <ol>
//	 * [功能概要] 
//	 * <li>物理删除。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authRole:deph")
//	public String delph(@PathVariable("id") String id) {
//		log.info("AuthRoleController del ph.........");
//		AuthRole bean1=new AuthRole();
//		bean1.setId(id);//角色ID
//		String msg="1";
//		try {
//			msg=authRoleService.deleteData(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
	/**
	 * <p> 信息保存
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"authRole:add","authRole:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	public String save(AuthRole bean) {
		log.info("AuthRoleController save.........");
		if(bean!=null){
			String msg="1";
			try {
				if(ValidatorUtil.isEmpty(bean.getName())){
					msg="保存失败!信息为空!";
				}else{
					OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
					if(user!=null){
						bean.setCreateIp(getIpAddr());
						bean.setCreateId(user.getId());
						bean.setUpdateIp(getIpAddr());
						bean.setUpdateId(user.getId());
					}
					msg=authRoleService.saveOrUpdateData(bean);
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
//	/**
//	 * <p> 预览。
//	 * <ol>
//	 * [功能概要] 
//	 * <li>预览。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authRole:view")
//	public String view() {
//		log.info("AuthRoleController view.........");
//		String id=request.getParameter("id");
//		if(ValidatorUtil.notEmpty(id)){
//			AuthRole bean1=new AuthRole();
//			bean1.setId(id);//角色ID
//			bean=authRoleService.findDataById(bean1);
//		}
//		return "view";
//	}
//	/**
//	 * <p> 回收站。
//	 * <ol>
//	 * [功能概要] 
//	 * <li>回收站。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authRole:recycle")
//	public String recycle(AuthRole bean) {
//		log.info("AuthRoleController recycle.........");
//		int offset = 0;
//		// 分页偏移量
//		if (!ValidatorUtil.isNullEmpty(request.getParameter("offset"))
//				&& ValidatorUtil.isNum(request.getParameter("offset"))) {
//			offset = Integer.parseInt(request.getParameter("offset"));
//		}
//		PageInfo page = new PageInfo(); 
//		//当前页
//		page.setCurrOffset(offset);
//		//每页显示条数
//		page.setPageRowCount(15);
//		AuthRole bean1 = new AuthRole();
//		bean1.setPageInfo(page);
//		//已删除
//		//TODO 未找到逻辑删除标记 字段
//		//列表
//		List<AuthRole> beans=authRoleService.findDataIsPage(bean1);
//		request.setAttribute("beans",beans);
//		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
//		return "recycle";
//	}
//	/**
//	 * <p> 恢复。
//	 * <ol>[功能概要] 
//	 * <li>恢复逻辑删除的数据。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authRole:recovery")
//	public String recovery() {
//		log.info("AuthRoleController recovery.........");
//		String id=request.getParameter("id");
//		//========创建bena对象=============
//		AuthRole bean1=new AuthRole();
//		bean1.setId(id);//角色ID
//		String msg="1";
//		try {
//			msg=authRoleService.recoveryDataById(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
}