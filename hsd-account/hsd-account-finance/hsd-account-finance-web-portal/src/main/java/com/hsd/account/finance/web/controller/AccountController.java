package com.hsd.account.finance.web.controller;

import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.dto.AccountBindThirdpartyDto;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.dto.op.AccountFreezeDto;
import com.hsd.account.finance.dto.op.AccountStateDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
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

    @Autowired
    private IIdentityService identityService;

    private static final String acPrefix = "/api/account/finance/account/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("AccountController info.........");
        Response result = new Response(0, "success");
        try {
            AccountDto dto = new AccountDto(){{
                setAppUserId(userId);
            }};
            result.data = accountService.findAccount(dto);
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
        Response result = new Response(0, "success");
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

    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "updateState")
    @ALogOperation(type = "变更", desc = "账户操作-状态变更")
    @ApiOperation(value = "账户操作-状态变更")
    public Response state(@Validated @ModelAttribute AccountStateDto dto, BindingResult bindingResult) {
        log.info("AccountController state.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "state." + dto.toString()))) {
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
                result = accountService.updateState(copyTo(dto,AccountDto.class));
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 实名认证信息
     */
    @RequestMapping(method={RequestMethod.GET},value = acPrefix + "identityInfo/{userId}")
    @ApiOperation(value = "实名认证信息")
    public Response identityInfo(@PathVariable("userId") Long userId) {
        log.info("AccountController identityInfo.........");
        Response result = new Response(0);
        try {

            //获取实名信息
            IdentityDto identityDto = new IdentityDto();
            identityDto.setUserId(userId);
            IdentityDto userIdentity =  identityService.findDataById(identityDto);
            if(userIdentity == null){
                result = Response.error("未找到用户实名认证信息!");
                return result;
            }
            Integer identityState = userIdentity.getState();
            if(identityState == null || identityState.intValue() == 0){
                result = Response.error("实名认证信息处理中!");
                return result;
            }
            if(identityState == null || identityState.intValue() != 1){
                result = Response.error("实名认证未成功!");
                return result;
            }
            result.setData(userIdentity);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "freeze")
    @ALogOperation(type = "变更", desc = "账户操作-冻结/解冻")
    @ApiOperation(value = "账户操作-冻结/解冻")
    @RfAccount2Bean
    public Response freeze(@Validated @ModelAttribute AccountFreezeDto dto, BindingResult bindingResult) {
        log.info("AccountController freeze.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "freeze." + dto.toString()))) {
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
                result = accountService.freeze(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
