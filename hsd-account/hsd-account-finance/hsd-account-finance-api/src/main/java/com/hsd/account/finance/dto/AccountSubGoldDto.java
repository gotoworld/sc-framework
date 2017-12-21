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
@ApiModel("子账户-实物贵金属 DTO")
public class AccountSubGoldDto extends BaseDto {
     @ApiModelProperty("贵金属账户id")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("子账户类型")
	 @NotNull(message="type不能为空")
     private Long type;
     @ApiModelProperty("子账户名称")
	 @Size(max=32,message="alias_name最大32字符")
     private String aliasName;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("金总克重")
     private BigDecimal totalGold;
     @ApiModelProperty("购买次数")
     private Integer buyCount;
     @ApiModelProperty("购买总额")
     private BigDecimal totalAmount;
     @ApiModelProperty("累计收益")
     private BigDecimal cumulativeIncome;
     @ApiModelProperty("首次购买时间")
     private Date dateBuyFirst;
     @ApiModelProperty("是否购买过新手金")
     private Integer isBuyNovice;
     @ApiModelProperty("账户状态")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("校验码md5,安全控制值编码")
	 @Size(max=32,message="check_value最大32字符")
     private String checkValue;
     @ApiModelProperty("开户日期")
     private Date dateOpen;
     @ApiModelProperty("销户日期")
     private Date dateClose;
     @ApiModelProperty("账户模板id")
	 @NotNull(message="account_template_id不能为空")
     private Long accountTemplateId;
     @ApiModelProperty("模板值（json形式保存）")
	 @NotNull(message="expand_json不能为空")@Size(max=255,message="expand_json最大255字符")
     private String expandJson;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}