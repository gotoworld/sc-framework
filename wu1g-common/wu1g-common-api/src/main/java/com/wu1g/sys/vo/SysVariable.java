/*	
 * 数据字典  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.12.14      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 baseos wxqy demo  System. - All Rights Reserved.		
 *	
 */
package com.wu1g.sys.vo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>数据字典  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysVariable extends BaseVO{

	private static final long serialVersionUID = -762636592679599049L;
	/**ID */
	private String id;
	/**版本号  默认值(0) */
	private String version;
	/**语言*/
	private String language;
	/**编码 */
	private String code;
	/**名称 */
	private String name;
	/**名称_英文*/
	private String nameEn;
	/**级别 */
	private String level;
	/**父级ID */
	private String parentid;
	/**父级名称*/
	private String pname;
	/**上下级组合编码 */
	private String pCode;
	/**关键字 */
	private String keyword;
	/**排序 */
	private String orderNo;
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

}