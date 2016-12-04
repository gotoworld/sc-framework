/*	
 * 组织架构_用户  BEAN类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.vo;

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
    @Size(max = 32, message = "id最大32字符")
    private String id;
    /**
     * 员工UserID
     */
    private String userid;
    /**
     * 员工密码
     */
    @Size(max = 64, message = "pwd最大64字符")
    private String pwd;
    /**
     * 成员名称
     */
    @Size(max = 100, message = "name最大100字符")
    private String name;
    /**
     * 工号
     */
    @Size(max = 64, message = "jobNo最大64字符")
    private String jobNo;
    /**
     * 职位
     */
    @Size(max = 64, message = "position最大64字符")
    private String position;
    /**
     * 性别[0男1女]
     */
    @Size(max = 1, message = "gender最大1字符")
    private String gender;
    /**
     * 移动电话
     */
    @Size(max = 50, message = "mobile最大50字符")
    private String mobile;
    /**
     * 固定电话
     */
    @Size(max = 50, message = "tel最大50字符")
    private String tel;
    /**
     * 是否启用启用/禁用成员。1表示启用成员，0表示禁用成员
     */
    @Size(max = 1, message = "enable最大1字符")
    private String enable;
    /**
     * 头像url。注：小图将url最后的"/0"改成"/64"
     */
    @Size(max = 255, message = "avatar最大255字符")
    private String avatar;
    /**
     * 关注状态: 1=已关注，2=已冻结，4=未关注
     */
    @Size(max = 50, message = "status最大50字符")
    private String status;
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
    @NotNull(message = "type不能为空")
    @Size(max = 1, message = "type最大1字符")
    private String type;
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
    @Size(max = 1, message = "state最大1字符")
    private String state;
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
    @Size(max = 100, message = "keyword最大100字符")
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
     * 是否删除(0否1是)
     */
    private String delFlag;
    /**
     * 数据过期时间0:永不过期
     */
    private Long invalidTime;
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
     * 用户角色id集合
     */
    private List<String> roleIdArray;
    /**
     * 用户角色名称集合
     */
    private String roleNames;
    /**
     * 部门id集合
     */
    private List<String> deptIdArray;
}