package com.hsd.util.dto.msg;

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
public class MsgVerifyDto extends BaseDto {
    /**id*/
    private Long id;
    /**验证码*/
    @NotNull(message="verify_code不能为空")@Size(max=15,message="verify_code最大15字符")
    private String verifyCode;
    /**消息地址*/
    @NotNull(message="sms_address不能为空")@Size(max=55,message="sms_address最大55字符")
    private String smsAddress;
    /**类型0手机1邮件*/
    @NotNull(message="sms_type不能为空")
    private Integer smsType;
    /**创建时间*/
    private Date dateCreated;
    /**有效时长(秒)*/
    @NotNull(message="data_expire不能为空")
    private Integer dataExpire;
    /**使用时间*/
    private Date dateUsing;
    /**IP地址*/
    @NotNull(message="ip_address不能为空")@Size(max=25,message="ip_address最大25字符")
    private String ipAddress;
    /**是否使用0否1是*/
    @NotNull(message="isUsed不能为空")
    private Integer isUsed;

    /**预定发送时间*/
    private Date dateSend;
    /**实际发送时间*/
    private Date dateActual;
    /**实际发送次数*/
    private Integer sendCount;
    /**发送状态0：未发送 1：发送中 2：已发送*/
    private Integer state;

    /**图片验证码Id*/
    private String imgCaptchaId;
    /**图片验证码code*/
    private String imgCaptchaCode;
    /**图片验证码 验证成功删除*/
    private Boolean imgCaptchaDel;
}