package com.hsd.account.staff.entity.org;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgLogLogin extends BaseEntity {
     /**id*/
     private Long id;
     /**员工ID*/
     private Long staffId;
     /**员工名称*/
     private String staffName;
     /**类型0登录1登出*/
     private Integer type;
     /**IP地址*/
     private String ipAddr;
     /**MAC地址*/
     private String deviceMac;
     /**创建时间*/
     private Date dateCreated;
     /**app用户id*/
     private Long appUserId;
     /**系统id*/
     private String appId;
     /**系统名称*/
     private String appName;
}