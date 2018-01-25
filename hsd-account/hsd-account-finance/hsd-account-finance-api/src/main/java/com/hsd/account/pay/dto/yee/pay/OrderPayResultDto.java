package com.hsd.account.pay.dto.yee.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("支付请求-返回 DTO")
public class OrderPayResultDto extends YeeBaseDto {
    /** 商户订单号 样返回商户所传 */
    private String orderid;
    /** 易宝交易流水号 */
    private String yborderid;
    /** 交易金额 以分为单位 */
    private Integer amount;
}
