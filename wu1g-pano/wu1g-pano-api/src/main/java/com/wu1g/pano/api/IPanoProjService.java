/*	
 * 全景_项目 业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.api;
 

import com.wu1g.pano.vo.PanoProj;

import java.util.List;

/**
 * <p>全景_项目 业务处理接口类。
 * <ol>[功能概要] 
 * <li>编辑(新增or修改)。
 * <li>详情检索。
 * <li>分页检索。
 * <li>列表检索。
 * <li>逻辑删除。
 * <li>物理删除。
 * <li>恢复逻辑删除。
 * 
 * @author easycode
 */
public interface IPanoProjService {

	/**	
	 * <p>信息编辑。
	 * <ol>[功能概要] 	
	 * <li>新增信息。	
	 * <li>修改信息。	
	 * 	
	 *
	 */	
	public String saveOrUpdateData(PanoProj bean) throws Exception;
	/**	
	 * <p>信息编辑。
	 * <ol>[功能概要] 	
	 * <li>物理删除。	
	 * 	
	 *
	 */	
	public String deleteData(PanoProj bean) throws Exception;	
	/**	
	 * <p>信息 单条。
	 * <ol>[功能概要] 	
	 * <li>恢复逻辑删除的数据。	
	 * 	
	 *
	 */	
	public String recoveryDataById(PanoProj bean) throws Exception;	
	/**	
	 * <p>信息 单条。
	 * <ol>[功能概要] 	
	 * <li>逻辑删除。	
	 * 	
	 *
	 */	
	public String deleteDataById(PanoProj bean) throws Exception;	
	/**	
	 * <p>信息列表 分页。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>分页。	
	 * 	
	 *
	 */	
	public List<PanoProj> findDataIsPage(PanoProj bean);	
	/**	
	 * <p>信息列表。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * 	
	 *
	 */	
	public List<PanoProj> findDataIsList(PanoProj bean);	
	/**	
	 * <p>信息详情。
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>详情。	
	 * 	
	 *
	 */	
	public PanoProj findDataById(PanoProj bean);	
	
	/**	
	 * <p>生成全景图。
	 */	
	public void makePano(PanoProj bean);
	
	/**	
	 * <p>保存xml信息
	 */	
	public String saveXmlData(PanoProj bean) throws Exception;	
	/**	
	 * <p>点赞
	 */	
	public String thumbsUpNum(PanoProj bean) throws Exception;
	/**	
	 * <p>浏览量+1
	 */	
	public String pvNum(PanoProj bean) throws Exception;	
	/**	
	 * <p>生成视频文件。
	 */	
	public void makeVideo(PanoProj bean) throws Exception;
}