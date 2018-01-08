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

/**
 * Created by hsd7 on 2018/1/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("扣款 DTO")
public class DeductMoneyDto extends BaseDto {
    @NotNull(message="app_user_id不能为空")
    private Long appUserId;

    @NotNull(message="account_id不能为空")
    private Long accountId;

    @NotNull(message="deduct_money不能为空")
    private BigDecimal deductMoney;

    @ApiModelProperty("三方账户账号")
    @NotNull(message="thirdparty_account不能为空")@Size(max=32,message="thirdparty_account最大32字符")
    private String cardNo;
}
