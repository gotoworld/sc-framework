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
package com.wu1g.account.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * <p>权限_权限信息  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}