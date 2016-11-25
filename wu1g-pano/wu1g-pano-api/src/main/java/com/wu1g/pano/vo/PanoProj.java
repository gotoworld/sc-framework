/*	
 * 全景_项目 BEAN类	
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

import java.util.List;
/**
 * <p>全景_项目  BEAN类。
 * @author easycode
 */
public class PanoProj   extends BaseVO {

	private static final long serialVersionUID = -964391982845378801L;
	/**id */
	private String id;
	/**类目id */
	private String categoryId;
	/**项目类型0图片1视频 */
	private String type;
	/**名称 */
	private String name;
	/**背景音乐 */
	private String soundSrc;
	/**视频解说*/
	private String videoSrc;
	/**开放评论 0否1是 默认值(0)*/
	private String isComments;
	/**小行星开场 0否1是 默认值(0)*/
	private String isPlanetoid;
	/**是否补地 0否1是 默认值(0)*/
	private String isMending;
	/**导览图 */
	private String mapSrc;
	/**全景生成标记0否1是 默认值(0)*/
	private String isSeccuss;
	/**点赞数量 默认值(0)*/
	private String thumbsUpNum;
	/**浏览量 默认值(0)*/
	private String pvNum;
	/**XMlDATA */
	private String xmlData;
	/**logo图片 */
	private String logoPic;
	/**logo链接 */
	private String logoUrl;
	/**雪景类型*/
	private String snowType;
	/**显示fps 0否1是*/
	private String isFps;
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
	/**场景集合*/
	private List<PanoScene> scenes;
	/**导览图中场景信息*/
	private List<PanoMap> radars;
	/**生成全景文件执行标记*/
	private boolean makePanoFlag;
	
	
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
	 * 类目id取得
	 * @return 类目id
	 */
	public String getCategoryId(){
		return categoryId;
	}
	/**
	 * 类目id设定
	 * @param categoryId 类目id
	 */
	public void setCategoryId(String categoryId){
		this.categoryId=categoryId;
	}
	/**
	 * 项目类型0图片1视频取得
	 * @return 项目类型0图片1视频
	 */
	public String getType(){
		return type;
	}
	/**
	 * 项目类型0图片1视频设定
	 * @param type 项目类型0图片1视频
	 */
	public void setType(String type){
		this.type=type;
	}
	/**
	 * 名称取得
	 * @return 名称
	 */
	public String getName(){
		return name;
	}
	/**
	 * 名称设定
	 * @param name 名称
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * 背景音乐取得
	 * @return 背景音乐
	 */
	public String getSoundSrc(){
		return soundSrc;
	}
	/**
	 * 背景音乐设定
	 * @param soundSrc 背景音乐
	 */
	public void setSoundSrc(String soundSrc){
		this.soundSrc=soundSrc;
	}
	/**
	 * 视频解说取得
	 * @return 视频解说
	 */
	public String getVideoSrc() {
	    return videoSrc;
	}
	/**
	 * 视频解说设定
	 * @param videoSrc 视频解说
	 */
	public void setVideoSrc(String videoSrc) {
	    this.videoSrc = videoSrc;
	}
	/**
	 * 开放评论 0否1是 默认值(0)取得
	 * @return 开放评论 0否1是 默认值(0)
	 */
	public String getIsComments(){
		if(isComments==null){
			isComments="0";
		}
		return isComments;
	}
	/**
	 * 开放评论 0否1是 默认值(0)设定
	 * @param isComments 开放评论 0否1是 默认值(0)
	 */
	public void setIsComments(String isComments){
		this.isComments=isComments;
	}
	/**
	 * 小行星开场 0否1是 默认值(0)取得
	 * @return 小行星开场 0否1是 默认值(0)
	 */
	public String getIsPlanetoid(){
		if(isPlanetoid==null){
			isPlanetoid="0";
		}
		return isPlanetoid;
	}
	/**
	 * 小行星开场 0否1是 默认值(0)设定
	 * @param isPlanetoid 小行星开场 0否1是 默认值(0)
	 */
	public void setIsPlanetoid(String isPlanetoid){
		this.isPlanetoid=isPlanetoid;
	}
	/**
	 * 是否补地 0否1是 默认值(0)取得
	 * @return 是否补地 0否1是 默认值(0)
	 */
	public String getIsMending(){
		if(isMending==null){
			isMending="0";
		}
		return isMending;
	}
	/**
	 * 是否补地 0否1是 默认值(0)设定
	 * @param isMending 是否补地 0否1是 默认值(0)
	 */
	public void setIsMending(String isMending){
		this.isMending=isMending;
	}
	/**
	 * 导览图取得
	 * @return 导览图
	 */
	public String getMapSrc(){
		return mapSrc;
	}
	/**
	 * 导览图设定
	 * @param mapSrc 导览图
	 */
	public void setMapSrc(String mapSrc){
		this.mapSrc=mapSrc;
	}
	/**
	 * 全景生成标记0否1是 默认值(0)取得
	 * @return 全景生成标记0否1是 默认值(0)
	 */
	public String getIsSeccuss(){
		return isSeccuss;
	}
	/**
	 * 全景生成标记0否1是 默认值(0)设定
	 * @param isSeccuss 全景生成标记0否1是 默认值(0)
	 */
	public void setIsSeccuss(String isSeccuss){
		this.isSeccuss=isSeccuss;
	}
	/**
	 * 点赞数量 默认值(0)取得
	 * @return 点赞数量 默认值(0)
	 */
	public String getThumbsUpNum(){
		if(thumbsUpNum==null){
			thumbsUpNum="0";
		}
		return thumbsUpNum;
	}
	/**
	 * 点赞数量 默认值(0)设定
	 * @param thumbsUpNum 点赞数量 默认值(0)
	 */
	public void setThumbsUpNum(String thumbsUpNum){
		this.thumbsUpNum=thumbsUpNum;
	}
	/**
	 * 浏览量 默认值(0)取得
	 * @return 浏览量 默认值(0)
	 */
	public String getPvNum() {
	    return pvNum;
	}
	/**
	 * 浏览量 默认值(0)设定
	 * @param pvNum 浏览量 默认值(0)
	 */
	public void setPvNum(String pvNum) {
	    this.pvNum = pvNum;
	}
	/**
	 * XMlDATA取得
	 * @return XMlDATA
	 */
	public String getXmlData(){
		return xmlData;
	}
	/**
	 * XMlDATA设定
	 * @param xmlData XMlDATA
	 */
	public void setXmlData(String xmlData){
		this.xmlData=xmlData;
	}
	/**
	 * logo图片取得
	 * @return logo图片
	 */
	public String getLogoPic(){
		return logoPic;
	}
	/**
	 * logo图片设定
	 * @param logoPic logo图片
	 */
	public void setLogoPic(String logoPic){
		this.logoPic=logoPic;
	}
	/**
	 * logo链接取得
	 * @return logo链接
	 */
	public String getLogoUrl(){
		return logoUrl;
	}
	/**
	 * logo链接设定
	 * @param logoUrl logo链接
	 */
	public void setLogoUrl(String logoUrl){
		this.logoUrl=logoUrl;
	}
	/**
	 * 雪景类型取得
	 * @return 雪景类型
	 */
	public String getSnowType() {
	    return snowType;
	}
	/**
	 * 雪景类型设定
	 * @param snowType 雪景类型
	 */
	public void setSnowType(String snowType) {
	    this.snowType = snowType;
	}
	/**
	 * 显示fps 0否1是取得
	 * @return 显示fps 0否1是
	 */
	public String getIsFps() {
	    return isFps;
	}
	/**
	 * 显示fps 0否1是设定
	 * @param isFps 显示fps 0否1是
	 */
	public void setIsFps(String isFps) {
	    this.isFps = isFps;
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
	 * 场景集合取得
	 * @return 场景集合
	 */
	public List<PanoScene> getScenes() {
	    return scenes;
	}
	/**
	 * 场景集合设定
	 * @param scenes 场景集合
	 */
	public void setScenes(List<PanoScene> scenes) {
	    this.scenes = scenes;
	}
	/**
	 * 导览图中场景信息取得
	 * @return 导览图中场景信息
	 */
	public List<PanoMap> getRadars() {
	    return radars;
	}
	/**
	 * 导览图中场景信息设定
	 * @param radars 导览图中场景信息
	 */
	public void setRadars(List<PanoMap> radars) {
	    this.radars = radars;
	}
	/**
	 * 生成全景文件执行标记取得
	 * @return 生成全景文件执行标记
	 */
	public boolean isMakePanoFlag() {
	    return makePanoFlag;
	}
	/**
	 * 生成全景文件执行标记设定
	 * @param makePanoFlag 生成全景文件执行标记
	 */
	public void setMakePanoFlag(boolean makePanoFlag) {
	    this.makePanoFlag = makePanoFlag;
	}}