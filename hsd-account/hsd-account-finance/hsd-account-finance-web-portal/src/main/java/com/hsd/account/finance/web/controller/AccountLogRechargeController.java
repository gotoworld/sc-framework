package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountLogRechargeService;
import com.hsd.account.finance.dto.AccountLogRechargeDto;
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

@Api(description = "账户-日志-充值记录")
@RestController
@Slf4j
public class AccountLogRechargeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountLogRechargeService accountLogRechargeService;
    private static final String acPrefix = "/api/account/finance/accountLogRecharge/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountLogRechargeDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountLogRechargeController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new AccountLogRechargeDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            result.data = PageUtil.copy(accountLogRechargeService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountLogRechargeController info.........");
        Response result = new Response(0, "success");
        try {
            result.data = accountLogRechargeService.findDataById(new AccountLogRechargeDto(){{setId(id);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}