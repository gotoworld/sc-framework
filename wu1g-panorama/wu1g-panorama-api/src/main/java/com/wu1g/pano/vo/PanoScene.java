/*	
 * 全景_场景 BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.vo;


import java.util.List;

import com.wu1g.framework.vo.BaseVO;
/**
 * <p>全景_场景  BEAN类。
 * @author easycode
 */
public class PanoScene   extends BaseVO {

	private static final long serialVersionUID = -586813007296179232L;
	/**id */
	private String id;
	/**项目id */
	private String projId;
	/**场景名称 */
	private String sceneTitle;
	/**全景图宽高比须为2:1，格式为jpg,全景视频为mp4 */
	private String sceneSrc;
	/**水平视角 */
	private String hlookat;
	/**垂直视角 */
	private String vlookat;
	/**场景音乐 */
	private String soundSrc;
	/**场景视频解说  */
	private String videoSrc;
	/**版本号 默认值(0)*/
	private String version;
	/**状态 */
	private String state;
	/**备注 */
	private String memo;
	/**排序 */
	private String orderNo;
	/**关键字 */
	private String keyword;
	/**是否删除(0否1是) 默认值(0)*/
	private String delFlag;
	/**创建时间 默认值(2000-01-01 00:00:00)*/
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**修改时间 默认值(2000-01-01 00:00:00)*/
	private String dateUpdate;
	/**修改者ID */
	private String updateId;
	/**修改者IP */
	private String updateIp;

