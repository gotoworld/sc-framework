package com.hsd.account.finance.dto.op;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.finance.dto.AccountLogFreezeDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-操作-冲正/抵扣 DTO")
public class AccountStateDto extends AccountLogFreezeDto {

}