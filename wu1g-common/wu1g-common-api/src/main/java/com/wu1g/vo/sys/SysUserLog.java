/*	
 * 系统_管理员操作日志  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.vo.sys;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>系统_管理员操作日志  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}