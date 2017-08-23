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
     /**用户id*/
     private Long id;
     /**登录名*/
     private String account;
     /**用户名*/
     private String name;
     /**登录密码*/
     private String pwd;
     /**交易密码*/
     private String tradePwd;
     /**用户email*/
     private String email;
     /**性别[0男1女2保密]*/
     private Integer gender;
     /**手机号码*/
     private String cellphone;
     /**用户类型 1会员2渠道商*/
     private Integer type;
     /**是否禁用 1、启用 2、禁用 3.黑名单 默认1*/
     private Integer state;
     /**注册渠道 默认为other*/
     private String registerChannel;
     /**标签*/
     private String tags;
     /**注册时间*/
     private Date dateCreated;
}