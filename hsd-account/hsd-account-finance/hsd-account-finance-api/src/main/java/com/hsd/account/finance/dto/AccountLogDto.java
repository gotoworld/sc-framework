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
@ApiModel("账户-日志 DTO")
public class AccountLogDto extends FinanceBaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("支付流水号")
	 @NotNull(message="serial_number不能为空")@Size(max=32,message="serial_number最大32字符")
     private String serialNumber;
     @ApiModelProperty("交易金额")
	 @NotNull(message="amount不能为空")
     private BigDecimal amount;
     @ApiModelProperty("货币类型0人民币")
	 @NotNull(message="currency不能为空")
     private Integer currency;
     @ApiModelProperty("操作者域用户id")
	 @NotNull(message="op_app_user_id不能为空")
     private Long opAppUserId;
     @ApiModelProperty("操作者账户类型")
	 @NotNull(message="op_account_type不能为空")
     private Long opAccountType;
     @ApiModelProperty("操作者账户id")
	 @NotNull(message="op_account_id不能为空")
     private Long opAccountId;
     @ApiModelProperty("操作者子账户类型")
     private Long opAccountSubType;
     @ApiModelProperty("操作者子账户id")
     private Long opAccountSubId;
     @ApiModelProperty("对方域用户id")
	 @NotNull(message="other_app_user_id不能为空")
     private Long otherAppUserId;
     @ApiModelProperty("对方账户类型")
	 @NotNull(message="other_account_type不能为空")
     private Long otherAccountType;
     @ApiModelProperty("对方账户id")
	 @NotNull(message="other_account_id不能为空")
     private Long otherAccountId;
     @ApiModelProperty("对方子账户类型")
     private Long otherAccountSubType;
     @ApiModelProperty("对方子账户id")
     private Long otherAccountSubId;
     @ApiModelProperty("外部交易时间")
	 @Size(max=32,message="out_trade_date最大32字符")
     private String outTradeDate;
     @ApiModelProperty("外部订单号")
	 @Size(max=32,message="out_order_no最大32字符")
     private String outOrderNo;
     @ApiModelProperty("外部订单名称")
	 @Size(max=55,message="out_order_name最大55字符")
     private String outOrderName;
     @ApiModelProperty("交易状态")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("业务类型:1投资扣费,2收款,3充值,4提现,6奖励,7转账,8投资奖励,9融资,10扣费,11充值手续费,12提现撤销,13提现手续费,14借款管理费,15投资冻结资金,16投资撤回解冻资金,17投资奖励扣除,19还款,20内部调账,21结息,22原交易退款,23原交易撤销")
	 @NotNull(message="trade_type不能为空")
     private Integer tradeType;
     @ApiModelProperty("业务类型名称")
	 @Size(max=32,message="trade_type_name最大32字符")
     private String tradeTypeName;
     @ApiModelProperty("交易渠道")
	 @Size(max=32,message="trade_channel最大32字符")
     private String tradeChannel;
     @ApiModelProperty("交易机构")
     private Long tradeAgency;
     @ApiModelProperty("交易说明")
	 @NotNull(message="trade_desc不能为空")@Size(max=80,message="trade_desc最大80字符")
     private String tradeDesc;
     @ApiModelProperty("支付方式")
	 @NotNull(message="pay_type不能为空")
     private Integer payType;
     @ApiModelProperty("支付机构交易码,充值成功后回写,用于对账")
	 @Size(max=36,message="pay_trade_no最大36字符")
     private String payTradeNo;
     @ApiModelProperty("支付回馈")
	 @Size(max=255,message="pay_result最大255字符")
     private String payResult;
     @ApiModelProperty("流水方向0存入1支出")
	 @NotNull(message="is_direction不能为空")
     private Integer isDirection;
     @ApiModelProperty("冲正标记0否1是")
     private Integer isCorrect;
     @ApiModelProperty("红字标记0否1是")
     private Integer isRed;
     @ApiModelProperty("会计科目代码")
	 @Size(max=32,message="fin_subject_code最大32字符")
     private String finSubjectCode;
     @ApiModelProperty("凭证id")
	 @Size(max=32,message="fin_voucher_id最大32字符")
     private String finVoucherId;
     @ApiModelProperty("交易数据json")
	 @Size(max=255,message="data最大255字符")
     private String data;
     @ApiModelProperty("交易操作人id")
     private Long createId;
     @ApiModelProperty("交易操作人IP")
	 @NotNull(message="create_ip不能为空")@Size(max=20,message="create_ip最大20字符")
     private String createIp;
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