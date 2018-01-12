package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.ITransactionService;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.account.finance.dto.DeductMoneyDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by hsd7 on 2018/1/4.
 */
@Api("交易")
@RestController
@Slf4j
public class TransactionController extends BaseController {

    private static final String acPrefix = "/api/account/finance/transaction/";

    @Autowired
    private ITransactionService transactionService;

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "deduct/{userId}")
    @ApiOperation(value = "扣款")
    public Response deduct(@ModelAttribute DeductMoneyDto dto) {
        log.info("TransactionController deduct.........");
        Response result = new Response(0,"success");
        try {
            transactionService.deduct(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
