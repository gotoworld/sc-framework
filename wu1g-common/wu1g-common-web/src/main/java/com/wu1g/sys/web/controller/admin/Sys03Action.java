/*	
 * 系统_数据字典  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.sys.web.controller.admin;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import com.wu1g.sys.api.IVariableService;
import com.wu1g.sys.vo.SysVariable;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

/**
 * <p>系统_数据字典  ACTION类。</p>
 * <ol>[功能概要] 
 * <li>初始化。 
 * <li>信息列表(未删除)。 
 * <li>编辑页面(页面)(新增or修改)。 
 * <li>信息保存(功能)(新增or修改)。 
 * <li>预览(页面)。 
 * <li>回收站(页面)。 
 * <li>逻辑删除(功能)。 
 * <li>物理删除(功能)。 
 * <li>恢复逻辑删除(功能)。 
 *</ol> 
 * @author easycode
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class Sys03Action extends BaseController {

	private static final long serialVersionUID = -184287954681953050L;
	@Autowired
	protected IVariableService variableService;
	//系统_数据字典 管理
	private static final String acPrefix="/sys03.";
	private static final String init = "admin/sys/sys03";
	private static final String edit = "admin/sys/sys03_01";
	private static final String infoList = "admin/sys/sys03_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("Sys03Action init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>信息列表。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(value=acPrefix+"infoList")
	public String infoList(SysVariable bean) {
		log.info("Sys03Action infoList.........");
		if(bean==null){
			bean = new SysVariable();
		}
		PageInfo<?> page=new PageInfo<>(variableService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象-JSP标签使用-
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return infoList;
	}
	/**
	 * <p> 编辑。</p>
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("sysDic:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit(SysVariable bean,@PathVariable("id") String id) {
		log.info("Sys03Action edit.........");
		int pageNum = 0;
		if(bean!=null && bean.getPageNum()!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			SysVariable bean1=new SysVariable();
			bean1.setId(id);//ID
			bean=variableService.findDataById(bean1);
		}
		if(bean==null){
			bean=new SysVariable();
			bean.setId(IdUtil.createUUID(22));//ID
		}
		bean.setPageNum( pageNum );
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
	@RequiresPermissions("sysDic:del")
	@RequestMapping(value=acPrefix+"del/{id}")
	public String del(@PathVariable("id") String id) {
		log.info("Sys03Action del.........");
		SysVariable bean1=new SysVariable();
		bean1.setId(id);//ID
		String msg="1";
		try {
			msg=variableService.deleteDataById(bean1);
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
//	@RequiresPermissions("sysDic:delph")
//	public String delph() {
//		log.info("Sys03Action del ph.........");
//		String id=request.getParameter("id");
//		SysVariable bean1=new SysVariable();
//		bean1.setId(id);//ID
//		String msg="1";
//		try {
//			msg=variableService.deleteData(bean1);
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
	@RequiresPermissions(value={"sysDic:add","sysDic:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	public String save(SysVariable bean) {
		log.info("Sys03Action save.........");
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
					msg=variableService.saveOrUpdateData(bean);
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
//	 * <p> 预览。</p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>预览。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("sysDic:view")
//	public String view() {
//		log.info("Sys03Action view.........");
//		String id=request.getParameter("id");
//		if(ValidatorUtil.notEmpty(id)){
//			SysVariable bean1=new SysVariable();
//			bean1.setId(id);//ID
//			bean=variableService.findDataById(bean1);
//		}
//		return "view";
//	}
//	/**
//	 * <p> 回收站。</p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>回收站。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("sysDic:recycle")
//	public String recycle() {
//		log.info("Sys03Action recycle.........");
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
//		SysVariable bean1 = new SysVariable();
//		bean1.setPageInfo(page);
//		//已删除
//		//TODO 未找到逻辑删除标记 字段
//		//列表
//		List<SysVariable> beans=variableService.findDataIsPage(bean1);
//		request.setAttribute("beans",beans);
//		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
//		return "recycle";
//	}
//	/**
//	 * <p> 恢复。</p>
//	 * <ol>[功能概要] 
//	 * <li>恢复逻辑删除的数据。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("sysDic:recovery")
//	public String recovery() {
//		log.info("Sys03Action recovery.........");
//		String id=request.getParameter("id");
//		//========创建bena对象=============
//		SysVariable bean1=new SysVariable();
//		bean1.setId(id);//ID
//		String msg="1";
//		try {
//			msg=variableService.recoveryDataById(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
}