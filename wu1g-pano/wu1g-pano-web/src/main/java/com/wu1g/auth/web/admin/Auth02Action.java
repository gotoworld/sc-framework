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
package com.wu1g.auth.web.admin;

import com.wu1g.auth.api.IAuthPermService;
import com.wu1g.auth.vo.AuthPerm;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>权限_权限信息  ACTION类。</p>	
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
public class Auth02Action extends BaseController {

	private static final long serialVersionUID = -255059907001339225L;
	private static final transient Logger log = Logger.getLogger(Auth02Action.class);
	/**权限_权限信息 业务处理*/
	@Autowired
	private IAuthPermService authPermService;
	
	//权限_权限信息 管理
	private static final String acPrefix="/auth02.";
	private static final String init = "admin/auth/auth02";
	private static final String edit = "admin/auth/auth02_01";
	private static final String infoList = "admin/auth/auth02_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	/**
	 * <p> 初始化处理。 </p>
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
	 * <p> 编辑。</p>
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
	 * <p> 删除。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authPerm:del")
	@RequestMapping(value = acPrefix+"del/{id}")
	public String del(@PathVariable("id") String id) {
		log.info("Auth02Action del.........");
		AuthPerm bean1=new AuthPerm();
		bean1.setId(id);//权限id
		String msg="1";
		try {
			msg=authPermService.deleteDataById(bean1);
		} catch (Exception e) {
			msg=e.getMessage();
		}
		request.setAttribute("msg",msg);
		
		return success;
	}
//	/**
//	 * <p> 删除。 </p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>物理删除。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authPerm:delph")
//	@RequestMapping(value = acPrefix+"delph/{id}")
//	public String delph(@PathVariable("id") String id) {
//		log.info("Auth02Action del ph.........");
//		AuthPerm bean1=new AuthPerm();
//		bean1.setId(id);//权限id
//		String msg="1";
//		try {
//			msg=authPermService.deleteData(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
	/**
	 * <p> 信息保存 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"authPerm:edit","authPerm:add"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	public String save( AuthPerm bean) {
		log.info("Auth02Action save.........");
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
					msg=authPermService.saveOrUpdateData(bean);
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
//	 * <p> 回收站。</p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>回收站。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authPerm:recycle")
//	@RequestMapping(value = acPrefix+"recycle/{pageNum}")
//	public String recycle( AuthPerm bean) {
//		log.info("Auth02Action recycle.........");
//		bean.setPageSize( CommonConstant.PAGEROW_DEFAULT_COUNT );
//		bean.setDelFlag( "1" );
//		//列表
//		List<AuthPerm> beans=authPermService.findDataIsPage(bean);
//		request.setAttribute("beans",beans);
//		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,beans);
//		return "recycle";
//	}
//	/**
//	 * <p> 恢复。</p>
//	 * <ol>[功能概要] 
//	 * <li>恢复逻辑删除的数据。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("authPerm:recovery")
//	@RequestMapping(value = acPrefix+"recovery/{id}")
//	public String recovery(@PathVariable("id") String id) {
//		log.info("Auth02Action recovery.........");
//		//========创建bena对象=============
//		AuthPerm bean1=new AuthPerm();
//		bean1.setId(id);//权限id
//		String msg="1";
//		try {
//			msg=authPermService.recoveryDataById(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
}