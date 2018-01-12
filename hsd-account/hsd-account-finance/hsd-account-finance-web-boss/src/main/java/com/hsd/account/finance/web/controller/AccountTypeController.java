package com.hsd.account.finance.web.controller;

import com.hsd.account.finance.api.IAccountTypeService;
import com.hsd.account.finance.dto.AccountTypeDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
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

@Api(description = "账户类型")
@RestController
@Slf4j
public class AccountTypeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAccountTypeService accountTypeService;
    private static final String acPrefix = "/boss/account/finance/accountType/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("accountType:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  AccountTypeDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountTypeController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountTypeDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            result.data = PageUtil.copy(accountTypeService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>回收站 (已删除)。
     */
    @RequiresPermissions("accountType:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "recycle/{pageNum}")
    @ApiOperation(value = "回收站")
    public Response recycle(@ModelAttribute  AccountTypeDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AccountTypeController recycle.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountTypeDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(1);
            result.data = PageUtil.copy(accountTypeService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("accountType:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@ModelAttribute  AccountTypeDto dto) {
        log.info("AccountTypeController list.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new AccountTypeDto();
            dto.setDelFlag(0);
            result.data = accountTypeService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("accountType:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("AccountTypeController info.........");
        Response result = new Response();
        try {
            AccountTypeDto dto = new AccountTypeDto(){{
                setId(id);
              setDelFlag(0);
            }};
            result.data = accountTypeService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("accountType:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "账户类型-逻辑删除")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("AccountTypeController del.........");
        Response result = new Response();
        try {
            if (id==null) {throw new RuntimeException("参数异常!");}
            AccountTypeDto dto = new AccountTypeDto(){{
                setId(id);
            }};
            result.message = accountTypeService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <li>恢复。
     */
    @RequiresPermissions("accountTemplate:recovery")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recovery/{id}")
    @ALogOperation(type = "恢复", desc = "账户模板-恢复数据")
    @ApiOperation(value = "恢复")
    public Response recovery(@PathVariable("id") Long id) {
        log.info("AccountTypeController recovery.........");
        Response result = new Response();
        try {
            result.message=accountTypeService.recoveryDataById(new AccountTypeDto(){{setId(id);}});
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>物理删除。
     */
    @RequiresPermissions("accountType:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "账户类型-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") Long id) {
        log.info("AccountTypeController phydel.........");
        Response result = new Response();
        try {
            if (id==null) {throw new RuntimeException("参数异常!");}
            AccountTypeDto dto = new AccountTypeDto(){{
                setId(id);
            }};
            result.message = accountTypeService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"accountType:add", "accountType:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "账户类型")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute AccountTypeDto dto, BindingResult bindingResult) {
        log.info("AccountTypeController save.........");
        Response result = new Response();
        try {
            if (dto == null) return Response.error("参数获取异常!");
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
                if(dto.getIsOverdraft()==null) dto.setIsOverdraft(0);
                if(dto.getIsPay()==null) dto.setIsPay(0);
                if(dto.getIsRecharge()==null) dto.setIsRecharge(0);
                if(dto.getIsWithdraw()==null) dto.setIsWithdraw(0);
                if(dto.getIsShiftIn()==null) dto.setIsShiftIn(0);
                if(dto.getIsShiftOut()==null) dto.setIsShiftOut(0);
                result = accountTypeService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}