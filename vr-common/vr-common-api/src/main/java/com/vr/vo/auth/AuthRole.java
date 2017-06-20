/*	
 * 权限_角色信息  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.vr.vo.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vr.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <p>权限_角色信息  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthRole extends BaseVO {

    private static final long serialVersionUID = -355116576084573380L;
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名称
     */
    @NotNull(message = "name不能为空")
    @Size(max = 50, message = "name最大50字符")
    private String name;
    /**
     * 超级管理员0否1是
     */
    private Integer isSuper;
    /**
     * 备注
     */
    @Size(max = 255, message = "memo最大255字符")
    private String memo;
    /**
     * 排序
     */
    private Integer orderNo;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 关键字
     */
    @Size(max = 255, message = "keyword最大255字符")
    private String keyword;
    /**
     * 是否删除
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 更新时间
     */
    private Date dateUpdated;
    /**
     * 权限id集合
     */
    private List<String> permIdArray;
}