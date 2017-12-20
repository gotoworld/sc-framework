package com.hsd.account.finance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hsd.framework.dto.BaseDto;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-日志-充值记录 DTO")
public class AccountLogRechargeDto extends BaseDto {
     @ApiModelProperty("充值记录id")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("子账户类型")
     private Long accountSubType;
     @ApiModelProperty("子账户id")
     private Long accountSubId;
     @ApiModelProperty("交易订单号")
	 @Size(max=32,message="trade_no最大32字符")
     private String tradeNo;
     @ApiModelProperty("用户名称")
	 @Size(max=22,message="user_name最大22字符")
     private String userName;
     @ApiModelProperty("真实姓名")
	 @Size(max=22,message="real_name最大22字符")
     private String realName;
     @ApiModelProperty("支付方式")
	 @NotNull(message="pay_type不能为空")
     private Integer payType;
     @ApiModelProperty("充值金额")
	 @NotNull(message="money不能为空")
     private BigDecimal money;
     @ApiModelProperty("手续费")
	 @NotNull(message="fee不能为空")
     private BigDecimal fee;
     @ApiModelProperty("到帐金额")
	 @NotNull(message="account_money不能为空")
     private BigDecimal accountMoney;
     @ApiModelProperty("充值状态1申请2成功3失败4撤销")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("充值数据json")
	 @NotNull(message="data不能为空")@Size(max=65535,message="data最大65535字符")
     private String data;
     @ApiModelProperty("三方机构交易码,充值成功后回写,用于对账")
	 @Size(max=36,message="pay_trade_no最大36字符")
     private String payTradeNo;
     @ApiModelProperty("三方回馈")
	 @Size(max=65535,message="pay_result最大65535字符")
     private String payResult;
     @ApiModelProperty("备注")
	 @Size(max=55,message="memo最大55字符")
     private String memo;
     @ApiModelProperty("用户IP")
	 @Size(max=20,message="create_ip最大20字符")
     private String createIp;
     @ApiModelProperty("申请时间")
     private Date dateCreated;
     @ApiModelProperty("更新时间")
     private Date dateUpdated;
     @ApiModelProperty("成功时间")
     private Date dateSuccess;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}