/*	
 * 全景_视频 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.05      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import com.wu1g.pano.api.IPanoCategoryService;
import com.wu1g.pano.api.IPanoProjService;
import com.wu1g.pano.api.IPanoSceneService;
import com.wu1g.pano.vo.PanoProj;
import com.wu1g.pano.vo.PanoScene;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 全景_视频 ACTION类。
 */
@Controller
@Slf4j
public class VideoController extends BaseController {
	private static final long				serialVersionUID	= -528422099490438672L;
	/** 全景_项目 业务处理 */
	@Autowired
	private IPanoProjService panoProjService;
	@Autowired
	private IPanoCategoryService panoCategoryService;
	@Autowired
	private IPanoSceneService panoSceneService;
	// 全景_项目
	private static final String				acPrefix			= "/h/video/";
	private static final String				init				= "admin/pano/video";
	private static final String				edit				= "admin/pano/video_edit";
	private static final String				list				= "admin/pano/video_list";
	private static final String				success				= "redirect:" + acPrefix + "init";

	/**
	 * <p>
	 * 初始化处理。
	 * <ol>
	 * [功能概要]
	 * <li>初始化处理。
	 * 
	 *
	 */
	@RequiresPermissions("video:init")
	@RequestMapping(value = acPrefix + "init")
	public String init() {
		log.info( "PanoVideoController init........." );
		return init;
	}
	/**
	 * <p>
	 * 信息列表 (未删除)。
	 * <ol>
	 * [功能概要]
	 * <li>信息列表。
	 * 
	 *
	 */
	@RequiresPermissions("video:init")
	@RequestMapping(value = acPrefix + "list")
	public String list(PanoProj bean) {
		log.info( "PanoVideoController list........." );
		if (bean == null) {
			bean = new PanoProj();
		}
		bean.setType( "1" );
		bean.setPageSize( CommonConstant.PAGEROW_DEFAULT_COUNT );
		// 信息列表
		PageInfo<?> page = new PageInfo<>( panoProjService.findDataIsPage( bean ) );
		request.setAttribute( "beans", page.getList() );
		// 分页对象-JSP标签使用-
		request.setAttribute( CommonConstant.PAGEROW_OBJECT_KEY, page );
		return list;
	}

	/**
	 * <p>
	 * 编辑。
	 *
	 * <ol>
	 * [功能概要]
	 * <li>编辑。
	 * 
	 *
	 */
	@RequiresPermissions("video:edit")
	@RequestMapping(value = acPrefix + "edit/{id}")
	public String edit(PanoProj bean, @PathVariable("id") String id) {
		log.info( "PanoVideoController edit........." );
		int pageNum = 1;
		if (bean != null && bean.getPageNum() != null) {
			pageNum = bean.getPageNum();
		}
		if("add".equals( id )){
			id=null;
			bean=null;
		}
		if (ValidatorUtil.notEmpty( id )) {
			if (bean == null) {
				bean = new PanoProj();
			}
			bean.setId( id );// id
			bean = panoProjService.findDataById( bean );
			if(bean!=null){
				PanoScene scene=new PanoScene();
				scene.setProjId( bean.getId() );
				bean.setScenes(panoSceneService.findDataIsList( scene ));
			}
		}
		if (bean == null) {
			bean = new PanoProj();
			bean.setId( IdUtil.createUUID( 32 ) );// id
		}
		bean.setPageNum( pageNum );
		bean.setToken(IdUtil.createUUID(32));
		request.setAttribute( "bean", bean );
		
		//获取类目列表
		request.setAttribute( "categoryBeans", panoCategoryService.findDataTree(null) );
		
		return edit;
	}

	/**
	 * <p>
	 * 删除。
	 * <ol>
	 * [功能概要]
	 * <li>逻辑删除。
	 * 
	 *
	 */
	@RequiresPermissions("video:del")
	@RequestMapping(value = acPrefix + "del/{id}")
	@ALogOperation(type="删除",desc="全景视频信息")
	public String del(@PathVariable("id") String id,RedirectAttributesModelMap modelMap) {
		log.info( "PanoVideoController del........." );
		Response result = new Response();
		try {
			PanoProj bean = new PanoProj();
			bean.setId( id );// id
			result.message = panoProjService.deleteDataById( bean );
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("msg", result);
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
	 *
	 */
	@RequiresPermissions(value = { "video:add", "video:edit" }, logical = Logical.OR)
	@RequestMapping(method = {RequestMethod.POST},value = acPrefix + "save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="全景视频信息")
	public String save(@Validated @RequestBody PanoProj bean, RedirectAttributesModelMap modelMap, BindingResult bindingResult) {
		log.info( "PanoVideoController save........." );
		Response result = new Response();
		if (bean != null) {
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
				} else {
					List<PanoScene> scenes=new ArrayList<PanoScene>();
					String[] scene_id_arr=request.getParameterValues( "scene_id" );
					if(scene_id_arr!=null && scene_id_arr.length>0){
						for(int i=0;i<scene_id_arr.length;i++){
							String scene_id=scene_id_arr[i];
							PanoScene scene=new PanoScene();
							scene.setId( scene_id );
							scene.setProjId( bean.getId() );
							scene.setOrderNo(i );
							scene.setSceneSrc( request.getParameter( scene_id+"_scene_src" ) );
							scene.setSceneTitle(request.getParameter( scene_id+"_scene_tit" ) );
							
							scene.setCreateIp( getIpAddr() );
							scene.setCreateId( bean.getId() );
							scene.setUpdateIp( getIpAddr() );
							scene.setUpdateId( bean.getId() );
							
							scenes.add( scene );
						}
					}
					bean.setScenes( scenes );
					result.message = panoProjService.saveOrUpdateData( bean );
					result.data = bean.getId();

					bean.setStr( getBasePath() );
					panoProjService.makeVideo( bean );

					request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
				}
				result.data = bean.getId();
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
	} else {
		result = Response.error("信息保存失败!");
	}
		modelMap.addFlashAttribute("msg", result );
		return success;
	}
}