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
@ApiModel("支付账户与三方账户绑定 DTO")
public class AccountBindThirdpartyDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("真实姓名")
	 @Size(max=22,message="real_name最大22字符")
     private String realName;
     @ApiModelProperty("身份证号码")
	 @Size(max=19,message="card_no最大19字符")
     private String cardNo;
     @ApiModelProperty("预留手机号码")
     private Integer cellphone;
     @ApiModelProperty("三方账户币种")
	 @NotNull(message="thirdparty_currency不能为空")
     private Integer thirdpartyCurrency;
     @ApiModelProperty("三方账户类型0银行卡")
	 @NotNull(message="thirdparty_type不能为空")
     private Integer thirdpartyType;
     @ApiModelProperty("三方账户组织名称")
	 @NotNull(message="thirdparty_name不能为空")@Size(max=20,message="thirdparty_name最大20字符")
     private String thirdpartyName;
     @ApiModelProperty("三方账户开户机构")
	 @NotNull(message="thirdparty_branch不能为空")@Size(max=32,message="thirdparty_branch最大32字符")
     private String thirdpartyBranch;
     @ApiModelProperty("三方账户账号")
	 @NotNull(message="thirdparty_account不能为空")@Size(max=32,message="thirdparty_account最大32字符")
     private String thirdpartyAccount;
     @ApiModelProperty("三方账号失效日期")
     private Date thirdpartyDateExpiry;
     @ApiModelProperty("状态")
	 @NotNull(message="state不能为空")
     private Integer state;
     @ApiModelProperty("绑定时间")
     private Date dateBind;
     @ApiModelProperty("成功时间")
     private Date dateSuccess;
     @ApiModelProperty("解绑时间")
     private Date dateUnbind;
     @ApiModelProperty("备注")
	 @Size(max=55,message="memo最大55字符")
     private String memo;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}