package com.hsd.account.actor.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends BaseDto {
    public enum userType{
        USER(1,"会员"),CHANNEL(2,"渠道商");
        private userType(Integer _code,String _name){
            this.code=_code;
            this.name=_name;
        }
        @Getter
        Integer code;
        @Getter
        String name;
    }
     /**客户id*/
     private Long id;
     /**登录名*/
	 @Size(max=64,message="account最大64字符")
     private String account;
     /**客户名*/
	 @Size(max=64,message="name最大64字符")
     private String name;
     /**登录密码*/
	@Size(max=64,message="pwd最大64字符")
     private String pwd;
    /**交易密码*/
	 @Size(max=64,message="trade_pwd最大64字符")
     private String tradePwd;
    /**客户email*/
	 @Size(max=100,message="email最大100字符")
     private String email;
    /**性别[0男1女2保密]*/
     private Integer gender;
    /**手机号码*/
	 @Size(max=18,message="cellphone最大18字符")
     private String cellphone;
    /**客户类型 1会员2渠道商*/
     private Integer type;
    /**状态 1、启用 2、禁用 3.黑名单 默认1*/
     private Integer state;
    /**注册渠道 默认为other*/
	 @Size(max=50,message="register_channel最大50字符")
     private String registerChannel;
    /**标签*/
	 @Size(max=2000,message="tags最大2000字符")
     private String tags;
    /**注册时间*/
     private Date dateCreated;

    /**风险评估*/
    private String riskAssess;
    /**真实姓名*/
    private String realName;
    /**证件编号*/
    private String credentialNumber;
    /**验证码*/
    private String captcha;
    /**新密码*/
    private String newpwd;
    /**密码确认*/
    private String confirmpwd;
    /**协议确认0否1是*/
    private Integer agreement;
    /**手机号0否1是*/
    private Integer isCellphoneYN;
    /**邮箱0否1是*/
    private Integer isEmailYN;
    /**交易密码0否1是*/
    private Integer isTradePwdYN;
    /**实名认证0否1是*/
    private Integer isIdentityYN;
    /**风险评测0否1是*/
    private Integer isRiskAssessYN;
}