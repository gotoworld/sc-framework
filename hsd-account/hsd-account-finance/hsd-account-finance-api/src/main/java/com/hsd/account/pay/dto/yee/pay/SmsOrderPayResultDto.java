package com.hsd.account.pay.dto.yee.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("支付请求-发送短信-返回 DTO")
public class SmsOrderPayResultDto extends YeeBaseDto {
    /** 商户订单号 原样返回商户所传 */
    private String orderid;
    /** 手机号 */
    private String phone;
    /** 短信确认 0：建议不需要进行短信校验 1：建议需要进行短信校验 */
    private Integer smsconfirm;
    /** 短信验证码发送 YEEPAY：易宝发送方 BANK：银行发送 */
    private String codesender;
}
