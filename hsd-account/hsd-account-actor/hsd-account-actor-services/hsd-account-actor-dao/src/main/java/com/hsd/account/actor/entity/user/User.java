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
public class User extends BaseEntity {
     /**客户id*/
     private Long id;
     /**登录名*/
     private String account;
     /**客户名*/
     private String name;
     /**登录密码*/
     private String pwd;
     /**交易密码*/
     private String tradePwd;
     /**客户email*/
     private String email;
     /**性别[0男1女2保密]*/
     private Integer gender;
     /**手机号码*/
     private String cellphone;
     /**客户类型 1会员2渠道商*/
     private Integer type;
     /**状态 1、启用 2、禁用 3.黑名单 默认1*/
     private Integer state;
     /**注册渠道 默认为other*/
     private String registerChannel;
     /**标签*/
     private String tags;
     /**注册时间*/
     private Date dateCreated;
     /**最后登录日期*/
     private Date lastLogin;
     /**登录次数*/
     private Integer count;
}