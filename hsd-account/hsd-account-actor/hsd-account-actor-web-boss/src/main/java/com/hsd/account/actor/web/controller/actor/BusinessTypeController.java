package com.hsd.account.actor.web.controller.actor;

import com.hsd.account.actor.api.actor.IBusinessTypeService;
import com.hsd.account.actor.dto.actor.BusinessTypeDto;
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

@Api(description = "业务类型")
@RestController
@Slf4j
public class BusinessTypeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IBusinessTypeService businessTypeService;
    private static final String acPrefix = "/boss/account/actor/actor/businessType/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("businessType:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  BusinessTypeDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("BusinessTypeController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new BusinessTypeDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(businessTypeService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("businessType:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("BusinessTypeController info.........");
        Response result = new Response();
        try {
            BusinessTypeDto dto = new BusinessTypeDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = businessTypeService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>删除。
     */
   @RequiresPermissions("businessType:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "业务类型")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("BusinessTypeController del.........");
        Response result = new Response();
        try {
            BusinessTypeDto dto = new BusinessTypeDto();
            dto.setId(id);
            result.message = businessTypeService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"businessType:add", "businessType:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "业务类型")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute BusinessTypeDto dto, BindingResult bindingResult) {
        log.info("BusinessTypeController save.........");
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
                result = businessTypeService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}