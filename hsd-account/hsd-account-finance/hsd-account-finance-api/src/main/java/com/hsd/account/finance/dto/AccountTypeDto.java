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
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户类型 DTO")
public class AccountTypeDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("名称")
	 @NotNull(message="name不能为空")@Size(max=25,message="name最大25字符")
     private String name;
     @ApiModelProperty("账户性质0个人1企业")
	 @NotNull(message="is_enterprise不能为空")
     private Integer isEnterprise;
     @ApiModelProperty("允许充值")
	 @NotNull(message="is_recharge不能为空")
     private Integer isRecharge;
     @ApiModelProperty("允许提现")
	 @NotNull(message="is_withdraw不能为空")
     private Integer isWithdraw;
     @ApiModelProperty("允许透支")
	 @NotNull(message="is_overdraft不能为空")
     private Integer isOverdraft;
     @ApiModelProperty("允许支付")
	 @NotNull(message="is_pay不能为空")
     private Integer isPay;
     @ApiModelProperty("允许转入")
	 @NotNull(message="is_shift_in不能为空")
     private Integer isShiftIn;
     @ApiModelProperty("允许转出")
	 @NotNull(message="is_shift_out不能为空")
     private Integer isShiftOut;
     @ApiModelProperty("安全保障等级")
	 @NotNull(message="security_level不能为空")
     private Integer securityLevel;
     @ApiModelProperty("简介")
	 @Size(max=255,message="brief_info最大255字符")
     private String briefInfo;
     @ApiModelProperty("排序")
     private Integer orderNo;
     @ApiModelProperty("删除标记(0正常1删除)")
     private Integer delFlag;
     @ApiModelProperty("建立者id")
     private Long createId;
     @ApiModelProperty("创建时间")
     private Date dateCreated;
     @ApiModelProperty("更新时间")
     private Date dateUpdated;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}