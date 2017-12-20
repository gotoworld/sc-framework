package com.hsd.account.finance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-日志-交易记录 DTO")
public class AccountLogTransactionDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("支付流水号")
	 @Size(max=32,message="serial_number最大32字符")
     private String serialNumber;
     @ApiModelProperty("外部交易时间")
	 @Size(max=32,message="out_trade_date最大32字符")
     private String outTradeDate;
     @ApiModelProperty("交易编号")
	 @Size(max=32,message="trade_code最大32字符")
     private String tradeCode;
     @ApiModelProperty("交易金额")
	 @NotNull(message="trade_amount不能为空")
     private BigDecimal tradeAmount;
     @ApiModelProperty("货币类型")
     private Integer currency;
     @ApiModelProperty("冻结状态")
     private Integer isFreeze;
     @ApiModelProperty("转出账户类型")
     private Long outAccountType;
     @ApiModelProperty("转出账户id")
     private Long outAccountId;
     @ApiModelProperty("转出子账户类型")
     private Long outAccountSubType;
     @ApiModelProperty("转出子账户id")
     private Long outAccountSubId;
     @ApiModelProperty("转入账户类型")
     private Long inAccountType;
     @ApiModelProperty("转入账户id")
     private Long inAccountId;
     @ApiModelProperty("转入子账户类型")
     private Long inAccountSubType;
     @ApiModelProperty("转入子账户id")
     private Long inAccountSubId;
     @ApiModelProperty("操作人id")
	 @NotNull(message="action_app_user_id不能为空")
     private Long actionAppUserId;
     @ApiModelProperty("交易状态")
     private Integer state;
     @ApiModelProperty("交易渠道")
	 @Size(max=32,message="trade_channel最大32字符")
     private String tradeChannel;
     @ApiModelProperty("交易机构")
     private Long tradeAgency;
     @ApiModelProperty("外部订单号")
	 @Size(max=32,message="out_order_no最大32字符")
     private String outOrderNo;
     @ApiModelProperty("外部订单名称")
	 @Size(max=55,message="out_order_name最大55字符")
     private String outOrderName;
     @ApiModelProperty("交易说明")
	 @NotNull(message="trade_desc不能为空")@Size(max=80,message="trade_desc最大80字符")
     private String tradeDesc;
     @ApiModelProperty("交易数据json")
	 @Size(max=65535,message="data最大65535字符")
     private String data;
     @ApiModelProperty("业务类型:1投资扣费,2收款,3充值,4提现,6奖励,7转账,8投资奖励,9融资,10扣费,11充值手续费,12提现撤销,13提现手续费,14借款管理费,15投资冻结资金,16投资撤回解冻资金,17投资奖励扣除,19还款,20内部调账,21结息,22原交易退款,23原交易撤销")
	 @NotNull(message="biz_type不能为空")
     private Integer bizType;
     @ApiModelProperty("业务类型名称")
	 @Size(max=32,message="biz_type_name最大32字符")
     private String bizTypeName;
     @ApiModelProperty("业务ID")
	 @Size(max=32,message="biz_id最大32字符")
     private String bizId;
     @ApiModelProperty("业务描述")
	 @Size(max=55,message="biz_name最大55字符")
     private String bizName;
     @ApiModelProperty("相关交易id")
	 @Size(max=32,message="ref_trade_id最大32字符")
     private String refTradeId;
     @ApiModelProperty("会计科目代码")
	 @Size(max=32,message="fin_subject_code最大32字符")
     private String finSubjectCode;
     @ApiModelProperty("凭证id")
	 @Size(max=32,message="fin_voucher_id最大32字符")
     private String finVoucherId;
     @ApiModelProperty("交易时间")
     private Date dateCreated;
     @ApiModelProperty("最后修改时间")
     private Date dateUpdated;
     @ApiModelProperty("备注")
	 @Size(max=55,message="memo最大55字符")
     private String memo;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}