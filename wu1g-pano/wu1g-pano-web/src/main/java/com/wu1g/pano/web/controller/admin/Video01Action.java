/*	
 * 全景_视频 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.05      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.web.controller.admin;

import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 全景_视频 ACTION类。
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
@Slf4j
public class Video01Action extends BaseController {
	private static final long				serialVersionUID	= -528422099490438672L;
	/** 全景_项目 业务处理 */
	@Autowired
	private IPanoProjService panoProjService;
	@Autowired
	private IPanoCategoryService panoCategoryService;
	@Autowired
	private IPanoSceneService panoSceneService;
	// 全景_项目
	private static final String				acPrefix			= "/h/video.";
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
	 * @return 转发字符串
	 */
	@RequiresPermissions("video:init")
	@RequestMapping(value = acPrefix + "init")
	public String init() {
		log.info( "VideoAction init........." );
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
	@RequiresPermissions("video:init")
	@RequestMapping(value = acPrefix + "list")
	public String list(PanoProj bean) {
		log.info( "VideoAction list........." );
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
	 * </p>
	 * <ol>
	 * [功能概要]
	 * <li>编辑。
	 * 
	 * @return 转发字符串
	 */
	@RequiresPermissions("video:edit")
	@RequestMapping(value = acPrefix + "edit/{id}")
	public String edit(PanoProj bean, @PathVariable("id") String id) {
		log.info( "VideoAction edit........." );
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
	 * @return 转发字符串
	 */
	@RequiresPermissions("video:del")
	@RequestMapping(value = acPrefix + "del/{id}")
	public String del(@PathVariable("id") String id,RedirectAttributesModelMap modelMap) {
		log.info( "VideoAction del........." );
		PanoProj bean = new PanoProj();
		bean.setId( id );// id
		String msg = "1";
		try {
			msg = panoProjService.deleteDataById( bean );
		} catch (Exception e) {
			msg = e.getMessage();
		}
		modelMap.addFlashAttribute( "msg", msg );
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
	@RequiresPermissions(value = { "video:add", "video:edit" }, logical = Logical.OR)
	@RequestMapping(value = acPrefix + "save")
	public String save(PanoProj bean,RedirectAttributesModelMap modelMap) {
		log.info( "VideoAction save........." );
		if (bean != null) {
			String msg = "1";
			try {
				// TODO--请加入验证字段--
				if (ValidatorUtil.isEmpty( "需要验证的字段" )) {
					msg = "保存失败!信息为空!";
				} else {
					OrgUser user = (OrgUser) request.getSession().getAttribute( CommonConstant.SESSION_KEY_USER );
					if (user != null) {
						bean.setCreateIp( getIpAddr() );
						bean.setCreateId( user.getId() );
						bean.setUpdateIp( getIpAddr() );
						bean.setUpdateId( user.getId() );
					}
					List<PanoScene> scenes=new ArrayList<PanoScene>();
					String[] scene_id_arr=request.getParameterValues( "scene_id" );
					if(scene_id_arr!=null && scene_id_arr.length>0){
						for(int i=0;i<scene_id_arr.length;i++){
							String scene_id=scene_id_arr[i];
							PanoScene scene=new PanoScene();
							scene.setId( scene_id );
							scene.setProjId( bean.getId() );
							scene.setOrderNo(""+ i );
							scene.setSceneSrc( request.getParameter( scene_id+"_scene_src" ) );
							scene.setSceneTitle(request.getParameter( scene_id+"_scene_tit" ) );
							
							scene.setCreateIp( getIpAddr() );
							scene.setCreateId( user.getId() );
							scene.setUpdateIp( getIpAddr() );
							scene.setUpdateId( user.getId() );
							
							scenes.add( scene );
						}
					}
					bean.setScenes( scenes );
					msg = panoProjService.saveOrUpdateData( bean );
					
					bean.setStr( getBasePath() );
					panoProjService.makeVideo( bean );
				}
			} catch (Exception e) {
				msg = e.getMessage();
			}
			modelMap.addFlashAttribute( "msg", msg );
		} else {
			modelMap.addFlashAttribute( "msg", "信息保存失败!" );
		}
		return success;
	}
}