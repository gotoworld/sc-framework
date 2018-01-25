package com.hsd.account.pay.dto.yee.bind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
/** 确定绑卡-返回 DTO */
public class ConfirmBindBankCardResultDto extends YeeBaseDto {//https://ok.yeepay.com/payapi/api/tzt/confirmbindbankcard
    /** 绑卡请求号,原样返回商户所传 */
    private String requestid;
    /** 银行编码,详见银行编码列表 */
    private String bankcode;
    /** 卡号前 6 位 */
    private String card_top;
    /** 卡号后 4 位 */
    private String card_last;
}
