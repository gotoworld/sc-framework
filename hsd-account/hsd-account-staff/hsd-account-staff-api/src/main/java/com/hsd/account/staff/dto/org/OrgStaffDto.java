package com.hsd.account.staff.dto.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgStaffDto extends BaseDto {
     /**ID*/
     private Long id;
     /**员工账号*/
	 @NotNull(message="account不能为空")@Size(max=55,message="account最大55字符")
     private String account;
     /**员工密码*/
	 @Size(max=64,message="pwd最大64字符")
     private String pwd;
     /**成员名称*/
	 @NotNull(message="name不能为空")@Size(max=55,message="name最大55字符")
     private String name;
     /**工号*/
     private String jobNo;
     /**性别[0男1女3保密]*/
     private Integer gender;
     /**手机号*/
	 @Size(max=24,message="cellphone最大24字符")
     private String cellphone;
     /**头像url*/
	 @Size(max=255,message="avatar最大255字符")
     private String avatar;
     /**邮箱*/
	 @Size(max=64,message="email最大64字符")
     private String email;
     /**员工类型0管理员1普通员工*/
     private Integer type;
     /**最后登录日期*/
     private Date lastLogin;
     /**登录次数*/
     private Integer count;
     /**状态0在职1离职2试用期*/
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



     /**员工角色名称集合*/
     private String roleNames;
     private String oldpwd;
     private String newpwd;
     private String confirmpwd;
     /**机构id*/
     private Long orgId;
     /**超级管理员标记*/
     private Integer iissuperman;
     /**员工权限,匹配字符集合*/
     private Set<String> authorizationInfoPerms;
     /**员工角色,匹配字符集合*/
     private Set<String> authorizationInfoRoles;

     private List<Long> roleIds;
     private List<Long> orgIds;

     /**员工级别*/
     private String level;
     /**上级领导id*/
     private Long leadership;

     /**用户所在组织*/
     private List<OrgInfoDto> orgInfos;
     /** app员工id */
     private Long appStaffId;
     /**系统名称*/
     private String appName;
     /**系统Id*/
     private String appId;
}