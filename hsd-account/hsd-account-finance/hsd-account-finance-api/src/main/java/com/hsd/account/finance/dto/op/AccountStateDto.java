package com.hsd.account.finance.dto.op;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.finance.dto.AccountLogFreezeDto;
import com.hsd.framework.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-操作-状态变更")
public class AccountStateDto extends BaseDto {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("域用户id,安全控制值")
    @NotNull(message="appUserId不能为空")
    private Long appUserId;
    @ApiModelProperty("账户类型,安全控制值")
    @NotNull(message="accountType不能为空")
    private Long accountType;
    @ApiModelProperty("子账户类型")
    private Long accountSubType;
    @ApiModelProperty("子账户id")
    private Long accountSubId;
    @ApiModelProperty("账户状态")
    @NotNull(message="state不能为空")
    private Integer state;
}