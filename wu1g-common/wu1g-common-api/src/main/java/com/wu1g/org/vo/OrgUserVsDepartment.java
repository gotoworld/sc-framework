/*	
 * 组织架构_用户vs部门  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.vo;
import com.wu1g.framework.vo.BaseVO;
/**
 * <p>组织架构_用户vs部门  BEAN类。</p>	
 * @author easycode
 */
public class OrgUserVsDepartment extends BaseVO{

	private static final long serialVersionUID = -970983598656672964L;
	/**用户id */
	private String userid;
	/**部门id */
	private String departmentid;
	/**版本号  默认值(0) */
	private String version;
	/**创建时间  默认值(2000-01-01 00:00:00) */
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**
	 * 用户id 设定 
	 * @param userid用户id
	 */
	public void setUserid(String userid){
		this.userid=userid;
	}
	/**
	 * 用户id 取得 
	 * @return 用户id
	 */
	public String getUserid(){
		return userid;
	}
	/**
	 * 部门id 设定 
	 * @param departmentid部门id
	 */
	public void setDepartmentid(String departmentid){
		this.departmentid=departmentid;
	}
	/**
	 * 部门id 取得 
	 * @return 部门id
	 */
	public String getDepartmentid(){
		return departmentid;
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