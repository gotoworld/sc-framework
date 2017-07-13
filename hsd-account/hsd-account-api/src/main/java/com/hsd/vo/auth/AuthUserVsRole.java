/*	
 * 权限_用户vs角色  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.hsd.vo.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>权限_用户vs角色  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthUserVsRole extends BaseVO {

    private static final long serialVersionUID = -746905040521582988L;
    /**
     * 用户id
     */
    private Long UserId;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 创建时间
     */
    private Date dateCreated;
}