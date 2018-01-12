package com.hsd.account.staff.web.controller.sys;

import com.hsd.account.staff.api.sys.ISysAppService;
import com.hsd.account.staff.dto.sys.SysAppDto;
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

@Api(description = "应用系统表")
@RestController
@Slf4j
public class SysAppController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private ISysAppService sysAppService;
    private static final String acPrefix = "/boss/account/staff/sys/sysApp/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("sysApp:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  SysAppDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("SysAppController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new SysAppDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            result.data = PageUtil.copy(sysAppService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>回收站。
     */
    @RequiresPermissions("sysApp:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "recycle/{pageNum}")
    @ApiOperation(value = "回收站")
    public Response recycle(@ModelAttribute  SysAppDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("SysAppController recycle.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new SysAppDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            dto.setDelFlag(1);
            result.data = PageUtil.copy(sysAppService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息列表 (未删除)。
     */
//    @RequiresPermissions("sysApp:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@ModelAttribute  SysAppDto dto) {
        log.info("SysAppController list.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                dto = new SysAppDto();
            }
            result.data = sysAppService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("sysApp:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") String id) {
        log.info("SysAppController info.........");
        Response result = new Response(0, "success");
        try {
            if (id == null) {throw new RuntimeException("参数异常!");}
            SysAppDto dto = new SysAppDto(){{
                setId(id);
            
            }};
            result.data = sysAppService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>物理删除。
     */
    @RequiresPermissions("sysApp:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "应用系统表-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") String id) {
        log.info("SysAppController phydel.........");
        Response result = new Response(0, "success");
        try {
           if (id==null) {throw new RuntimeException("参数异常!");}
            SysAppDto dto = new SysAppDto(){{
            setId(id);
           }};
            result.message = sysAppService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>逻辑删除。
     */
    @RequiresPermissions("sysApp:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "应用系统表-逻辑删除")
    @ApiOperation(value = "逻辑删除")
    public Response del(@PathVariable("id") String id) {
        log.info("SysAppController del.........");
        Response result = new Response(0, "success");
        try {
           if (id==null) {throw new RuntimeException("参数异常!");}
            SysAppDto dto = new SysAppDto(){{
            setId(id);
           }};
            result.message = sysAppService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <li>恢复。
     */
    @RequiresPermissions("sysApp:recovery")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recovery/{id}")
    @ALogOperation(type = "恢复", desc = "应用系统表")
    @ApiOperation(value = "恢复")
    public Response recovery(@PathVariable("id") String id) {
        log.info("SysAppController recovery.........");
        Response result = new Response(0, "success");
        try {
            result.message=sysAppService.recoveryDataById(new SysAppDto(){{setId(id);}});
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"sysApp:add", "sysApp:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "应用系统表")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute SysAppDto dto, BindingResult bindingResult) {
        log.info("SysAppController save.........");
        Response result = new Response(0, "success");
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
                result = sysAppService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}