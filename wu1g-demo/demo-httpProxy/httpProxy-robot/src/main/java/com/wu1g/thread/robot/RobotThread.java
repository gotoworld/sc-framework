/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016年5月3日  wxg      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 httpProxy  System. - All Rights Reserved.
 *
 */
package com.wu1g.thread.robot;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wu1g.bean.robot.BaseSiteBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wu1g.service.robot.IBaseFileManager;
import com.wu1g.service.robot.IBaseInfoManager;

/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
public class RobotThread implements Runnable {
	private static transient Logger		log					= LogManager.getLogger( RobotThread.class );

	/**
	 * 线程池
	 */
	private ExecutorService				executor;
	/** 采集结果 业务处理 */
	private IBaseInfoManager			baseInfoManager;
	/** 附件 业务处理 */
	private IBaseFileManager			baseFileManager;
	/** 站点信息bean */
	private BaseSiteBean siteBean;
	// /** 域名 */
	// private String myDomain;
	/** 网址网页深度 */
	private Map<String, Integer>		deepUrls			= new Hashtable<String, Integer>();
	/** 存放已经处理过的Url集合及摘要 */
	private Map<String, String>			urlSet				= new Hashtable<String, String>();
	/** 爬虫访问多线程标记 0不开启多线程 1开启多线程(容易被封IP) */
	private String						ROBOTS_THREAD_FLAG;
	/** 爬虫获取信息间隔时间(单位 seconds秒) */
	private int							ROBOTS_THREAD_SLEEP;
	/** 站点总链接map */
	public static Map<String, Integer>	siteUrlAllCountMap	= new Hashtable<String, Integer>();
	/** 站点已处理链接 */
	public static Map<String, Integer>	siteUrlOKCountMap	= new Hashtable<String, Integer>();
	/** 站点执行开关map 0关1开 */
	public static Map<String, Integer>	siteRunSwitcMap		= new Hashtable<String, Integer>();
	/** 存放客户端请求的报文头 */
	protected HashMap<String, String>	requestHeader;

	/**
	 * 有参 构造器
	 * 
	 * @param _ROBOTS_THREAD_FLAG爬虫访问多线程标记
	 *            0不开启多线程 1开启多线程(容易被封IP)
	 * @param _ROBOTS_THREAD_SLEEP
	 *            爬虫获取信息间隔时间(单位 ms)
	 * @param _siteBean
	 *            站点信息bean
	 * @param _executor
	 *            线程池
	 * @param _baseInfoManager
	 *            爬虫结果 业务处理
	 * @param _baseFileManager
	 *            附件 业务处理
	 */
	public RobotThread(String _ROBOTS_THREAD_FLAG, int _ROBOTS_THREAD_SLEEP, BaseSiteBean _siteBean, ExecutorService _executor, HashMap<String, String> _requestHeader, IBaseInfoManager _baseInfoManager, IBaseFileManager _baseFileManager) {
		ROBOTS_THREAD_FLAG = _ROBOTS_THREAD_FLAG;
		ROBOTS_THREAD_SLEEP = _ROBOTS_THREAD_SLEEP;
		siteBean = _siteBean;
		executor = _executor;
		if (executor == null) {
			executor = Executors.newCachedThreadPool();
		}
		/// ** 存放客户端请求的报文头 */
		requestHeader = _requestHeader;

		baseInfoManager = _baseInfoManager;
		baseFileManager = _baseFileManager;

		log.info( "开始爬取============" + siteBean.getUrl() );

		// TODO
		// if (siteBean != null && "1".equals( siteBean.getType() )) {
		// try {
		// siteBean.setConfigJsonBean( JsonUtils.jsonToBean2( siteBean.getConfig() ) );
		// } catch (Exception e) {
		// log.error( "严重错误!配置信息解析错误!", e );
		// }
		// }
	}

	/**
	 * 执行线程
	 */
	public void run() {
		// TODO 线程
		try {
			if (" js jpg png bmp json css gif svg mov avi mv mtv mkv ".contains(parseSuffix(siteBean.getUrl()).toLowerCase())) {
				log.error( "爬虫访问!" + siteBean.getUrl() + " 不是html放弃爬取!" );
				return;
			}
			// 首页深度
			deepUrls.put( siteBean.getUrl(), 1 );
			// 先把超链接从队列中取出来,然后从头部删除。 //判断该url没有访问
			executor.execute( new HtmlThread( ROBOTS_THREAD_FLAG, ROBOTS_THREAD_SLEEP, siteBean.getUrl(), deepUrls, urlSet, siteBean, executor, requestHeader, null, null, baseInfoManager, baseFileManager ) );
		} catch (Exception e) {
			// TODO 问题
			log.error( "爬虫访问!" + siteBean.getUrl() + "失败!", e );
		}
	}

	final static Pattern pattern = Pattern.compile( "\\S*[?|#]\\S*" );
	/**
	 * 获取链接的后缀名
	 * 
	 * @return
	 */
	private static String parseSuffix(String url) {
		Matcher matcher = pattern.matcher( url );
		String[] spUrl = url.toString().split( "/" );
		int len = spUrl.length;
		String endUrl = spUrl[ len - 1 ];
		if (matcher.find()) {
			String[] spEndUrl = endUrl.split( "\\?|#" );
			if(spEndUrl[ 0 ].indexOf( "." )!=-1){
				return spEndUrl[ 0 ].split( "\\." )[ 1 ];
			}else{
				return spEndUrl[ 0 ];
			}
			
		}
		if(endUrl.indexOf( "." )!=-1){
			return endUrl.split( "\\." )[ 1 ];
		}else{
			return endUrl;
		}
		
	}
	//test
	public static void main(String[] args) {
		System.out.println(( parseSuffix( "http://wu1g119.iteye.com/admin.js?a=2#6" )) );
	}
}