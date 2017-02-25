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
package com.wu1g.vo.org;

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
 * <p>组织架构_用户  BEAN类。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgUser extends BaseVO {

    private static final long serialVersionUID = -520196248671485682L;
    /**
     * ID
     */
    private Long id;
    /**
     * 员工账号
     */
    @NotNull(message = "accid不能为空")
    @Size(max = 55, message = "accid最大55字符")
    private String accid;
    /**
     * 员工密码
     */
    @Size(max = 64, message = "pwd最大64字符")
    private String pwd;
    /**
     * 成员名称
     */
    @Size(max = 55, message = "name最大55字符")
    private String name;
    /**
     * 工号
     */
    @Size(max = 32, message = "jobNo最大32字符")
    private String jobNo;
    /**
     * 职位
     */
    @Size(max = 32, message = "position最大32字符")
    private String position;
    /**
     * 性别[0男1女3保密]
     */
    private Integer gender;
    /**
     * 移动电话
     */
    @Size(max = 24, message = "mobile最大24字符")
    private String mobile;
    /**
     * 固定电话
     */
    @Size(max = 24, message = "tel最大24字符")
    private String tel;
    /**
     * 启用状态1启用0禁用
     */
    private Integer enable;
    /**
     * 头像url
     */
    @Size(max = 255, message = "avatar最大255字符")
    private String avatar;
    /**
     * 邮箱
     */
    @Size(max = 64, message = "email最大64字符")
    private String email;
    /**
     * 微信id
     */
    @Size(max = 64, message = "weixinid最大64字符")
    private String weixinid;
    /**
     * 会员类型0管理员1普通用户
     */
    private Integer type;
    /**
     * 最后登录日期
     */
    private Date lastLogin;
    /**
     * 登录次数
     */
    private Integer count;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 皮肤
     */
    @Size(max = 100, message = "skin最大100字符")
    private String skin;
    /**
     * 备注
     */
    @Size(max = 255, message = "memo最大255字符")
    private String memo;
    /**
     * 关键字
     */
    @Size(max = 255, message = "keyword最大255字符")
    private String keyword;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 排序
     */
    private Integer orderNo;
    /**
     * 删除标记(0正常1删除)
     */
    private Integer delFlag;
    /**
     * 建立者ID
     */
    private Long createId;
    /**
     * 创建时间
     */
    private Date dateCreated;
    /**
     * 更新时间
     */
    private Date dateUpdated;
    /**
     * 用户角色id集合
     */
    private List<Long> roleIdArray;
    /**
     * 用户角色名称集合
     */
    private String roleNames;
    /**
     * 部门id集合
     */
    private List<Long> deptIdArray;
}