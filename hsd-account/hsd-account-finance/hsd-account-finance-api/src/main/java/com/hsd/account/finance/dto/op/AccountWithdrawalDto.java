package com.hsd.account.finance.dto.op;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.finance.dto.AccountLogRechargeDto;
import com.hsd.account.finance.dto.AccountLogWithdrawalDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-操作-提现")
public class AccountWithdrawalDto extends AccountLogWithdrawalDto {

}