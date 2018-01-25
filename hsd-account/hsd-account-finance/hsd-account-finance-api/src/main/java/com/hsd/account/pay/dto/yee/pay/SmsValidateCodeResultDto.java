package com.hsd.account.pay.dto.yee.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
/** 发送短信验证码-返回 DTO*/
public class SmsValidateCodeResultDto extends YeeBaseDto {
    /** 商户订单号 商户生成的唯一订单号，最长 50 位 */
    private String orderid;
    /** 易宝交易流水号 */
    private String yborderid;
    /** 交易金额 以分为单位 */
    private Integer amount;
    /** 用户标识识,最长 50 位，商户生成的用户唯一标 */
    private String identityid;
    /** 卡号前 6 位 */
    private String card_top;
    /** 卡号后 4 位 */
    private String card_last;
    /** 支付状态 0：失败 1：成功 2：撤销 */
    private Integer status;
}
