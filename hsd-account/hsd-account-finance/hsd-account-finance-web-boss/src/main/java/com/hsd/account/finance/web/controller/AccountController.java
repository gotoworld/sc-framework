package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dto.AccountDto;
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

@Api(description = "支付账户")
@RestController
@Slf4j
public class AccountController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountService accountService;
    private static final String acPrefix = "/boss/account/finance/account/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("account:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            result.data = PageUtil.copy(accountService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("account:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountController info.........");
        Response result = new Response();
        try {
            AccountDto dto = new AccountDto(){{
                setId(id);
            }};
            result.data = accountService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}