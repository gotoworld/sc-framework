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
package com.hsd.account.staff.dto.sys;

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
 * <p>数据字典  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysVariableDto extends BaseDto {

    private static final long serialVersionUID = -762636592679599049L;
    /**ID*/
    private Long id;
    /**编码*/
    @NotNull(message="code不能为空")@Size(max=32,message="code最大32字符")
    private String code;
    /**名称*/
    @NotNull(message="name不能为空")@Size(max=100,message="name最大100字符")
    private String name;
    /**所属系统域*/
    private String domainCode;
    /**父级ID*/
    private Long parentId;
    /**备注*/
    @Size(max=255,message="memo最大255字符")
    private String memo;
    /**版本号*/
    private Integer version;
    /**排序*/
    private Integer orderNo;
    /**删除标记(0正常1删除)*/
    private Integer delFlag;
    /**建立者ID*/
    private Long createId;
    /**创建时间*/
    private Date dateCreated;
    /**更新时间*/
    private Date dateUpdated;

    /** 子对象集合 */
    private List<SysVariableDto> nodes;
    private String text;
    private Integer[] tags;
    public String getText(){
        return name;
    }
    public Integer[] getTags(){
        return new Integer[]{nodes!=null?nodes.size():0};
    }
}