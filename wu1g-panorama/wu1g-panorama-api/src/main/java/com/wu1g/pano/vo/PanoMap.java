/*	
 * 全景_导览图 BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.vo;


import com.wu1g.framework.vo.BaseVO;

import cn.com.baseos.bean.pano.PanoMap;
/**
 * <p>全景_导览图  BEAN类。
 * @author easycode
 */
public class PanoMap   extends BaseVO {

	private static final long serialVersionUID = -499724720850030385L;
	/**项目id */
	private String projId;
	/**场景id */
	private String sceneId;
	/**场景在导览图中雷达角度 */
	private String rotate;
	/**导览图坐标x */
	private String x;
	/**导览图坐标y */
	private String y;
	/**版本号 默认值(0)*/
	private String version;
	/**创建时间 默认值(2000-01-01 00:00:00)*/
	private String dateCreated;
	/**修改时间 默认值(2000-01-01 00:00:00)*/
	private String dateUpdate;

	
	
	/**
	 * 项目id取得
	 * @return 项目id
	 */
	public String getProjId(){
		return projId;
	}
	/**
	 * 项目id设定
	 * @param projId 项目id
	 */
	public void setProjId(String projId){
		this.projId=projId;
	}
	/**
	 * 场景id取得
	 * @return 场景id
	 */
	public String getSceneId(){
		return sceneId;
	}
	/**
	 * 场景id设定
	 * @param sceneId 场景id
	 */
	public void setSceneId(String sceneId){
		this.sceneId=sceneId;
	}
	/**
	 * 场景在导览图中雷达角度取得
	 * @return 场景在导览图中雷达角度
	 */
	public String getRotate() {
	    return rotate;
	}
	/**
	 * 场景在导览图中雷达角度设定
	 * @param rotate 场景在导览图中雷达角度
	 */
	public void setRotate(String rotate) {
	    this.rotate = rotate;
	}
	/**
	 * 导览图坐标x取得
	 * @return 导览图坐标x
	 */
	public String getX(){
		return x;
	}
	/**
	 * 导览图坐标x设定
	 * @param x 导览图坐标x
	 */
	public void setX(String x){
		this.x=x;
	}
	/**
	 * 导览图坐标y取得
	 * @return 导览图坐标y
	 */
	public String getY(){
		return y;
	}
	/**
	 * 导览图坐标y设定
	 * @param y 导览图坐标y
	 */
	public void setY(String y){
		this.y=y;
	}
	/**
	 * 版本号 默认值(0)取得
	 * @return 版本号 默认值(0)
	 */
	public String getVersion(){
		return version;
	}
	/**
	 * 版本号 默认值(0)设定
	 * @param version 版本号 默认值(0)
	 */
	public void setVersion(String version){
		this.version=version;
	}
	/**
	 * 创建时间 默认值(2000-01-01 00:00:00)取得
	 * @return 创建时间 默认值(2000-01-01 00:00:00)
	 */
	public String getDateCreated(){
		return dateCreated;
	}
	/**
	 * 创建时间 默认值(2000-01-01 00:00:00)设定
	 * @param dateCreated 创建时间 默认值(2000-01-01 00:00:00)
	 */
	public void setDateCreated(String dateCreated){
		this.dateCreated=dateCreated;
	}
	/**
	 * 修改时间 默认值(2000-01-01 00:00:00)取得
	 * @return 修改时间 默认值(2000-01-01 00:00:00)
	 */
	public String getDateUpdate(){
		return dateUpdate;
	}
	/**
	 * 修改时间 默认值(2000-01-01 00:00:00)设定
	 * @param dateUpdate 修改时间 默认值(2000-01-01 00:00:00)
	 */
	public void setDateUpdate(String dateUpdate){
		this.dateUpdate=dateUpdate;
	}}