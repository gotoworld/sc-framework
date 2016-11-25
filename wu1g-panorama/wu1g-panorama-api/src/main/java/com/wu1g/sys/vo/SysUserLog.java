/*	
 * 系统_管理员操作日志  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.sys.vo;
import com.wu1g.framework.vo.BaseVO;
/**
 * <p>系统_管理员操作日志  BEAN类。</p>	
 * @author easycode
 */
public class SysUserLog extends BaseVO{

	private static final long serialVersionUID = -687161275861309523L;
	/**ID */
	private String id;
	/**操作类型(a增d删u改q查) */
	private String type;
	/**具体描述 */
	private String description;
	/**创建时间  默认值(2000-01-01 00:00:00) */
	private String dateCreated;
	/**建立者ID */
	private String createId;
	/**建立者IP */
	private String createIp;
	/**
	 * ID 设定 
	 * @param idID
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * ID 取得 
	 * @return ID
	 */
	public String getId(){
		return id;
	}
	/**
	 * 操作类型(a增d删u改q查) 设定 
	 * @param type操作类型(a增d删u改q查)
	 */
	public void setType(String type){
		this.type=type;
	}
	/**
	 * 操作类型(a增d删u改q查) 取得 
	 * @return 操作类型(a增d删u改q查)
	 */
	public String getType(){
		return type;
	}
	/**
	 * 具体描述 设定 
	 * @param description具体描述
	 */
	public void setDescription(String description){
		this.description=description;
	}
	/**
	 * 具体描述 取得 
	 * @return 具体描述
	 */
	public String getDescription(){
		return description;
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