package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hsd7 on 2018/1/2.
 */
@Api("支付账户")
@RestController
@Slf4j
public class AccountController extends BaseController{


    @Autowired
    private IAccountService accountService;
    private static final String acPrefix = "/portal/account/finance/account/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("AccountController info.........");
        Response result = new Response();
        try {
            AccountDto dto = new AccountDto(){{
                setAppUserId(userId);
            }};
            result.data = accountService.findDataByUserId(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 开户信息保存
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "save")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated AccountDto dto, BindingResult bindingResult) {
        log.info("AccountController save.........");
        Response result = new Response("success");
        try {
            if (dto == null)throw new RuntimeException("参数异常");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + dto.getToken()))) {
                throw new RuntimeException("请不要重复提交!");
            }
            if (bindingResult.hasErrors()) {
                String errorMsg = "";
                List<ObjectError> errorList = bindingResult.getAllErrors();
                for (ObjectError error : errorList) {
                    errorMsg += (error.getDefaultMessage()) + ";";
                }
                result = Response.error(errorMsg);
            } else {
                result = accountService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 开户信息保存
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.PUT},value = acPrefix + "updateState/{userId}")
    @ApiOperation(value = "状态变更")
    public Response updateState(@ModelAttribute AccountDto dto) {
        log.info("AccountController updateState.........");
        Response result = new Response("success");
        try {
            accountService.updateState(dto);

        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}
