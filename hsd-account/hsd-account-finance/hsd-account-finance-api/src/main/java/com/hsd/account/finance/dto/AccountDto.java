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
@ApiModel("支付账户 DTO")
public class AccountDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("域用户id,安全控制值")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("账户类型,安全控制值")
	 @NotNull(message="account_type不能为空")
     private Long accountType;
     @ApiModelProperty("账户名称")
	 @Size(max=32,message="alias_name最大32字符")
     private String aliasName;
     @ApiModelProperty("账户币种,安全控制值")
	 @NotNull(message="currency不能为空")
     private Integer currency;
     @ApiModelProperty("账户总额,安全控制值")
	 @NotNull(message="total_money不能为空")
     private BigDecimal totalMoney;
     @ApiModelProperty("可用余额,安全控制值")
	 @NotNull(message="available_money不能为空")
     private BigDecimal availableMoney;
     @ApiModelProperty("冻结资金,安全控制值")
	 @NotNull(message="freeze_money不能为空")
     private BigDecimal freezeMoney;
     @ApiModelProperty("冻结资金,安全控制值")
	 @NotNull(message="in_float_money不能为空")
     private BigDecimal inFloatMoney;
     @ApiModelProperty("账户状态0生效1冻结2注销")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("账户模板id")
	 @NotNull(message="account_template_id不能为空")
     private Long accountTemplateId;
     @ApiModelProperty("模板值（json形式保存）")
	 @NotNull(message="expand_json不能为空")@Size(max=65535,message="expand_json最大65535字符")
     private String expandJson;
     @ApiModelProperty("开户时间")
     private Date dateCreated;
     @ApiModelProperty("更新时间")
     private Date dateUpdated;
     @ApiModelProperty("校验码md5,安全控制值编码")
	 @Size(max=32,message="check_value最大32字符")
     private String checkValue;
     @ApiModelProperty("充值次数")
     private Integer rechargeCount;
     @ApiModelProperty("充值总额")
     private BigDecimal rechargeTotalAmount;
     @ApiModelProperty("首次充值时间")
     private Date dateRechargeFirst;
     @ApiModelProperty("提现次数")
     private Integer withdrawCount;
     @ApiModelProperty("提现总额")
     private BigDecimal withdrawTotalAmount;
     @ApiModelProperty("客户注册时间")
     private Date dateUserRegister;
     @ApiModelProperty("会计科目代码")
	 @Size(max=32,message="fin_subject_code最大32字符")
     private String finSubjectCode;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}