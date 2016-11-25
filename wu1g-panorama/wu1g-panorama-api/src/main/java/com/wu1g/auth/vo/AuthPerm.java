/*	
 * 权限_权限信息  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.vo;

import com.wu1g.framework.vo.BaseVO;

import java.util.List;
/**
 * <p>权限_权限信息  BEAN类。</p>	
 * @author easycode
 */
public class AuthPerm extends BaseVO{

	private static final long serialVersionUID = -786164389318663680L;
	/**权限id */
	private String id;
	/**权限名称 */
	private String name;
	/**权限匹配符 */
	private String matchStr;
	/**备注 */
	private String memo;
	/**排序 */
	private String orderNo;
	/**版本号  默认值(0) */
	private String version;
	/**关键字 */
	private String keyword;
	/**是否删除  默认值(0) */
	private String delFlag;
	/**创建时间  默认值(2000-01-01 00:00:00) */
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**修改时间  默认值(2000-01-01 00:00:00) */
	private String dateUpdate;
	/**修改者ID */
	private String updateId;
	/**修改者IP */
	private String updateIp;
	/**父级ID */
	private String parentid;
	/**bean类 集合*/
	List<AuthPerm> beans;
	/**
	 * 权限id取得
	 * @return 权限id
	 */
	public String getId(){
		return id;
	}
	/**
	 * 权限id设定
	 * @param id 权限id
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * 权限名称取得
	 * @return 权限名称
	 */
	public String getName(){
		return name;
	}
	/**
	 * 权限名称设定
	 * @param name 权限名称
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * 权限匹配符取得
	 * @return 权限匹配符
	 */
	public String getMatchStr(){
		return matchStr;
	}
	/**
	 * 权限匹配符设定
	 * @param matchStr 权限匹配符
	 */
	public void setMatchStr(String matchStr){
		this.matchStr=matchStr;
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
	 * 版本号  默认值(0)取得
	 * @return 版本号  默认值(0)
	 */
	public String getVersion(){
		return version;
	}
	/**
	 * 版本号  默认值(0)设定
	 * @param version 版本号  默认值(0)
	 */
	public void setVersion(String version){
		this.version=version;
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
	 * 是否删除  默认值(0)取得
	 * @return 是否删除  默认值(0)
	 */
	public String getDelFlag(){
		return delFlag;
	}
	/**
	 * 是否删除  默认值(0)设定
	 * @param delFlag 是否删除  默认值(0)
	 */
	public void setDelFlag(String delFlag){
		this.delFlag=delFlag;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00)取得
	 * @return 创建时间  默认值(2000-01-01 00:00:00)
	 */
	public String getDateCreated(){
		return dateCreated;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00)设定
	 * @param dateCreated 创建时间  默认值(2000-01-01 00:00:00)
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
	 * 修改时间  默认值(2000-01-01 00:00:00)取得
	 * @return 修改时间  默认值(2000-01-01 00:00:00)
	 */
	public String getDateUpdate(){
		return dateUpdate;
	}
	/**
	 * 修改时间  默认值(2000-01-01 00:00:00)设定
	 * @param dateUpdate 修改时间  默认值(2000-01-01 00:00:00)
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
	 * 父级ID取得
	 * @return 父级ID
	 */
	public String getParentid(){
		return parentid;
	}
	/**
	 * 父级ID设定
	 * @param parentid 父级ID
	 */
	public void setParentid(String parentid){
		this.parentid=parentid;
	}
	/**
	 * bean类 集合取得
	 * @return bean类 集合
	 */
	public List<AuthPerm> getBeans() {
	    return beans;
	}
	/**
	 * bean类 集合设定
	 * @param beans bean类 集合
	 */
	public void setBeans(List<AuthPerm> beans) {
	    this.beans = beans;
	}
}