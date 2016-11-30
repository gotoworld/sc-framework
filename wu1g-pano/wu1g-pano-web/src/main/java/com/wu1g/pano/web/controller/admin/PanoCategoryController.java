/*	
 * 全景_类目 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.web.controller.admin;

import com.wu1g.framework.Response;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import com.wu1g.pano.api.IPanoCategoryService;
import com.wu1g.pano.vo.PanoCategory;
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

import java.util.List;


/**
 * <p>
 * 全景_类目 ACTION类。
 * </p>
 * <ol>
 * [功能概要]
 * <li>初始化。
 * <li>信息列表(未删除)。
 * <li>编辑页面(页面)(新增or修改)。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class PanoCategoryController extends BaseController {

	private static final long				serialVersionUID	= -344702788488841783L;
	/** 全景_类目 业务处理 */
	@Autowired
	private IPanoCategoryService panoCategoryService;

	// 全景_类目
	private static final String				acPrefix			= "/pano01.";
	private static final String				init				= "admin/pano/pano01";
	private static final String				edit				= "admin/pano/pano01_edit";
	private static final String				list				= "admin/pano/pano01_list";
	private static final String				success				= "redirect:/h" + acPrefix + "init";

	/**
	 * <p>
	 * 初始化处理。
	 * <ol>
	 * [功能概要]
	 * <li>初始化处理。
	 * 
	 * @return 转发字符串
	 */
	@RequiresPermissions("pano01:init")
	@RequestMapping(value = acPrefix + "init")
	public String init() {
		log.info( "PanoCategoryController init........." );
		//获取类目列表
		request.setAttribute( "categoryBeans", panoCategoryService.findDataTree(null) );
		return init;
	}
	/**
	 * <p>
	 * 编辑。
	 * </p>
	 * <ol>
	 * [功能概要]
	 * <li>编辑。
	 * 
	 * @return 转发字符串
	 */
	@RequiresPermissions("pano01:edit")
	@RequestMapping(value = acPrefix + "edit/{id}")
	public String edit(PanoCategory bean, @PathVariable("id") String id) {
		log.info( "PanoCategoryController edit........." );
		int pageNum = 0;
		if (bean != null && bean.getPageNum() != null) {
			pageNum = bean.getPageNum();
		}
		if("add".equals( id )){
			id=null;
			bean=null;
		}
		if (ValidatorUtil.notEmpty( id )) {
			if (bean == null) {
				bean = new PanoCategory();
			}
			bean.setId( id );// ID
			bean = panoCategoryService.findDataById( bean );
		}
		if (bean == null) {
			bean = new PanoCategory();
			bean.setId( IdUtil.createUUID( 32 ) );// ID
		}
		bean.setPageNum( pageNum );
		request.setAttribute( "bean", bean );
		return edit;
	}

	/**
	 * <p>
	 * 删除。
	 * <ol>
	 * [功能概要]
	 * <li>逻辑删除。
	 * 
	 * @return 转发字符串
	 */
	@RequiresPermissions("pano01:del")
	@RequestMapping(value = acPrefix + "del/{id}")
	public String del(@PathVariable("id") String id) {
		log.info( "PanoCategoryController del........." );

		// String id=request.getParameter("id");//ID
		PanoCategory bean = new PanoCategory();
		bean.setId( id );// ID
		String msg = "1";
		try {
			msg = panoCategoryService.deleteDataById( bean );
		} catch (Exception e) {
			msg = e.getMessage();
		}
		request.setAttribute( "msg", msg );

		return success;
	}

	/**
	 * <p>
	 * 信息保存
	 * <ol>
	 * [功能概要]
	 * <li>新增。
	 * <li>修改。
	 * 
	 * @return 转发字符串
	 */
	@RequiresPermissions(value = { "pano01:add", "pano01:edit" }, logical = Logical.OR)
	@RequestMapping(value = acPrefix + "save")
	public String save(@Validated @RequestBody PanoCategory bean, BindingResult bindingResult) {
		log.info( "PanoCategoryController save........." );
		if (bean != null) {
			Response result = new Response();
			try {
				if ("1".equals(request.getSession().getAttribute("bussiness_contact_" + bean.getToken()))) {
					throw new RuntimeException("请不要重复提交!");
				} else {
					request.getSession().setAttribute("bussiness_contact_" + bean.getToken(), "1");
				}
				if (bindingResult.hasErrors()) {
					String errorMsg = "";
					List<ObjectError> errorList = bindingResult.getAllErrors();
					for (ObjectError error : errorList) {
						errorMsg += (error.getDefaultMessage()) + ";";
					}
					result = Response.error(errorMsg);
				} else {
					OrgUser user = (OrgUser) request.getSession().getAttribute( CommonConstant.SESSION_KEY_USER );
					if (user != null) {
						bean.setCreateIp( getIpAddr() );
						bean.setCreateId( user.getId() );
						bean.setUpdateIp( getIpAddr() );
						bean.setUpdateId( user.getId() );
					}
					result.message = panoCategoryService.saveOrUpdateData(bean);
					result.data = bean.getId();
				}
			} catch (Exception e) {
				result = Response.error(e.getMessage());
			}
			request.setAttribute( "msg", result );
		} else {
			request.setAttribute( "msg", "信息保存失败!" );
		}
		return success;
	}
}