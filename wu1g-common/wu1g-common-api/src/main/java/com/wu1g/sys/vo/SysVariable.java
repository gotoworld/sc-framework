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
import com.wu1g.framework.vo.BaseVO;
/**
 * <p>数据字典  BEAN类。</p>	
 * @author easycode
 */
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
	/**
	 * ID取得
	 * @return ID
	 */
	public String getId() {
	    return id;
	}
	/**
	 * ID设定
	 * @param id ID
	 */
	public void setId(String id) {
	    this.id = id;
	}
	/**
	 * 版本号  默认值(0)取得
	 * @return 版本号  默认值(0)
	 */
	public String getVersion() {
	    return version;
	}
	/**
	 * 版本号  默认值(0)设定
	 * @param version 版本号  默认值(0)
	 */
	public void setVersion(String version) {
	    this.version = version;
	}
	/**
	 * 语言取得
	 * @return 语言
	 */
	public String getLanguage() {
	    return language;
	}
	/**
	 * 语言设定
	 * @param language 语言
	 */
	public void setLanguage(String language) {
	    this.language = language;
	}
	/**
	 * 编码取得
	 * @return 编码
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 编码设定
	 * @param code 编码
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 名称取得
	 * @return 名称
	 */
	public String getName() {
	    return name;
	}
	/**
	 * 名称设定
	 * @param name 名称
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * 名称_英文取得
	 * @return 名称_英文
	 */
	public String getNameEn() {
	    return nameEn;
	}
	/**
	 * 名称_英文设定
	 * @param nameEn 名称_英文
	 */
	public void setNameEn(String nameEn) {
	    this.nameEn = nameEn;
	}
	/**
	 * 级别取得
	 * @return 级别
	 */
	public String getLevel() {
	    return level;
	}
	/**
	 * 级别设定
	 * @param level 级别
	 */
	public void setLevel(String level) {
	    this.level = level;
	}
	/**
	 * 父级ID取得
	 * @return 父级ID
	 */
	public String getParentid() {
	    return parentid;
	}
	/**
	 * 父级ID设定
	 * @param parentid 父级ID
	 */
	public void setParentid(String parentid) {
	    this.parentid = parentid;
	}
	/**
	 * 父级名称取得
	 * @return 父级名称
	 */
	public String getPname() {
	    return pname;
	}
	/**
	 * 父级名称设定
	 * @param pname 父级名称
	 */
	public void setPname(String pname) {
	    this.pname = pname;
	}
	/**
	 * 上下级组合编码取得
	 * @return 上下级组合编码
	 */
	public String getpCode() {
	    return pCode;
	}
	/**
	 * 上下级组合编码设定
	 * @param pCode 上下级组合编码
	 */
	public void setpCode(String pCode) {
	    this.pCode = pCode;
	}
	/**
	 * 关键字取得
	 * @return 关键字
	 */
	public String getKeyword() {
	    return keyword;
	}
	/**
	 * 关键字设定
	 * @param keyword 关键字
	 */
	public void setKeyword(String keyword) {
	    this.keyword = keyword;
	}
	/**
	 * 排序取得
	 * @return 排序
	 */
	public String getOrderNo() {
	    return orderNo;
	}
	/**
	 * 排序设定
	 * @param orderNo 排序
	 */
	public void setOrderNo(String orderNo) {
	    this.orderNo = orderNo;
	}
	/**
	 * 是否删除  默认值(0)取得
	 * @return 是否删除  默认值(0)
	 */
	public String getDelFlag() {
	    return delFlag;
	}
	/**
	 * 是否删除  默认值(0)设定
	 * @param delFlag 是否删除  默认值(0)
	 */
	public void setDelFlag(String delFlag) {
	    this.delFlag = delFlag;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00)取得
	 * @return 创建时间  默认值(2000-01-01 00:00:00)
	 */
	public String getDateCreated() {
	    return dateCreated;
	}
	/**
	 * 创建时间  默认值(2000-01-01 00:00:00)设定
	 * @param dateCreated 创建时间  默认值(2000-01-01 00:00:00)
	 */
	public void setDateCreated(String dateCreated) {
	    this.dateCreated = dateCreated;
	}
	/**
	 * 建立者ID取得
	 * @return 建立者ID
	 */
	public String getCreateId() {
	    return createId;
	}
	/**
	 * 建立者ID设定
	 * @param createId 建立者ID
	 */
	public void setCreateId(String createId) {
	    this.createId = createId;
	}
	/**
	 * 建立者IP取得
	 * @return 建立者IP
	 */
	public String getCreateIp() {
	    return createIp;
	}
	/**
	 * 建立者IP设定
	 * @param createIp 建立者IP
	 */
	public void setCreateIp(String createIp) {
	    this.createIp = createIp;
	}
	/**
	 * 修改时间  默认值(2000-01-01 00:00:00)取得
	 * @return 修改时间  默认值(2000-01-01 00:00:00)
	 */
	public String getDateUpdate() {
	    return dateUpdate;
	}
	/**
	 * 修改时间  默认值(2000-01-01 00:00:00)设定
	 * @param dateUpdate 修改时间  默认值(2000-01-01 00:00:00)
	 */
	public void setDateUpdate(String dateUpdate) {
	    this.dateUpdate = dateUpdate;
	}
	/**
	 * 修改者ID取得
	 * @return 修改者ID
	 */
	public String getUpdateId() {
	    return updateId;
	}
	/**
	 * 修改者ID设定
	 * @param updateId 修改者ID
	 */
	public void setUpdateId(String updateId) {
	    this.updateId = updateId;
	}
	/**
	 * 修改者IP取得
	 * @return 修改者IP
	 */
	public String getUpdateIp() {
	    return updateIp;
	}
	/**
	 * 修改者IP设定
	 * @param updateIp 修改者IP
	 */
	public void setUpdateIp(String updateIp) {
	    this.updateIp = updateIp;
	}

}