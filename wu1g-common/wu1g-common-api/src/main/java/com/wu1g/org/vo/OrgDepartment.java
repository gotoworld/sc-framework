/*	
 * 组织架构_部门  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * <p>组织架构_部门  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgDepartment extends BaseVO{

	private static final long serialVersionUID = -723744788319541400L;
	/**ID */
	private String id;
	/**版本号  默认值(0) */
	private String version;
	/**名称 */
	private String name;
	/**编码 */
	private String code;
	/**父级ID */
	private String parentid;
	/**级别 */
	private String level;
	/**上下级组合编码 */
	private String pCode;
	/**状态 */
	private String state;
	/**备注 */
	private String memo;
	/**排序 */
	private String orderNo;
	/**关键字 */
	private String keyword;
	/**是否删除(0否1是)  默认值(0) */
	private String delFlag;
	/**数据过期时间0:永不过期  默认值(0) */
	private String invalidTime;
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
	/**子对象集合*/
	private List<OrgDepartment> beans;
}