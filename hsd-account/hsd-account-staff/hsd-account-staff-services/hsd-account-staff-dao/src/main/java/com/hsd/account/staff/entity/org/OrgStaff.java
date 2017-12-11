package com.hsd.account.staff.entity.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgStaff extends BaseEntity {
     /**ID*/
     private Long id;
     /**员工账号*/
     private String account;
     /**成员名称*/
     private String name;
     /**工号*/
     private String jobNo;
     /**性别[0男1女3保密]*/
     private Integer gender;
     /**手机号*/
     private String cellphone;
     /**头像url*/
     private String avatar;
     /**邮箱*/
     private String email;
     /**员工类型0管理员*/
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
     /**员工密码*/
     private String pwd;

     /**员工密码(旧)*/
     private String oldpwd;
     /**员工密码(新)*/
     private String confirmpwd;
     /**机构id*/
     private Long orgId;

     /**员工级别*/
     private String level;
     /**上级领导id*/
     private Long leadership;

     /**用户所在组织*/
     private List<OrgInfo> orgInfos;
     /** app员工id */
     private Long appStaffId;
     /**系统名称*/
     private String appName;
     /**系统Id*/
     private String appId;
}