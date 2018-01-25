package com.hsd.account.pay.dto.yee.bind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("确定绑卡 DTO")
public class ConfirmBindBankCardDto extends YeeBaseDto {//https://ok.yeepay.com/payapi/api/tzt/confirmbindbankcard
    @NotNull(message = "requestid 不能为空")
    @ApiModelProperty("商户生成的唯一绑卡请求号，最长 50 位")
    private String requestid;
    @ApiModelProperty("短信验证码 6 位数字")
    private String validatecode;
}
