package com.hsd.account.pay.dto.yee.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("支付接口-不发送短验 DTO")
public class OrderPayDto extends YeeBaseDto {//https://ok.yeepay.com/payapi/api/tzt/directbindpay
    @NotNull(message = "orderid 不能为空")
    @ApiModelProperty("商户订单号 商户生成的唯一订单号，最长 50 位")
    private String orderid;
    @NotNull(message = "transtime 不能为空")
    @ApiModelProperty("交易时间 时间戳，例如：1361324896，精确 到秒")
    private Integer transtime;
    @ApiModelProperty("交易币种 默认 156 人民币(当前仅支持人民币)")
    private Integer currency;
    @NotNull(message = "amount 不能为空")
    @ApiModelProperty("交易金额 以'分'为单位的整型")
    private Integer amount;
    @NotNull(message = "productname 不能为空")
    @ApiModelProperty("商品名称 最长 50 位")
    private String productname;
    @ApiModelProperty("商品描述 最长 200 位")
    private String productdesc;
    @NotNull(message = "identityid 不能为空")
    @ApiModelProperty("用户标识 最长 50 位，商户生成的用户唯一标识")
    private String identityid;
    @NotNull(message = "identitytype 不能为空")
    @ApiModelProperty("用户标识类型 0：IMEI,1：MAC 地址,2：用户 ID,3：用户 Email,4：用户手机号,5：用户身份证号,6：用户纸质订单协议号")
    private Integer identitytype;
    @NotNull(message = "card_top 不能为空")
    @ApiModelProperty("卡号前 6 位 ")
    private String card_top;
    @NotNull(message = "card_last 不能为空")
    @ApiModelProperty("卡号后 4 位 ")
    private String card_last;
    @ApiModelProperty("订单有效期 单位：分钟，例如：60，表示订单有 效期为 60 分钟 说明：商户传订单有效期则需要校验，不传默认 24 小时，传要求参数在 5 分钟-100 天内；")
    private Integer orderexpdate;
    @NotNull(message = "callbackurl 不能为空")
    @ApiModelProperty("回调地址 用来通知商户支付结果")
    private String callbackurl;
    @ApiModelProperty("国际移动设备身份码的缩写，国际移动装备辨识码，是由 15 位数字组成的'电子串号'，它与每台手机一一对应")
    private String imei;
    @NotNull(message = "userip 不能为空")
    @ApiModelProperty("用户请求 用户支付时使用的网络终端 IP")
    private String userip;
    @ApiModelProperty("用户使用的 浏览器信息")
    private String ua;
}
