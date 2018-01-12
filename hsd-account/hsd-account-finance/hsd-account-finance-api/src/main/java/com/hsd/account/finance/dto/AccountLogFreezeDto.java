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
@ApiModel("账户-日志-资金冻结记录 DTO")
public class AccountLogFreezeDto extends FinanceBaseDto {
     @ApiModelProperty("主键")
     @NotNull(message="id不能为空")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("操作类型")
     private Integer actionType;
     @ApiModelProperty("金额")
     @NotNull(message="amount不能为空")
     private BigDecimal amount;
     @ApiModelProperty("冻结类型")
     private Integer freezeType;
     @ApiModelProperty("冻结总额")
     private BigDecimal freezeTotalAmount;
     @ApiModelProperty("创建时间")
     private Date dateCreated;
     @ApiModelProperty("操作人id")
     private Long createId;
     @ApiModelProperty("子账户类型")
     private Long accountSubType;
     @ApiModelProperty("子账户id")
     private Long accountSubId;
     @ApiModelProperty("外部业务id")
	 @Size(max=32,message="out_ref_id最大32字符")
     private String outRefId;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}