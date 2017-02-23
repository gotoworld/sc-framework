/*	
 * 组织架构_用户vs部门  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.vo.org;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>组织架构_用户vs部门  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}