package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountBindThirdpartyService;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "支付账户与三方账户绑定")
@RestController
@Slf4j
public class AccountBindThirdpartyController extends BaseController {
    @Autowired
    private IAccountBindThirdpartyService accountBindThirdpartyService;
    private static final String acPrefix = "/portal/account/finance/accountBindThirdparty/";


    /**
     * 绑卡
     * @param name 姓名
     * @param cardNo 卡号
     * @param certNo 身份证号码
     * @param phone 银行预留手机号
     * @return
     */
    @RequiresPermissions("accountBindThirdparty:bind")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "bind/{userId}")
    @ApiOperation(value = "绑卡")
    public Response bindCard(@PathVariable("userId") Long userId,@RequestParam Long accountId,@RequestParam String name,@RequestParam String cardNo,@RequestParam String certNo,@RequestParam String phone) {
        log.info("AccountBindThirdpartyController info.........");
        Response result = new Response("success");
        try {
            return accountBindThirdpartyService.bindCard(userId,accountId,name,cardNo,certNo,phone);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountBindThirdparty:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info")
    @ApiOperation(value = "信息详情")
    public Response info(AccountBindThirdpartyDto dto) {
        log.info("AccountBindThirdpartyController info.........");
        Response result = new Response("success");
        try {
            result.data = accountBindThirdpartyService.findDataByUserId(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * 解绑
     * @param userId
     * @param accountId
     * @param cardNo
     * @return
     */
    @RequiresPermissions("accountBindThirdparty:unbind")
    @RequestMapping(method = RequestMethod.PUT, value = acPrefix + "unbind/{userId}")
    @ApiOperation(value = "绑卡")
    public Response unbindCard(@PathVariable("userId") Long userId,@RequestParam Long accountId,@RequestParam String cardNo) {
        log.info("AccountBindThirdpartyController info.........");
        Response result = new Response("success");
        try {
            return accountBindThirdpartyService.unbindCard(userId,accountId,cardNo);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}