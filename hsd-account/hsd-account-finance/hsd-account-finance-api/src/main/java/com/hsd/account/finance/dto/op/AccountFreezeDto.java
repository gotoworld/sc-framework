package com.hsd.account.finance.dto.op;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.finance.dto.AccountLogFreezeDto;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-操作-资金冻结记录")
public class AccountFreezeDto extends AccountLogFreezeDto {
    @ApiModelProperty("冻结类型")
    @NotNull(message="freezeType不能为空")
    private Integer freezeType;
}