package com.hsd.account.pay.dto.yee.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("发送短信验证码 DTO")
public class SmsValidateCodeDto extends YeeBaseDto {//https://ok.yeepay.com/payapi/api/tzt/pay/confirm/validatecode
    @NotNull(message = "orderid 不能为空")
    @ApiModelProperty("商户订单号 商户生成的唯一订单号，最长 50 位")
    private String orderid;
    @ApiModelProperty("短信校验码 测试环境下不会真实发送短信验证码，默认为“123456”")
    private String validatecode;
}
