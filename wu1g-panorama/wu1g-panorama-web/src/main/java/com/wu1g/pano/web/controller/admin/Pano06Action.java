/*	
 * 全景_评论 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.web.controller.admin;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import cn.com.baseos.bean.org.OrgUser;
import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.common.IdUtil;
import cn.com.baseos.common.ValidatorUtil;
import cn.com.baseos.web.action.BaseAction;
import com.github.pagehelper.PageInfo;

import cn.com.baseos.bean.pano.PanoComments;
import cn.com.baseos.service.pano.IPanoCommentsService;

/**
 * <p>
 * 全景_评论 ACTION类。
 * </p>
 * <ol>
 * [功能概要]
 * <li>初始化。
 * <li>信息列表(未删除)。
 * <li>编辑页面(页面)(新增or修改)。
 * 
 * @author easycode
 */
@Controller
@RequestMapping(value = "/h")
public class Pano06Action extends BaseAction {

	private static final long				serialVersionUID	= -893683902158611114L;
	private static final transient Logger	log					= Logger.getLogger( Pano06Action.class );
	/** 全景_评论 业务处理 */
	@Autowired
	private IPanoCommentsService			panoCommentsService;

	// 全景_评论
	private static final String				acPrefix			= "/pano06.";
	private static final String				init				= "admin/pano/pano06";
//	private static final String				edit				= "admin/pano/pano06_edit";
	private static final String				list				= "admin/pano/pano06_list";
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
	@RequiresPermissions("pano06:init")
	@RequestMapping(value = acPrefix + "init")
	public String init() {
		log.info( "Pano06Action init........." );
		return init;
	}

	/**
	 * <p>
	 * 信息列表 (未删除)。
	 * <ol>
	 * [功能概要]
	 * <li>信息列表。
	 * 
	 * @return 转发字符串
	 */
	@RequiresPermissions("pano06:init")
	@RequestMapping(value = acPrefix + "list")
	public String list(PanoComments bean) {
		log.info( "Pano06Action list........." );
		if (bean == null) {
			bean = new PanoComments();
		}
		bean.setPageSize( CommonConstant.PAGEROW_DEFAULT_COUNT );
		// 信息列表
		PageInfo<?> page = new PageInfo<>( panoCommentsService.findDataIsPage( bean ) );
		request.setAttribute( "beans", page.getList() );
		// 分页对象-JSP标签使用-
		request.setAttribute( CommonConstant.PAGEROW_OBJECT_KEY, page );
		return list;
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
	@RequiresPermissions("pano06:del")
	@RequestMapping(value = acPrefix + "del/{id}")
	public String del(@PathVariable("id") String id,RedirectAttributesModelMap modelMap) {
		log.info( "Pano06Action del........." );

		PanoComments bean = new PanoComments();
		bean.setId( id );// id
		String msg = "1";
		try {
			msg = panoCommentsService.deleteDataById( bean );
		} catch (Exception e) {
			msg = e.getMessage();
		}
		modelMap.addFlashAttribute( "msg", msg );

		return success;
	}
}