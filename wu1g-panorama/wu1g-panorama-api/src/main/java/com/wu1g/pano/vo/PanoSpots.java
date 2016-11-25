/*	
 * 全景_热点 BEAN类	
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

import cn.com.baseos.bean.pano.PanoSpots;
/**
 * <p>全景_热点  BEAN类。
 * @author easycode
 */
public class PanoSpots   extends BaseVO {

	private static final long serialVersionUID = -734160071374181202L;
	/**id */
	private String id;
	/**项目id */
	private String projId;
	/**场景id */
	private String sceneId;
	/**类型0热点1图片 默认值(0)*/
	private String htype;
	/**标题 */
	private String title;
	/**热点编号 */
	private String hname;
	/**水平坐标 */
	private String ath;
	/**垂直坐标 */
	private String atv;
	/**关联场景 */
	private String linkedscene;
	/**缩放比 */
	private String scale;
	/**纵深 */
	private String depth;
	/**旋转角度 */
	private String rotate;
	/**图片地址 */
	private String url;
	/**可否点击 0否1是 */
	private String isOnclick;
	/**版本号 默认值(0)*/
	private String version;
	/**创建时间 默认值(2000-01-01 00:00:00)*/
	private String dateCreated;
	/**修改时间 默认值(2000-01-01 00:00:00)*/
	private String dateUpdate;

	/**
	 * 类型0热点1图片 默认值(0)取得
	 * @return 类型0热点1图片 默认值(0)
	 */
	public String getHtype(){
		return htype;
	}
	/**
	 * 类型0热点1图片 默认值(0)设定
	 * @param htype 类型0热点1图片 默认值(0)
	 */
	public void setHtype(String htype){
		this.htype=htype;
	}
	/**
	 * 场景id 设定 
	 * @param sceneId场景id
	 */
	public void setSceneId(String sceneId){
		this.sceneId=sceneId;
	}
	/**
	 * id取得
	 * @return id
	 */
	public String getId(){
		return id;
	}
	/**
	 * id设定
	 * @param id id
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * 项目id取得
	 * @return 项目id
	 */
	public String getProjId() {
	    return projId;
	}
	/**
	 * 项目id设定
	 * @param projId 项目id
	 */
	public void setProjId(String projId) {
	    this.projId = projId;
	}
	/**
	 * 场景id 取得 
	 * @return 场景id
	 */
	public String getSceneId(){
		return sceneId;
	}
	/**
	 * 标题取得
	 * @return 标题
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * 标题设定
	 * @param title 标题
	 */
	public void setTitle(String title){
		this.title=title;
	}
	/**
	 * 热点编号取得
	 * @return 热点编号
	 */
	public String getHname(){
		return hname;
	}
	/**
	 * 热点编号设定
	 * @param hname 热点编号
	 */
	public void setHname(String hname){
		this.hname=hname;
	}
	/**
	 * 水平坐标取得
	 * @return 水平坐标
	 */
	public String getAth(){
		return ath;
	}
	/**
	 * 水平坐标设定
	 * @param ath 水平坐标
	 */
	public void setAth(String ath){
		this.ath=ath;
	}
	/**
	 * 垂直坐标取得
	 * @return 垂直坐标
	 */
	public String getAtv(){
		return atv;
	}
	/**
	 * 垂直坐标设定
	 * @param atv 垂直坐标
	 */
	public void setAtv(String atv){
		this.atv=atv;
	}
	/**
	 * 关联场景取得
	 * @return 关联场景
	 */
	public String getLinkedscene(){
		return linkedscene;
	}
	/**
	 * 关联场景设定
	 * @param linkedscene 关联场景
	 */
	public void setLinkedscene(String linkedscene){
		this.linkedscene=linkedscene;
	}
	/**
	 * 缩放比取得
	 * @return 缩放比
	 */
	public String getScale(){
		return scale;
	}
	/**
	 * 缩放比设定
	 * @param scale 缩放比
	 */
	public void setScale(String scale){
		this.scale=scale;
	}
	/**
	 * 纵深取得
	 * @return 纵深
	 */
	public String getDepth(){
		return depth;
	}
	/**
	 * 纵深设定
	 * @param depth 纵深
	 */
	public void setDepth(String depth){
		this.depth=depth;
	}
	/**
	 * 旋转角度取得
	 * @return 旋转角度
	 */
	public String getRotate(){
		return rotate;
	}
	/**
	 * 旋转角度设定
	 * @param rotate 旋转角度
	 */
	public void setRotate(String rotate){
		this.rotate=rotate;
	}
	/**
	 * 图片地址取得
	 * @return 图片地址
	 */
	public String getUrl(){
		return url;
	}
	/**
	 * 图片地址设定
	 * @param url 图片地址
	 */
	public void setUrl(String url){
		this.url=url;
	}
	/**
	 * 可否点击 0否1是取得
	 * @return 可否点击 0否1是
	 */
	public String getIsOnclick() {
	    return isOnclick;
	}
	/**
	 * 可否点击 0否1是设定
	 * @param isOnclick 可否点击 0否1是
	 */
	public void setIsOnclick(String isOnclick) {
	    this.isOnclick = isOnclick;
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