package com.hsd.account.actor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushDto extends BaseDto {
    /**前缀*/
     private String prefix;
    /**用户id*/
     private String userid;
    /**用户账号*/
     private String account;
    /**手机号码*/
     private String cellphone;
    /**邮箱*/
    private String email;
    /**验证码*/
    private String captcha;
}