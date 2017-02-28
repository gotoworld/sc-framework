/*	
 * 权限_角色vs权限  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.vo.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>权限_角色vs类目  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthRoleVsCategory extends BaseVO {

    private static final long serialVersionUID = -345188437933742640L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 创建时间
     */
    private Date dateCreated;
}