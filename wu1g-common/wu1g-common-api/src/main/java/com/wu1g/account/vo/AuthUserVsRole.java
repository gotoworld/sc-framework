/*	
 * 权限_用户vs角色  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.account.vo;
import com.wu1g.framework.vo.BaseVO;
/**
 * <p>权限_用户vs角色  BEAN类。</p>	
 * @author easycode
 */
public class AuthUserVsRole extends BaseVO{

	private static final long serialVersionUID = -746905040521582988L;
	/**用户id */
	private String userId;
	/**角色id */
	private String roleId;
	/**创建时间  默认值(2000-01-01 00:00:00) */
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**
	 * 用户id 设定 
	 * @param userId用户id
	 */
	public void setUserId(String userId){
		this.userId=userId;
	}
	/**
	 * 用户id 取得 
	 * @return 用户id
	 */
	public String getUserId(){
		return userId;
	}
	/**
	 * 角色id 设定 
	 * @param roleId角色id
	 */
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}
	/**
	 * 角色id 取得 
	 * @return 角色id
	 */
	public String getRoleId(){
		return roleId;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00) 设定 
	 * @param dateCreated创建时间
	 */
	public void setDateCreated(String dateCreated){
		this.dateCreated=dateCreated;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00) 取得 
	 * @return 创建时间
	 */
	public String getDateCreated(){
		return dateCreated;
	}
	/**
	 * 建立者ID 设定 
	 * @param createId建立者ID
	 */
	public void setCreateId(String createId){
		this.createId=createId;
	}
	/**
	 * 建立者ID 取得 
	 * @return 建立者ID
	 */
	public String getCreateId(){
		return createId;
	}
	/**
	 * 建立者IP 设定 
	 * @param createIp建立者IP
	 */
	public void setCreateIp(String createIp){
		this.createIp=createIp;
	}
	/**
	 * 建立者IP 取得 
	 * @return 建立者IP
	 */
	public String getCreateIp(){
		return createIp;
	}
}