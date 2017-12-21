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
@ApiModel("子账户-P2P网贷 DTO")
public class AccountSubLoanDto extends BaseDto {
     @ApiModelProperty("网贷子账户id")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("账户类型")
	 @NotNull(message="account_type不能为空")
     private Long accountType;
     @ApiModelProperty("0投资/1贷款")
     private Integer balanceDirection;
     @ApiModelProperty("子账户名称")
	 @Size(max=32,message="alias_name最大32字符")
     private String aliasName;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("币种")
	 @NotNull(message="currency不能为空")
     private Integer currency;
     @ApiModelProperty("账户总额")
	 @NotNull(message="total_money不能为空")
     private BigDecimal totalMoney;
     @ApiModelProperty("可用余额")
	 @NotNull(message="available_money不能为空")
     private BigDecimal availableMoney;
     @ApiModelProperty("冻结资金")
	 @NotNull(message="freeze_money不能为空")
     private BigDecimal freezeMoney;
     @ApiModelProperty("待收资金")
	 @NotNull(message="due_in不能为空")
     private BigDecimal dueIn;
     @ApiModelProperty("待还资金")
	 @NotNull(message="stay_still不能为空")
     private BigDecimal stayStill;
     @ApiModelProperty("待收利息")
	 @NotNull(message="stay_interest不能为空")
     private BigDecimal stayInterest;
     @ApiModelProperty("已赚利息")
	 @NotNull(message="make_interest不能为空")
     private BigDecimal makeInterest;
     @ApiModelProperty("已赚奖励")
	 @NotNull(message="make_reward不能为空")
     private BigDecimal makeReward;
     @ApiModelProperty("逾期金额")
	 @NotNull(message="overdue不能为空")
     private BigDecimal overdue;
     @ApiModelProperty("账户状态")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("账户模板id")
	 @NotNull(message="account_template_id不能为空")
     private Long accountTemplateId;
     @ApiModelProperty("模板值（json形式保存）")
	 @NotNull(message="expand_json不能为空")@Size(max=255,message="expand_json最大255字符")
     private String expandJson;
     @ApiModelProperty("校验码md5,安全控制值编码")
	 @Size(max=32,message="check_value最大32字符")
     private String checkValue;
     @ApiModelProperty("开户日期")
     private Date dateOpen;
     @ApiModelProperty("销户日期")
     private Date dateClose;
     @ApiModelProperty("信用金额")
     private BigDecimal creditAmount;
     @ApiModelProperty("抵押冻结金")
     private BigDecimal depositAmount;
     @ApiModelProperty("风控冻结金")
     private BigDecimal ctuAmount;
     @ApiModelProperty("系统冻结金")
     private BigDecimal sysAmount;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}