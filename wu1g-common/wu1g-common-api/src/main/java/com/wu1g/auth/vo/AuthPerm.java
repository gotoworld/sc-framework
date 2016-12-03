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
package com.wu1g.auth.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <p>权限_权限信息  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthPerm extends BaseVO {

    private static final long serialVersionUID = -786164389318663680L;
    /**
     * 权限id
     */
    @Size(max = 22, message = "id最大22字符")
    private String id;
    /**
     * 权限名称
     */
    @NotNull(message = "name不能为空")
    @Size(max = 50, message = "name最大50字符")
    private String name;
    /**
     * 权限匹配符
     */
    @NotNull(message = "matchStr不能为空")
    @Size(max = 50, message = "matchStr最大50字符")
    private String matchStr;
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
    private String delFlag;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 建立者ID
     */
    @Size(max = 50, message = "createId最大50字符")
    private String createId;
    /**
     * 建立者IP
     */
    @Size(max = 50, message = "createIp最大50字符")
    private String createIp;
    /**
     * 修改时间
     */
    private Date dateUpdate;
    /**
     * 修改者ID
     */
    @Size(max = 50, message = "updateId最大50字符")
    private String updateId;
    /**
     * 修改者IP
     */
    @Size(max = 50, message = "updateIp最大50字符")
    private String updateIp;
    /**
     * 父级ID
     */
    @Size(max = 22, message = "parentid最大22字符")
    private String parentid;
    /**
     * bean类 集合
     */
    List<AuthPerm> beans;
}