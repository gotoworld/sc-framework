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
@ApiModel("账户-日志-资金流水 DTO")
public class AccountLogCapitalDto extends BaseDto {
     @ApiModelProperty("主键")
     private Long id;
     @ApiModelProperty("交易编号")
	 @Size(max=32,message="trade_code最大32字符")
     private String tradeCode;
     @ApiModelProperty("交易记录id")
     private Long accountTradeLogId;
     @ApiModelProperty("交易金额")
	 @NotNull(message="amount不能为空")
     private BigDecimal amount;
     @ApiModelProperty("货币类型")
     private Integer currency;
     @ApiModelProperty("域用户id")
	 @NotNull(message="app_user_id不能为空")
     private Long appUserId;
     @ApiModelProperty("支付账户id")
     private Long accountId;
     @ApiModelProperty("子账户类型")
     private Long accountSubType;
     @ApiModelProperty("子账户id")
     private Long accountSubId;
     @ApiModelProperty("账户余额")
     private BigDecimal accountBalance;
     @ApiModelProperty("账户类型")
     private Long accountType;
     @ApiModelProperty("对方账户类型")
     private Long otherAccountType;
     @ApiModelProperty("对方账户id")
     private Long otherAccountId;
     @ApiModelProperty("对方子账户类型")
     private Long otherAccountSubType;
     @ApiModelProperty("对方子账户id")
     private Long otherAccountSubId;
     @ApiModelProperty("外部订单号")
	 @Size(max=32,message="out_order_no最大32字符")
     private String outOrderNo;
     @ApiModelProperty("外部交易时间")
	 @Size(max=32,message="out_trade_date最大32字符")
     private String outTradeDate;
     @ApiModelProperty("交易机构")
     private Long tradeAgency;
     @ApiModelProperty("交易说明")
	 @NotNull(message="trade_desc不能为空")@Size(max=80,message="trade_desc最大80字符")
     private String tradeDesc;
     @ApiModelProperty("交易数据json")
	 @Size(max=65535,message="data最大65535字符")
     private String data;
     @ApiModelProperty("流水方向0存入1支出")
	 @NotNull(message="is_direction不能为空")
     private Integer isDirection;
     @ApiModelProperty("冲正标记0否1是")
     private Integer isCorrect;
     @ApiModelProperty("红字标记0否1是")
     private Integer isRed;
     @ApiModelProperty("删除标记0否1是")
     private Integer delFlag;
     @ApiModelProperty("交易操作人id")
     private Long createId;
     @ApiModelProperty("IP")
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