package com.hsd.account.actor.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends BaseDto {
     /**用户id*/
     private Long id;
     /**登录名*/
	 @NotNull(message="account不能为空")@Size(max=64,message="account最大64字符")
     private String account;
     /**用户名*/
	 @NotNull(message="name不能为空")@Size(max=64,message="name最大64字符")
     private String name;
     /**登录密码*/
	 @NotNull(message="pwd不能为空")@Size(max=64,message="pwd最大64字符")
     private String pwd;
     /**交易密码*/
	 @Size(max=64,message="trade_pwd最大64字符")
     private String tradePwd;
     /**用户email*/
	 @Size(max=100,message="email最大100字符")
     private String email;
     /**性别[0男1女2保密]*/
     private Integer gender;
     /**手机号码*/
	 @Size(max=18,message="cellphone最大18字符")
     private String cellphone;
     /**用户类型 1会员2渠道商*/
     private Integer type;
     /**是否禁用 1、启用 2、禁用 3.黑名单 默认1*/
     private Integer state;
     /**注册渠道 默认为other*/
	 @Size(max=50,message="register_channel最大50字符")
     private String registerChannel;
     /**标签*/
	 @Size(max=2000,message="tags最大2000字符")
     private String tags;
     /**注册时间*/
     private Date dateCreated;

}