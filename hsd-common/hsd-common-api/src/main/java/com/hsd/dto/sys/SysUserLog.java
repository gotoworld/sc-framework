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
package com.hsd.dto.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>系统_管理员操作日志  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserLog extends BaseDto {

    private static final long serialVersionUID = -687161275861309523L;
    /**
     * ID
     */
    private Long id;
    /**
     * 操作类型
     */
    private String type;
    /**
     * 描述
     */
    private String memo;
    /**
     * 对象信息
     */
    private String detailInfo;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 操作人ID
     */
    private Long createId;
    /**
     * 操作人名称
     */
    private String createName;
    /**
     * 操作人IP
     */
    private String createIp;
}