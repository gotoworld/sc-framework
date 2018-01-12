package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountSubLoanService;
import com.hsd.account.finance.dto.AccountSubLoanDto;
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

@Api(description = "子账户-P2P网贷")
@RestController
@Slf4j
public class AccountSubLoanController extends BaseController {
    @Autowired
    private IAccountSubLoanService accountSubLoanService;
    private static final String acPrefix = "/api/account/finance/accountSubLoan/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("AccountSubLoanController info.........");
        Response result = new Response(0, "success");
        try {
            AccountSubLoanDto dto = new AccountSubLoanDto(){{
                setAppUserId(userId);
            }};
            result.data = accountSubLoanService.findDataById(dto);
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
    public Response save(@Validated @ModelAttribute AccountSubLoanDto dto, BindingResult bindingResult) {
        log.info("AccountSubLoanController save.........");
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
                result = accountSubLoanService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}