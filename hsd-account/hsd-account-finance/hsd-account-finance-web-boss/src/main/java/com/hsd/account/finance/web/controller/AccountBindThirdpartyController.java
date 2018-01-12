package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountBindThirdpartyService;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "支付账户与三方账户绑定")
@RestController
@Slf4j
public class AccountBindThirdpartyController extends FinanceBaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountBindThirdpartyService accountBindThirdpartyService;
    private static final String acPrefix = "/boss/account/finance/accountBindThirdparty/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountBindThirdparty:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountBindThirdpartyDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountBindThirdpartyController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new AccountBindThirdpartyDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setAppUserId(getAppUserId(dto.getAppId(),dto.getUserId()));
            result.data = PageUtil.copy(accountBindThirdpartyService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountBindThirdparty:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountBindThirdpartyController info.........");
        Response result = new Response(0, "success");
        try {
            result.data = accountBindThirdpartyService.findDataById(new AccountBindThirdpartyDto(){{setId(id);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}