/*	
 * 全景_项目 ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.04      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.web.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.baseos.bean.pano.PanoComments;
import cn.com.baseos.bean.pano.PanoMap;
import cn.com.baseos.bean.pano.PanoProj;
import cn.com.baseos.bean.pano.PanoScene;
import cn.com.baseos.common.ValidatorUtil;
import cn.com.baseos.service.pano.IPanoCommentsService;
import cn.com.baseos.service.pano.IPanoProjService;
import cn.com.baseos.service.pano.IPanoSceneService;
import cn.com.baseos.web.action.BaseAction;

/**
 * <p>
 * 全景_项目 ACTION类。
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
public class PanoAction extends BaseAction {
	private static final long				serialVersionUID	= -528422099490438672L;
	private static final transient Logger	log					= Logger.getLogger( PanoAction.class );
	@Autowired
	private IPanoProjService				panoProjService;
	@Autowired
	private IPanoSceneService panoSceneService;
	@Autowired
	private IPanoCommentsService panoCommentsService;
	// 全景_项目
	private static final String				acPrefix			= "/s/pano.";

	@RequestMapping(value = acPrefix+"thumbsUpNum")
	@ResponseBody
	public String thumbsUpNum() {
		log.info( "PanoAction thumbsUpNum........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//项目id
			PanoProj bean=new PanoProj();
			bean.setId( pid );
			panoProjService.thumbsUpNum(bean );
			
			bean=panoProjService.findDataById( bean );
			if(bean!=null){
				msg.put( "count", bean.getThumbsUpNum() );
			}else{
				msg.put( "count",0 );
			}
		} catch (Exception e) {
			log.error( "点赞失败!", e );
			msg=new HashMap();
			msg.put( "status", 0 );
			msg.put( "msg", e.getMessage() );
		}
		return JSON.toJSONString( msg );
	}
	@RequestMapping(value = acPrefix+"getComment")
	@ResponseBody
	public String getComment() {
		log.info( "PanoAction getComment........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//项目id
			String sceneId=request.getParameter( "sceneId" );//场景id
			PanoComments bean=new PanoComments();
			bean.setSceneId( sceneId );
			List beans=panoCommentsService.findDataIsList(bean );
			if(beans==null||beans.size()==0){
				throw new RuntimeException();
			}
			msg.put( "list",beans);
		} catch (Exception e) {
			log.error( "点赞失败!", e );
			msg=new HashMap();
			msg.put( "status", 0 );
			msg.put( "msg", e.getMessage() );
		}
		return JSON.toJSONString( msg );
	}
	@RequestMapping(value = acPrefix+"addComment")
	@ResponseBody
	public String addComment() {
		log.info( "PanoAction addComment........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//项目id
			String sceneId=request.getParameter( "sceneId" );//场景id
			String ath=request.getParameter( "ath" );//水平坐标
			String atv=request.getParameter( "atv" );//垂直坐标
			String content=request.getParameter( "content" );//评论内容
			
			if(ValidatorUtil.isNullEmpty( pid )
					||ValidatorUtil.isNullEmpty( sceneId )
					||ValidatorUtil.isNullEmpty( ath )
					||ValidatorUtil.isNullEmpty( atv )
					||ValidatorUtil.isNullEmpty( content )){
				throw new RuntimeException();
			}
			
			PanoComments bean=new PanoComments();
			bean.setSceneId( sceneId );
			bean.setAth( ath );
			bean.setAtv( atv );
			bean.setContent( content );
			bean.setCreateIp( getIpAddr() );
			bean.setUpdateIp( getIpAddr() );
			panoCommentsService.saveOrUpdateData(bean);
		} catch (Exception e) {
			log.error( "评论失败!", e );
			msg=new HashMap();
			msg.put( "status", 0 );
			msg.put( "msg", e.getMessage() );
		}
		return JSON.toJSONString( msg );
	}
	@RequestMapping(value = acPrefix+"pvNum")
	@ResponseBody
	public String pvNum() {
		log.info( "PanoAction pvNum........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//项目id
			PanoProj bean=new PanoProj();
			bean.setId( pid );
			panoProjService.pvNum(bean );
			
			bean=panoProjService.findDataById( bean );
			if(bean!=null){
				msg.put( "count", bean.getPvNum());
			}else{
				msg.put( "count",0 );
			}
		} catch (Exception e) {
			log.error( "增加浏览量失败!", e );
			msg=new HashMap();
			msg.put( "status", 0 );
			msg.put( "msg", e.getMessage() );
		}
		return JSON.toJSONString( msg );
	}
	@RequestMapping(value = "/tour/{id}")
	public String tour(@PathVariable("id") String id) {
		log.info( "PanoAction tour........." );
		PanoProj bean=new PanoProj();
		bean.setId( id );
		bean.setType( "0" );
		PanoProj proj=panoProjService.findDataById( bean );
		request.setAttribute("proj", proj );
		request.setAttribute("basePath", getBasePath() );
		request.setAttribute("panoPath", getBasePath()+"/static/plugins/krpano/" );
		return "client/pano/tour";
	}
}