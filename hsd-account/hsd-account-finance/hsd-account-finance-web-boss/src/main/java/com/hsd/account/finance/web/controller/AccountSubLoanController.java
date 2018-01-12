package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountService;
import com.hsd.account.finance.api.IAccountSubLoanService;
import com.hsd.account.finance.dto.AccountDto;
import com.hsd.account.finance.dto.AccountSubLoanDto;
import com.hsd.account.finance.dto.op.AccountFreezeDto;
import com.hsd.account.finance.dto.op.AccountReverseDto;
import com.hsd.account.finance.dto.op.AccountStateDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
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
public class AccountSubLoanController extends FinanceBaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountSubLoanService accountSubLoanService;
    @Autowired
    private IAccountService accountService;
    private static final String acPrefix = "/boss/account/finance/accountSubLoan/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountSubLoan:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountSubLoanDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountSubLoanController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountSubLoanDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setAppUserId(getAppUserId(dto.getAppId(),dto.getUserId()));
            result.data = PageUtil.copy(accountSubLoanService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountSubLoan:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountSubLoanController info.........");
        Response result = new Response();
        try {
            result.data = accountSubLoanService.findDataById(new AccountSubLoanDto(){{setId(id);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("accountSubLoan:op:freeze")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "op/freeze")
    @ALogOperation(type = "变更", desc = "网贷账户-冻结")
    @ApiOperation(value = "网贷账户-冻结")
    @RfAccount2Bean
    public Response freeze(@Validated @ModelAttribute AccountFreezeDto dto, BindingResult bindingResult) {
        log.info("AccountSubLoanController freeze.........");
        Response result = new Response(0,"success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "freeze." + dto.getToken()))) {
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
                dto.setActionType(0);//0冻结1解冻
                dto.setBizAccountType(2);//0网贷账户1黄金账户2网贷账户
                result = accountService.freeze(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("accountSubLoan:op:unfreeze")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "op/unfreeze")
    @ALogOperation(type = "变更", desc = "网贷账户-解冻")
    @ApiOperation(value = "网贷账户-解冻")
    @RfAccount2Bean
    public Response unfreeze(@Validated @ModelAttribute AccountFreezeDto dto, BindingResult bindingResult) {
        log.info("AccountSubLoanController unfreeze.........");
        Response result = new Response(0,"success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "freeze." + dto.getToken()))) {
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
                dto.setActionType(1);//0冻结1解冻
                dto.setBizAccountType(2);//0网贷账户1黄金账户2网贷账户
                result = accountService.freeze(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("accountSubLoan:op:reverse")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "op/reverse")
    @ALogOperation(type = "变更", desc = "网贷账户-冲正/抵扣")
    @ApiOperation(value = "网贷账户-冲正/抵扣")
    @RfAccount2Bean
    public Response reverse(@Validated @ModelAttribute AccountReverseDto dto, BindingResult bindingResult) {
        log.info("AccountSubLoanController reverse.........");
        Response result = new Response(0,"success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "reverse." + dto.getToken()))) {
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
                dto.setBizAccountType(2);//0网贷账户1黄金账户2网贷账户
                result = accountService.reverse(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("accountSubLoan:op:state")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "op/state")
    @ALogOperation(type = "变更", desc = "网贷账户-状态变更")
    @ApiOperation(value = "网贷账户-状态变更")
    @RfAccount2Bean
    public Response state(@Validated @ModelAttribute AccountStateDto dto, BindingResult bindingResult) {
        log.info("AccountSubLoanController state.........");
        Response result = new Response(0,"success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "state." + dto.getToken()))) {
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
                dto.setBizAccountType(2);//0网贷账户1黄金账户2网贷账户
                result = accountService.updateState(copyTo(dto,AccountDto.class));
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}