package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountSubGoldService;
import com.hsd.account.finance.dto.AccountSubGoldDto;
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
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "save")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated AccountSubGoldDto dto, BindingResult bindingResult) {
        log.info("AccountSubGoldController save.........");
        Response result = new Response();
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
                result = accountSubGoldService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}