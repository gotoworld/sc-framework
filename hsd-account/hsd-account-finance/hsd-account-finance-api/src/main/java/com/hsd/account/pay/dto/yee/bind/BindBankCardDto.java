package com.hsd.account.pay.dto.yee.bind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.pay.dto.yee.YeeBaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("绑卡请求 DTO")
public class BindBankCardDto extends YeeBaseDto {//https://ok.yeepay.com/payapi/api/tzt/invokebindbankcard
    @NotNull(message = "identityid 不能为空")
    @ApiModelProperty("用户标识")
    private String identityid;
    @NotNull(message = "identitytype 不能为空")
    @ApiModelProperty("用户标识类0:IMEI型 1：MAC 地址 2：用户 ID 3：用户 Email 4：用户手机号 5：用户身份证号 6：用户纸质订单协议号")
    private Integer identitytype;
    @NotNull(message = "requestid 不能为空")
    @ApiModelProperty("绑卡请求号")
    private String requestid;
    @NotNull(message = "cardno 不能为空")
    @ApiModelProperty("银行卡号")
    private String cardno;
    @NotNull(message = "idcardtype 不能为空")
    @ApiModelProperty("证件类型")
    private String idcardtype;
    @NotNull(message = "idcardno 不能为空")
    @ApiModelProperty("证件号")
    private String idcardno;
    @NotNull(message = "username 不能为空")
    @ApiModelProperty("持卡人姓名")
    private String username;
    @NotNull(message = "phone 不能为空")
    @ApiModelProperty("银行预留手机号")
    private String phone;
    @ApiModelProperty("建议短验发")
    private String advicesmstype;
    @ApiModelProperty("用户注册手")
    private String registerphone;
    @ApiModelProperty("用户注册日")
    private String registerdate;
    @ApiModelProperty("用户注册 ip")
    private String registerip;
    @ApiModelProperty("用户注册证")
    private String registeridcardtype;
    @ApiModelProperty("用户注册证件号")
    private String registeridcardno;
    @ApiModelProperty("用户注册联")
    private String registercontact;
    @ApiModelProperty("用户使用的")
    private String os;
    @ApiModelProperty("设备唯一标")
    private String imei;
    @NotNull(message = "userip 不能为空")
    @ApiModelProperty("用户请求 ip")
    private String userip;
    @ApiModelProperty("浏览器信息")
    private String ua;
}
