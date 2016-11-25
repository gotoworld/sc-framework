/*
 * 行政区划多级联动 Action
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 softvan  System. - All Rights Reserved.
 *
 */
package com.wu1g.adi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.baseos.service.adi.IAreaService;
import cn.com.baseos.web.action.BaseAction;
/**
 * <p>controller 行政区划多级联动</p>
 * <ol>[提供机能]
 * <li>行政区划多级联动<li>
 * </ol>
 */
public class AreaAction extends BaseAction {
	private static final long serialVersionUID = -6103432072290645133L;
	private static final transient Logger log = Logger.getLogger(AreaAction.class);
	/** 行政区划 */
	private IAreaService areaService;
	/** 默认的构造函数 */
	public AreaAction() {
		log.info("AreaAction constructed");
	}
	/**
	 * 省 列表
	 */
	public String a() throws Exception {
		log.info("AreaAction A");
		List<Map<String,String>> list= areaService.findDataIsListA(null);
		getWriter().print(outStr(list));
		return null;
	}

	/**
	 * 市 列表
	 */
	public String  b() throws Exception {

		log.info("AreaAction B");
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", request.getParameter("id"));
		List<Map<String,String>> list= areaService.findDataIsListB(map);
		getWriter().print(outStr(list));
		return null;
	}

	/**
	 * 县 列表
	 */
	public String c() throws Exception {
		log.info("AreaAction C");
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", request.getParameter("id"));
		List<Map<String,String>> list= areaService.findDataIsListC(map);
		getWriter().print(outStr(list));
		return null;
	}
	/**
	 * 镇 列表
	 */
	public String d() throws Exception {
		log.info("AreaAction D");
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", request.getParameter("id"));
		List<Map<String,String>> list= areaService.findDataIsListD(map);
		getWriter().print(outStr(list));
		return null;
	}
	/**
	 * 村 列表
	 */
	public String e() throws Exception {
		log.info("AreaAction E");
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", request.getParameter("id"));
		List<Map<String,String>> list= areaService.findDataIsListE(map);
		getWriter().print(outStr(list));
		return null;
	}
	/**
	 * select . option 封装 id name
	 * @param list
	 * @return
	 */
	private StringBuffer outStr(List<Map<String,String>> list){
		StringBuffer outStr = new StringBuffer("");
		 if(list!=null){
			 for(Map<String,String> areaMap:list){
				 outStr.append("<option value=\"" + areaMap.get("id") + "\">"+ areaMap.get("name") + "</option>");
			 }
		}
		return outStr;
	}
	/**
	 * 行政区划取得
	 * @return 行政区划
	 */
	public IAreaService getAreaService() {
	    return areaService;
	}
	/**
	 * 行政区划设定
	 * @param areaService 行政区划
	 */
	public void setAreaService(IAreaService areaService) {
	    this.areaService = areaService;
	}
}
