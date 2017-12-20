package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountLogCapitalService;
import com.hsd.account.finance.dto.AccountLogCapitalDto;
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

@Api(description = "账户-日志-资金流水")
@RestController
@Slf4j
public class AccountLogCapitalController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountLogCapitalService accountLogCapitalService;
    private static final String acPrefix = "/boss/account/finance/accountLogCapital/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountLogCapital:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountLogCapitalDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountLogCapitalController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountLogCapitalDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            result.data = PageUtil.copy(accountLogCapitalService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountLogCapital:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountLogCapitalController info.........");
        Response result = new Response();
        try {
            if (id!=null) {throw new RuntimeException("参数异常!");}
            AccountLogCapitalDto dto = new AccountLogCapitalDto(){{
                setId(id);
              setDelFlag(0);
            }};
            result.data = accountLogCapitalService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}