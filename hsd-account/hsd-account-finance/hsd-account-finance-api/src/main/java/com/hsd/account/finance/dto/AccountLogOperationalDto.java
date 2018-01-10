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
@ApiModel("用户账户-操作日志 DTO")
public class AccountLogOperationalDto extends BaseDto {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("域用户id")
    private Long appUserId;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("支付账户id")
    @NotNull(message = "account_id不能为空")
    private Long accountId;
    @ApiModelProperty("子账户类型")
    private Long accountSubType;
    @ApiModelProperty("子账户id")
    private Long accountSubId;
    @ApiModelProperty("操作类型 操作类型 1冻结,2解冻,3状态变更")
    private Integer type;
    @ApiModelProperty("交易数据json")
    private String data;
    @ApiModelProperty("交易说明")
    @NotNull(message = "memo不能为空")
    private String memo;
    @ApiModelProperty("操作人id")
    private Long createId;
    @ApiModelProperty("IP地址")
    private String createIp;
    @ApiModelProperty("交易时间")
    private Date dateCreated;
    @ApiModelProperty("BI时间戳")
    private Date biUpdateTs;

}