package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountSubLoanService;
import com.hsd.account.finance.dto.AccountSubLoanDto;
import com.hsd.framework.Response;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "子账户-P2P网贷")
@RestController
@Slf4j
public class AccountSubLoanController extends BaseController {
    @Autowired
    private IAccountSubLoanService accountSubLoanService;
    private static final String acPrefix = "/api/account/finance/accountSubLoan/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("AccountSubLoanController info.........");
        Response result = new Response();
        try {
            AccountSubLoanDto dto = new AccountSubLoanDto(){{
                setAppUserId(userId);
            }};
            result.data = accountSubLoanService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 开户信息保存
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "open/{userId}")
    @ApiOperation(value = "信息保存")
    public Response open(@PathVariable("userId") Long userId, @RequestParam Long accountType) {
        log.info("AccountSubLoanController open.........");
        Response result = new Response(0,"success");
        try {
            AccountSubLoanDto dto = new AccountSubLoanDto(){{
                setAppUserId(userId);
                setAccountType(accountType);
            }};
            result.data = accountSubLoanService.open(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}