	/**全景分解图地址*/
	private String breakdownImg;
	/** 场景跳转热点 */
	private List<PanoSpots> hotSpots;
	/** 场景装饰图热点 */
	private List<PanoSpots> imgSpots;
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
	 * 场景名称取得
	 * @return 场景名称
	 */
	public String getSceneTitle(){
		return sceneTitle;
	}
	/**
	 * 场景名称设定
	 * @param sceneTitle 场景名称
	 */
	public void setSceneTitle(String sceneTitle){
		this.sceneTitle=sceneTitle;
	}
	/**
	 * 全景图宽高比须为2:1，格式为jpg,全景视频为mp4取得
	 * @return 全景图宽高比须为2:1，格式为jpg,全景视频为mp4
	 */
	public String getSceneSrc(){
		return sceneSrc;
	}
	/**
	 * 全景图宽高比须为2:1，格式为jpg,全景视频为mp4设定
	 * @param sceneSrc 全景图宽高比须为2:1，格式为jpg,全景视频为mp4
	 */
	public void setSceneSrc(String sceneSrc){
		this.sceneSrc=sceneSrc;
	}
	/**
	 * 水平视角取得
	 * @return 水平视角
	 */
	public String getHlookat(){
		if(hlookat==null){
			hlookat="0";
		}
		return hlookat;
	}
	/**
	 * 水平视角设定
	 * @param hlookat 水平视角
	 */
	public void setHlookat(String hlookat){
		this.hlookat=hlookat;
	}
	/**
	 * 垂直视角取得
	 * @return 垂直视角
	 */
	public String getVlookat(){
		if(vlookat==null){
			vlookat="0";
		}
		return vlookat;
	}
	/**
	 * 垂直视角设定
	 * @param vlookat 垂直视角
	 */
	public void setVlookat(String vlookat){
		this.vlookat=vlookat;
	}
	/**
	 * 场景音乐取得
	 * @return 场景音乐
	 */
	public String getSoundSrc() {
	    return soundSrc;
	}
	/**
	 * 场景音乐设定
	 * @param soundSrc 场景音乐
	 */
	public void setSoundSrc(String soundSrc) {
	    this.soundSrc = soundSrc;
	}
	/**
	 * 场景视频解说取得
	 * @return 场景视频解说
	 */
	public String getVideoSrc() {
	    return videoSrc;
	}
	/**
	 * 场景视频解说设定
	 * @param videoSrc 场景视频解说
	 */
	public void setVideoSrc(String videoSrc) {
	    this.videoSrc = videoSrc;
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
	 * 状态取得
	 * @return 状态
	 */
	public String getState(){
		return state;
	}
	/**
	 * 状态设定
	 * @param state 状态
	 */
	public void setState(String state){
		this.state=state;
	}
	/**
	 * 备注取得
	 * @return 备注
	 */
	public String getMemo(){
		return memo;
	}
	/**
	 * 备注设定
	 * @param memo 备注
	 */
	public void setMemo(String memo){
		this.memo=memo;
	}
	/**
	 * 排序取得
	 * @return 排序
	 */
	public String getOrderNo(){
		return orderNo;
	}
	/**
	 * 排序设定
	 * @param orderNo 排序
	 */
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	/**
	 * 关键字取得
	 * @return 关键字
	 */
	public String getKeyword(){
		return keyword;
	}
	/**
	 * 关键字设定
	 * @param keyword 关键字
	 */
	public void setKeyword(String keyword){
		this.keyword=keyword;
	}
	/**
	 * 是否删除(0否1是) 默认值(0)取得
	 * @return 是否删除(0否1是) 默认值(0)
	 */
	public String getDelFlag(){
		return delFlag;
	}
	/**
	 * 是否删除(0否1是) 默认值(0)设定
	 * @param delFlag 是否删除(0否1是) 默认值(0)
	 */
	public void setDelFlag(String delFlag){
		this.delFlag=delFlag;
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
	 * 建立者ID取得
	 * @return 建立者ID
	 */
	public String getCreateId(){
		return createId;
	}
	/**
	 * 建立者ID设定
	 * @param createId 建立者ID
	 */
	public void setCreateId(String createId){
		this.createId=createId;
	}
	/**
	 * 建立者IP取得
	 * @return 建立者IP
	 */
	public String getCreateIp(){
		return createIp;
	}
	/**
	 * 建立者IP设定
	 * @param createIp 建立者IP
	 */
	public void setCreateIp(String createIp){
		this.createIp=createIp;
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
	}
	/**
	 * 修改者ID取得
	 * @return 修改者ID
	 */
	public String getUpdateId(){
		return updateId;
	}
	/**
	 * 修改者ID设定
	 * @param updateId 修改者ID
	 */
	public void setUpdateId(String updateId){
		this.updateId=updateId;
	}
	/**
	 * 修改者IP取得
	 * @return 修改者IP
	 */
	public String getUpdateIp(){
		return updateIp;
	}
	/**
	 * 修改者IP设定
	 * @param updateIp 修改者IP
	 */
	public void setUpdateIp(String updateIp){
		this.updateIp=updateIp;
	}
	/**
	 * 全景分解图地址取得
	 * @return 全景分解图地址
	 */
	public String getBreakdownImg() {
	    return breakdownImg;
	}
	/**
	 * 全景分解图地址设定
	 * @param breakdownImg 全景分解图地址
	 */
	public void setBreakdownImg(String breakdownImg) {
	    this.breakdownImg = breakdownImg;
	}
	/**
	 * 场景跳转热点取得
	 * @return 场景跳转热点
	 */
	public List<PanoSpots> getHotSpots() {
	    return hotSpots;
	}
	/**
	 * 场景跳转热点设定
	 * @param hotSpots 场景跳转热点
	 */
	public void setHotSpots(List<PanoSpots> hotSpots) {
	    this.hotSpots = hotSpots;
	}
	/**
	 * 场景装饰图热点取得
	 * @return 场景装饰图热点
	 */
	public List<PanoSpots> getImgSpots() {
	    return imgSpots;
	}
	/**
	 * 场景装饰图热点设定
	 * @param imgSpots 场景装饰图热点
	 */
	public void setImgSpots(List<PanoSpots> imgSpots) {
	    this.imgSpots = imgSpots;
	}}