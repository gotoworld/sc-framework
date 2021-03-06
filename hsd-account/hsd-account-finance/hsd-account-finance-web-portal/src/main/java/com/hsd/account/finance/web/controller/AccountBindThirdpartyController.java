package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountBindThirdpartyService;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.framework.Response;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "支付账户与三方账户绑定")
@RestController
@Slf4j
public class AccountBindThirdpartyController extends BaseController {
    @Autowired
    private IAccountBindThirdpartyService accountBindThirdpartyService;
    private static final String acPrefix = "/api/account/finance/accountBindThirdparty/";


    /**
     * 绑卡
     * @param userId
     * @param accountId
     * @param name
     * @param cardNo 卡号
     * @param certNo 身份证号码
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST}, value = acPrefix + "bindCard/{userId}")
    @ApiOperation(value = "绑卡")
    public Response bindCard(@PathVariable("userId") Long userId,@RequestParam Long accountId,@RequestParam String name,
                             @RequestParam String cardNo,@RequestParam String certNo,@RequestParam String phone) {
        log.info("AccountBindThirdpartyController bindCard.........");
        Response result = new Response(0,"success");
        try {
            AccountBindThirdpartyDto dto = new AccountBindThirdpartyDto();
            dto.setAppUserId(userId);
            dto.setAccountId(accountId);
            dto.setRealName(name);
            dto.setCardNo(certNo);
            dto.setThirdpartyAccount(cardNo);
            dto.setCellphone(phone);
            return accountBindThirdpartyService.bindCard(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info")
    @ApiOperation(value = "信息详情")
    public Response info(@ModelAttribute AccountBindThirdpartyDto dto) {
        log.info("AccountBindThirdpartyController info.........");
        Response result = new Response();
        try {
            result.data = accountBindThirdpartyService.findDataByUserId(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * 解绑
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = acPrefix + "unbindCard")
    @ApiOperation(value = "解绑")
    public Response unbindCard(@ModelAttribute AccountBindThirdpartyDto dto) {
        log.info("AccountBindThirdpartyController unbindCard.........");
        Response result = new Response(0,"success");
        try {
            return accountBindThirdpartyService.unbindCard(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}