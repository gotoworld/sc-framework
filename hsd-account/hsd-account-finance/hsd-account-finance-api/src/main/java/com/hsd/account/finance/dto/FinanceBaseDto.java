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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinanceBaseDto extends BaseDto {
    @ApiModelProperty("业务账户[0资金账户1黄金账户2网贷账户]")
    private Integer bizAccountType;
}