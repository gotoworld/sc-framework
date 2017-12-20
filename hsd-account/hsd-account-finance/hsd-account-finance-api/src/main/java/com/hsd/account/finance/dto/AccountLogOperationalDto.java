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
@ApiModel("用户账户-操作日志 DTO")
public class AccountLogOperationalDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("用户名称")
	 @Size(max=25,message="user_name最大25字符")
     private String userName;
     @ApiModelProperty("支付账户id")
	 @NotNull(message="account_id不能为空")
     private Long accountId;
     @ApiModelProperty("子账户类型")
     private Long accountSubType;
     @ApiModelProperty("子账户id")
     private Long accountSubId;
     @ApiModelProperty("流水类型")
	 @NotNull(message="type不能为空")
     private Integer type;
     @ApiModelProperty("交易时间")
     private Date dateCreated;
     @ApiModelProperty("交易数据json")
	 @Size(max=65535,message="data最大65535字符")
     private String data;
     @ApiModelProperty("交易说明")
	 @NotNull(message="memo不能为空")@Size(max=80,message="memo最大80字符")
     private String memo;
     @ApiModelProperty("IP地址")
	 @NotNull(message="ip不能为空")@Size(max=20,message="ip最大20字符")
     private String ip;
     @ApiModelProperty("BI时间戳")
     private Date biUpdateTs;

}