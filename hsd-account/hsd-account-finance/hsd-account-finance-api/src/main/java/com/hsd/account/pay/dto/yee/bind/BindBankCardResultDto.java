package com.hsd.account.pay.dto.yee.bind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
/** 绑卡请求-返回 DTO */
public class BindBankCardResultDto extends YeeBaseDto {
     /** 绑卡请求号 */
    private String requestid;
     /** 短信验证码发送方 YEEPAY：易宝发送 BANK：银行发送 MERCHANT：商户发送 */
    private String codesender;
     /** 短信验证码 为商户发送短验时会返回易宝生成的验证码 */
    private String smscode;
}
