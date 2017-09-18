package com.hsd.util.entity.msg;

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
public class MsgVerify extends BaseEntity {
     /**id*/
     private Long id;
     /**验证码*/
     private String verifyCode;
     /**消息地址*/
     private String smsAddress;
     /**类型0手机1邮件*/
     private Integer smsType;
     /**创建时间*/
     private Date dateCreated;
     /**有效时长(秒)*/
     private Integer dataExpire;
     /**使用时间*/
     private Date dateUsing;
     /**IP地址*/
     private String ipAddress;
     /**是否使用0否1是*/
     private Integer state;
}