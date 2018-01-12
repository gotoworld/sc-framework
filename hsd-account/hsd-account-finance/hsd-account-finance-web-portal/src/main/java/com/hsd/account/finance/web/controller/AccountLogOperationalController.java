package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountLogOperationalService;
import com.hsd.account.finance.dto.AccountLogOperationalDto;
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

@Api(description = "用户账户-操作日志")
@RestController
@Slf4j
public class AccountLogOperationalController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountLogOperationalService accountLogOperationalService;
    private static final String acPrefix = "/api/account/finance/accountLogOperational/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountLogOperational:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountLogOperationalDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountLogOperationalController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new AccountLogOperationalDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            result.data = PageUtil.copy(accountLogOperationalService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountLogOperational:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountLogOperationalController info.........");
        Response result = new Response(0, "success");
        try {
            result.data = accountLogOperationalService.findDataById(new AccountLogOperationalDto(){{setId(id); }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}