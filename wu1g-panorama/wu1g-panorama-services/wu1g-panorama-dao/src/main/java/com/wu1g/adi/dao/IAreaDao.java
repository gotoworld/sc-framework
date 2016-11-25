/*
 * 接口Dao类  行政区划信息表
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序.发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.adi.dao;

import java.util.List;
import java.util.Map;
/**
 * <p>  行政区划信息表 Dao类 <p>
 */
public interface IAreaDao {
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要] 
	 * <li>信息检索。
	 * <li>列表 省。
	 * </ol>
	 * @return 处理结果
	 */
	public List<Map<String,String>> findDataIsListA(Map<String, String> map);
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要] 
	 * <li>信息检索。
	 * <li>列表 市。
	 * </ol>
	 * @return 处理结果
	 */
	public List<Map<String,String>> findDataIsListB(Map<String, String> map);
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要] 
	 * <li>信息检索。
	 * <li>列表 县。
	 * </ol>
	 * @return 处理结果
	 */
	public List<Map<String,String>> findDataIsListC(Map<String, String> map);
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要] 
	 * <li>信息检索。
	 * <li>列表 镇。
	 * </ol>
	 * @return 处理结果
	 */
	public List<Map<String,String>> findDataIsListD(Map<String, String> map);
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要] 
	 * <li>信息检索。
	 * <li>列表村。
	 * </ol>
	 * @return 处理结果
	 */
	public List<Map<String,String>> findDataIsListE(Map<String, String> map);
	
}