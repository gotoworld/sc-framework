package com.hsd.account.staff.web.controller.sys;

import com.hsd.account.staff.api.sys.ISysDomainService;
import com.hsd.account.staff.dto.sys.SysDomainDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "系统_域")
@RestController
@Slf4j
public class SysDomainController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ISysDomainService sysDomainService;
    private static final String acPrefix = "/boss/account/staff/sys/sysDomain/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("sysDomain:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute SysDomainDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("SysDomainController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new SysDomainDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(sysDomainService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("sysDomain:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@ModelAttribute SysDomainDto dto) {
        log.info("SysDomainController list.........");
        Response result = new Response();
        try {
            if (dto == null) {
                dto = new SysDomainDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setDelFlag(0);
            // 信息列表
            result.data = sysDomainService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("sysDomain:edit")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{code}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("code") String code) {
        log.info("SysDomainController info.........");
        Response result = new Response();
        try {
            SysDomainDto dto = new SysDomainDto();
            if (code!=null) {
                dto.setCode(code);
                dto.setDelFlag(0);
                result.data = sysDomainService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>删除。
     */
   @RequiresPermissions("sysDomain:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{code}")
    @ALogOperation(type = "删除", desc = "系统_域")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("code") String code) {
        log.info("SysDomainController del.........");
        Response result = new Response();
        try {
            SysDomainDto dto = new SysDomainDto();
            dto.setCode(code);
            result.message = sysDomainService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"sysDomain:add", "sysDomain:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "系统_域")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute SysDomainDto dto, BindingResult bindingResult) {
        log.info("SysDomainController save.........");
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
                result = sysDomainService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}