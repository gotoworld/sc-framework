package com.wu1g.pano.web.controller.client;

import com.alibaba.fastjson.JSON;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.pano.api.IPanoCommentsService;
import com.wu1g.pano.api.IPanoProjService;
import com.wu1g.pano.vo.PanoComments;
import com.wu1g.pano.vo.PanoProj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 全景_视频 ACTION类。
 */
@Controller
@Slf4j
public class PanoVideoController extends BaseController {
	private static final long				serialVersionUID	= -528422099490438672L;
	@Autowired
	private IPanoProjService panoProjService;
	@Autowired
	private IPanoCommentsService panoCommentsService;
	// 全景_视频
	private static final String				acPrefix			= "/s/video.";

	@RequestMapping(value = acPrefix+"thumbsUpNum")
	@ResponseBody
	public String thumbsUpNum() {
		log.info( "PanoVideoController thumbsUpNum........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//视频id
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
		log.info( "PanoVideoController getComment........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//视频id
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
		log.info( "PanoVideoController addComment........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//视频id
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
		log.info( "PanoVideoController pvNum........." );
		Map msg=new HashMap();
		msg.put( "status", 1 );
		try {
			String pid=request.getParameter("pid");//视频id
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
	@RequestMapping(value = "/video/{id}")
	public String video(@PathVariable("id") String id) {
		log.info( "PanoVideoController video........." );
		PanoProj bean=new PanoProj();
		bean.setId( id );
		bean.setType( "1" );
		PanoProj proj=panoProjService.findDataById( bean );
		request.setAttribute("proj", proj );
		request.setAttribute("basePath", getBasePath() );
		request.setAttribute("panoPath", getBasePath()+"/plugins/krpano/" );
		return "client/pano/video";
	}
}