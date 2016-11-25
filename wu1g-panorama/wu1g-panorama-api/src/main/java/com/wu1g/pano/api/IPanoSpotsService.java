/*	
 * 全景_热点 业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.api;
 

import java.util.List;

import cn.com.baseos.bean.pano.PanoSpots;
/**
 * <p>全景_热点 业务处理接口类。
 * <ol>[功能概要] 
 * <li>编辑(新增or修改)。
 * <li>列表检索。
 * <li>物理删除。
 * 
 * @author easycode
 */
public interface IPanoSpotsService {

	/**	
	 * <p>信息编辑。
	 * <ol>[功能概要] 	
	 * <li>新增信息。	
	 * <li>修改信息。	
	 * 	
	 * @return 处理结果	
	 */	
	public String saveOrUpdateData(PanoSpots bean) throws Exception;	
	/**	
	 * <p>信息编辑。
	 * <ol>[功能概要] 	
	 * <li>物理删除。	
	 * 	
	 * @return 处理结果	
	 */	
	public String deleteData(PanoSpots bean) throws Exception;	
	/**	
	 * <p>信息列表。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * 	
	 * @return 处理结果	
	 */	
	public List<PanoSpots> findDataIsList(PanoSpots bean);	
}