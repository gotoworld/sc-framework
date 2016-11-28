/*	
 * 权限_角色信息  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.account.vo;
import java.util.List;

import com.wu1g.framework.vo.BaseVO;
/**
 * <p>权限_角色信息  BEAN类。</p>	
 * @author easycode
 */
public class AuthRole extends BaseVO{

	private static final long serialVersionUID = -355116576084573380L;
	/**角色ID */
	private String id;
	/**角色名称 */
	private String name;
	/**超级管理员0否1是  默认值(0) */
	private String isSuper;
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
	/**权限id集合*/
	private List<String> permIdArray;
	/**
	 * 角色ID取得
	 * @return 角色ID
	 */
	public String getId(){
		return id;
	}
	/**
	 * 角色ID设定
	 * @param id 角色ID
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * 角色名称取得
	 * @return 角色名称
	 */
	public String getName(){
		return name;
	}
	/**
	 * 角色名称设定
	 * @param name 角色名称
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * 超级管理员0否1是  默认值(0)取得
	 * @return 超级管理员0否1是  默认值(0)
	 */
	public String getIsSuper(){
		return isSuper;
	}
	/**
	 * 超级管理员0否1是  默认值(0)设定
	 * @param isSuper 超级管理员0否1是  默认值(0)
	 */
	public void setIsSuper(String isSuper){
		this.isSuper=isSuper;
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
	 * 权限id集合取得
	 * @return 权限id集合
	 */
	public List<String> getPermIdArray() {
	    return permIdArray;
	}
	/**
	 * 权限id集合设定
	 * @param permIdArray 权限id集合
	 */
	public void setPermIdArray(List<String> permIdArray) {
	    this.permIdArray = permIdArray;
	}
}