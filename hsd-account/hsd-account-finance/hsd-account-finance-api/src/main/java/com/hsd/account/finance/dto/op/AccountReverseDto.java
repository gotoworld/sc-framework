package com.hsd.account.finance.dto.op;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.finance.dto.AccountLogFreezeDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-操作-冲正/抵扣")
public class AccountReverseDto extends AccountLogFreezeDto {

}