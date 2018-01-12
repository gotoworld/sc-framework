package com.hsd.account.finance.dto.op;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.account.finance.dto.AccountLogDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hsd7 on 2018/1/12.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("账户-操作-转账")
public class AccountTransferDto extends AccountLogDto {
}
