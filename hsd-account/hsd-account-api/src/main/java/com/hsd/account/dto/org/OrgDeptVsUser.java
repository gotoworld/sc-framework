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
package com.hsd.account.dto.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>组织架构_用户vs部门  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgDeptVsUser extends BaseDto {
    private static final long serialVersionUID = -970983598656672964L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 创建时间
     */
    private Date dateCreated;
}