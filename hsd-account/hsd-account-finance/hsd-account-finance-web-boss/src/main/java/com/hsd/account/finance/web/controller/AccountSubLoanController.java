package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountSubLoanService;
import com.hsd.account.finance.dto.AccountSubLoanDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "子账户-P2P网贷")
@RestController
@Slf4j
public class AccountSubLoanController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountSubLoanService accountSubLoanService;
    private static final String acPrefix = "/boss/account/finance/accountSubLoan/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountSubLoan:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountSubLoanDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountSubLoanController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountSubLoanDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            result.data = PageUtil.copy(accountSubLoanService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountSubLoan:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountSubLoanController info.........");
        Response result = new Response();
        try {
            AccountSubLoanDto dto = new AccountSubLoanDto(){{
                setId(id);
            }};
            result.data = accountSubLoanService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}