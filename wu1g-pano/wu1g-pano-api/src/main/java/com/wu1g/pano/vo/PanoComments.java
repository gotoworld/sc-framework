/*	
 * 全景_评论 BEAN类	
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

import cn.com.baseos.bean.pano.PanoComments;
/**
 * <p>全景_评论  BEAN类。
 * @author easycode
 */
public class PanoComments   extends BaseVO {

	private static final long serialVersionUID = -590742611946168992L;
	/**id */
	private String id;
	/**场景id */
	private String sceneId;
	/**sname */
	private String sname;
	/**内容 */
	private String content;
	/**头像 */
	private String img;
	/**水平坐标 */
	private String ath;
	/**垂直坐标 */
	private String atv;
	/**昵称 */
	private String nickname;
	/**性别 */
	private String sex;
	/**省份 */
	private String province;
	/**市区 */
	private String city;
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
	/**数据过期时间0:永不过期 默认值(0)*/
	private String invalidTime;
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
	/**场景标题 */
	private String sceneTitle;
	/**项目标题 */
	private String projTitle;
	/**项目Id */
	private String projId;
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
	 * sname取得
	 * @return sname
	 */
	public String getSname(){
		return sname;
	}
	/**
	 * sname设定
	 * @param sname sname
	 */
	public void setSname(String sname){
		this.sname=sname;
	}
	/**
	 * 内容取得
	 * @return 内容
	 */
	public String getContent(){
		return content;
	}
	/**
	 * 内容设定
	 * @param content 内容
	 */
	public void setContent(String content){
		this.content=content;
	}
	/**
	 * 头像取得
	 * @return 头像
	 */
	public String getImg(){
		return img;
	}
	/**
	 * 头像设定
	 * @param img 头像
	 */
	public void setImg(String img){
		this.img=img;
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
	 * 昵称取得
	 * @return 昵称
	 */
	public String getNickname(){
		return nickname;
	}
	/**
	 * 昵称设定
	 * @param nickname 昵称
	 */
	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	/**
	 * 性别取得
	 * @return 性别
	 */
	public String getSex(){
		return sex;
	}
	/**
	 * 性别设定
	 * @param sex 性别
	 */
	public void setSex(String sex){
		this.sex=sex;
	}
	/**
	 * 省份取得
	 * @return 省份
	 */
	public String getProvince(){
		return province;
	}
	/**
	 * 省份设定
	 * @param province 省份
	 */
	public void setProvince(String province){
		this.province=province;
	}
	/**
	 * 市区取得
	 * @return 市区
	 */
	public String getCity(){
		return city;
	}
	/**
	 * 市区设定
	 * @param city 市区
	 */
	public void setCity(String city){
		this.city=city;
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
	 * 数据过期时间0:永不过期 默认值(0)取得
	 * @return 数据过期时间0:永不过期 默认值(0)
	 */
	public String getInvalidTime(){
		return invalidTime;
	}
	/**
	 * 数据过期时间0:永不过期 默认值(0)设定
	 * @param invalidTime 数据过期时间0:永不过期 默认值(0)
	 */
	public void setInvalidTime(String invalidTime){
		this.invalidTime=invalidTime;
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
	 * 场景标题取得
	 * @return 场景标题
	 */
	public String getSceneTitle() {
	    return sceneTitle;
	}
	/**
	 * 场景标题设定
	 * @param sceneTitle 场景标题
	 */
	public void setSceneTitle(String sceneTitle) {
	    this.sceneTitle = sceneTitle;
	}
	/**
	 * 项目标题取得
	 * @return 项目标题
	 */
	public String getProjTitle() {
	    return projTitle;
	}
	/**
	 * 项目标题设定
	 * @param projTitle 项目标题
	 */
	public void setProjTitle(String projTitle) {
	    this.projTitle = projTitle;
	}
	/**
	 * 项目Id取得
	 * @return 项目Id
	 */
	public String getProjId() {
	    return projId;
	}
	/**
	 * 项目Id设定
	 * @param projId 项目Id
	 */
	public void setProjId(String projId) {
	    this.projId = projId;
	}}