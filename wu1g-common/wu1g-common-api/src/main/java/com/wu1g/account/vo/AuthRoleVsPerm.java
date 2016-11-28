/*	
 * 权限_角色vs权限  BEAN类	
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
 * <p>权限_角色vs权限  BEAN类。</p>	
 * @author easycode
 */
public class AuthRoleVsPerm extends BaseVO{

	private static final long serialVersionUID = -345188437933742640L;
	/**角色id */
	private String roleId;
	/**权限id */
	private String permId;
	/**创建时间  默认值(2000-01-01 00:00:00) */
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**版本号  默认值(0) */
	private String version;
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
	 * 权限id 设定 
	 * @param permId权限id
	 */
	public void setPermId(String permId){
		this.permId=permId;
	}
	/**
	 * 权限id 取得 
	 * @return 权限id
	 */
	public String getPermId(){
		return permId;
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
	/**
	 * 版本号  默认值(0) 设定 
	 * @param version版本号
	 */
	public void setVersion(String version){
		this.version=version;
	}
	/**
	 * 版本号  默认值(0) 取得 
	 * @return 版本号
	 */
	public String getVersion(){
		return version;
	}
}