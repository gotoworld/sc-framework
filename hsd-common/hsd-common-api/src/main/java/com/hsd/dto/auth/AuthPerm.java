/*	
 * 权限_权限信息  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.hsd.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
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
public class AuthPerm extends BaseDto {

    private static final long serialVersionUID = -786164389318663680L;
    /**
     * 权限id
     */
    @Size(max = 32, message = "id最大32字符")
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
     * 父级ID
     */
    @Size(max = 32, message = "parentId最大32字符")
    private String parentId;
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
     * 修改时间
     */
    private Date dateUpdated;
    /**
     * bean类 集合
     */
    List<AuthPerm> nodes;

    private String text;
    private Integer[] tags;
    public String getText(){
        return name;
    }
    public Integer[] getTags(){
        return new Integer[]{nodes!=null?nodes.size():0};
    };

}