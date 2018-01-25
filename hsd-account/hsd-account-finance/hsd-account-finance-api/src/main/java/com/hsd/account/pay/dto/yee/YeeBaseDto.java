package com.hsd.account.pay.dto.yee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class YeeBaseDto {
    @NotNull(message = "merchantaccount 不能为空")
    @ApiModelProperty("商户编号")
    private String merchantaccount;
    @NotNull(message = "sign 不能为空")
    @ApiModelProperty("商户使用自己生成的 RSA 私钥对参数除“sign”外的其他参数进行字母排序后串成的字符串进行签名")
    private String sign;

    @ApiModelProperty("错误码,其他详见附录：错误码说明")
    private String error_code;
    @ApiModelProperty("错误信息,详见附录：错误码说明")
    private String error_msg;
}
