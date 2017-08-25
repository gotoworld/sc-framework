package com.hsd.account.actor.entity.user;

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
public class UserLoginLog extends BaseEntity {
     /**id*/
     private Long id;
     /**客户ID*/
     private Long userId;
     /**员工名称*/
     private String userName;
     /**类型0登录1登出*/
     private Integer type;
     /**IP地址*/
     private String ipAddr;
     /**MAC地址*/
     private String deviceMac;
     /**创建时间*/
     private Date dateCreated;
}