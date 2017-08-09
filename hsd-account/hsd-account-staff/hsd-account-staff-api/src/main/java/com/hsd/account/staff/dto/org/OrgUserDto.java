/*	
 * 组织架构_用户  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.hsd.account.staff.dto.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <p>组织架构_用户  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgUserDto extends BaseDto {

    private static final long serialVersionUID = -520196248671485682L;
    /**ID*/
    private Long id;
    /**员工账号*/
    @Size(max=55,message="account最大55字符")
    private String account;
    /**员工密码*/
    @Size(max=64,message="pwd最大64字符")
    private String pwd;
    /**成员名称*/
    @Size(max=55,message="name最大55字符")
    private String name;
    /**性别[0男1女3保密]*/
    private Integer gender;
    /**手机号*/
    @Size(max=13,message="cellphone最大13字符")
    private String cellphone;
    /**头像url*/
    @Size(max=255,message="avatar最大255字符")
    private String avatar;
    /**邮箱*/
    @Size(max=64,message="email最大64字符")
    private String email;
    /**用户类型0管理员1普通用户*/
    private Integer type;
    /**最后登录日期*/
    private Date lastLogin;
    /**登录次数*/
    private Integer count;
    /**状态0启用1禁用*/
    private Integer state;
    /**生效时间*/
    private Date effDate;
    /**失效时间*/
    private Date expDate;
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
    /**BI时间戳*/
    private Date biUpdateTs;

    /** 用户角色id集合*/
    private List<Long> roleIdArray;
    /**用户角色名称集合*/
    private String roleNames;
    /**部门id集合*/
    private List<Long> orgIdArray;

    private String oldpwd;
    private String newpwd;
    private String confirmpwd;
    /**超级管理员标记*/
    private Integer iissuperman;
    /**sessionId*/
    private String sid;
}