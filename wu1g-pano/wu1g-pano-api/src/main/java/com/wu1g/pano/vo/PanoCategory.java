/*	
 * 全景_类目 BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.vo;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu1g.framework.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>全景_类目  BEAN类。
 *
 * @author easycode
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanoCategory extends BaseVO {

    private static final long serialVersionUID = -499831113308884045L;
    /**
     * ID
     */
    @Size(max = 22, message = "id最大22字符")
    private String id;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 名称
     */
    @NotNull(message = "name不能为空")
    @Size(max = 100, message = "name最大100字符")
    private String name;
    /**
     * 编码
     */
    @Size(max = 100, message = "code最大100字符")
    private String code;
    /**
     * 父级ID
     */
    @Size(max = 100, message = "parentid最大100字符")
    private String parentid;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 上下级组合编码
     */
    @Size(max = 1000, message = "pCode最大1000字符")
    private String pCode;
    /**
     * 状态
     */
    @Size(max = 1, message = "state最大1字符")
    private String state;
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
     * 关键字
     */
    @Size(max = 255, message = "keyword最大255字符")
    private String keyword;
    /**
     * 是否删除(0否1是)
     */
    @NotNull(message = "delFlag不能为空")
    @Size(max = 1, message = "delFlag最大1字符")
    private Byte delFlag;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 建立者ID
     */
    @Size(max = 64, message = "createId最大64字符")
    private String createId;
    /**
     * 建立者IP
     */
    @Size(max = 64, message = "createIp最大64字符")
    private String createIp;
    /**
     * 修改时间
     */
    private Date dateUpdate;
    /**
     * 修改者ID
     */
    @Size(max = 64, message = "updateId最大64字符")
    private String updateId;
    /**
     * 修改者IP
     */
    @Size(max = 64, message = "updateIp最大64字符")
    private String updateIp;
    /**
     * 类目集合
     */
    private List<PanoCategory> beans;

}