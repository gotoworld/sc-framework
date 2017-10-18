package com.hsd.account.staff.web.controller.sys;

import com.hsd.account.staff.api.sys.ISysVariableService;
import com.hsd.account.staff.dto.sys.SysVariableDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
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

@Api(description = "系统_数据字典表")
@RestController
@Slf4j
public class SysVariableController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ISysVariableService sysVariableService;
    private static final String acPrefix = "/boss/account/staff/sys/sysVariable/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("sysVariable:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  SysVariableDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("SysVariableController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new SysVariableDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(sysVariableService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("sysVariable:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list/{code}")
    @ApiOperation(value = "信息列表")
    public Response list(@PathVariable("code") String code) {
        log.info("SysVariableController list.........");
        Response result = new Response();
        try {
            SysVariableDto dto = new SysVariableDto();
            dto.setCode(code);
            // 信息列表
            result.data = sysVariableService.findChildDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息树json。
     */
    @RequiresPermissions("sysVariable:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"tree")
    @ResponseBody
    @ApiOperation(value = "信息树")
    public Response jsonTree() {
        log.info("SysVariableController jsonTree.........");
        Response result=new Response();
        try {
            result.data=sysVariableService.findDataIsTree(new SysVariableDto());
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("sysVariable:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("SysVariableController info.........");
        Response result = new Response();
        try {
            SysVariableDto dto = new SysVariableDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = sysVariableService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>删除。
     */
   @RequiresPermissions("sysVariable:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "系统_数据字典表")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("SysVariableController del.........");
        Response result = new Response();
        try {
            SysVariableDto dto = new SysVariableDto();
            dto.setId(id);
            result.message = sysVariableService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"sysVariable:add", "sysVariable:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "系统_数据字典表")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute SysVariableDto dto, BindingResult bindingResult) {
        log.info("SysVariableController save.........");
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
                result = sysVariableService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}