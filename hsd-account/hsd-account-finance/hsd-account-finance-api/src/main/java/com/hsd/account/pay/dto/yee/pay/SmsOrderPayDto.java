package com.hsd.account.pay.dto.yee.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("支付请求-发送短信 DTO")
public class SmsOrderPayDto extends OrderPayDto {//https://ok.yeepay.com/payapi/api/tzt/pay/bind/request
}
