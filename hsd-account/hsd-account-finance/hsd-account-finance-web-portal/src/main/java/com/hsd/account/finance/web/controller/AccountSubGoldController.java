package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountSubGoldService;
import com.hsd.account.finance.dto.AccountLogDto;
import com.hsd.account.finance.dto.AccountSubGoldDto;
import com.hsd.framework.Response;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "子账户-实物贵金属")
@RestController
@Slf4j
public class AccountSubGoldController extends BaseController {
    @Autowired
    private IAccountSubGoldService accountSubGoldService;
    private static final String acPrefix = "/api/account/finance/accountSubGold/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("AccountSubGoldController info.........");
        Response result = new Response();
        try {
            AccountSubGoldDto dto = new AccountSubGoldDto(){{
                setAppUserId(userId);
            }};
            result.data = accountSubGoldService.findDataById(dto);
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
    public Response open(@PathVariable("userId") Long userId,@RequestParam Long accountType) {
        log.info("AccountSubGoldController open.........");
        Response result = new Response(0,"success");
        try {
            AccountSubGoldDto dto = new AccountSubGoldDto(){{
                setAppUserId(userId);
                setType(accountType);
            }};
            result.data = accountSubGoldService.open(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 买入
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "buyIn")
    @ApiOperation(value = "买入")
    public Response buyIn(@ModelAttribute AccountLogDto dto){
        log.info("AccountSubGoldController buyIn.........");
        Response result = new Response(0,"success");
        try {
            result = accountSubGoldService.buyIn(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 卖出
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "buyOut")
    @ApiOperation(value = "卖出")
    public Response buyOut(@ModelAttribute AccountLogDto dto){
        log.info("AccountSubGoldController buyOut.........");
        Response result = new Response(0,"success");
        try {
            result = accountSubGoldService.buyOut(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}