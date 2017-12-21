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
@ApiModel("账户-日志-提现流水 DTO")
public class AccountLogWithdrawalDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("流水号")
	 @NotNull(message="serial_number不能为空")@Size(max=36,message="serial_number最大36字符")
     private String serialNumber;
     @ApiModelProperty("真实姓名")
	 @NotNull(message="real_name不能为空")@Size(max=12,message="real_name最大12字符")
     private String realName;
     @ApiModelProperty("提现银行")
	 @NotNull(message="bank_name不能为空")@Size(max=30,message="bank_name最大30字符")
     private String bankName;
     @ApiModelProperty("提现支行")
	 @NotNull(message="bank_branch不能为空")@Size(max=30,message="bank_branch最大30字符")
     private String bankBranch;
     @ApiModelProperty("提现账户")
	 @NotNull(message="bank_account不能为空")@Size(max=19,message="bank_account最大19字符")
     private String bankAccount;
     @ApiModelProperty("提现金额")
	 @NotNull(message="money不能为空")
     private BigDecimal money;
     @ApiModelProperty("提现手续费")
	 @NotNull(message="fee不能为空")
     private BigDecimal fee;
     @ApiModelProperty("到账金额")
	 @NotNull(message="arrive_money不能为空")
     private BigDecimal arriveMoney;
     @ApiModelProperty("提现机构交易码,成功后回写,用于对账")
	 @Size(max=36,message="bank_trade_no最大36字符")
     private String bankTradeNo;
     @ApiModelProperty("提现回馈")
	 @Size(max=255,message="bank_result最大255字符")
     private String bankResult;
     @ApiModelProperty("提现时间")
     private Date dateCreated;
     @ApiModelProperty("审核时间")
     private Date dateAudited;
     @ApiModelProperty("状态1待审核,2提现成功,3提现失败,4撤销提现")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("审核人")
	 @NotNull(message="auditor_id不能为空")
     private Long auditorId;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}