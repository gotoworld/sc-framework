package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountSubGoldService;
import com.hsd.account.finance.dto.AccountSubGoldDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "子账户-实物贵金属")
@RestController
@Slf4j
public class AccountSubGoldController extends FinanceBaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountSubGoldService accountSubGoldService;
    private static final String acPrefix = "/boss/account/finance/accountSubGold/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountSubGold:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountSubGoldDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountSubGoldController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new AccountSubGoldDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setAppUserId(getAppUserId(dto.getAppId(),dto.getUserId()));
            result.data = PageUtil.copy(accountSubGoldService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountSubGold:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountSubGoldController info.........");
        Response result = new Response(0, "success");
        try {
            result.data = accountSubGoldService.findDataById(new AccountSubGoldDto(){{setId(id);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}