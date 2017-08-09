package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.org.IOrgLogOperationService;
import com.hsd.account.staff.dto.org.OrgLogOperationDto;
import com.hsd.framework.Response;
import com.hsd.framework.PageUtil;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "组织架构_员工操作日志")
@RestController
@Slf4j
public class OrgLogOperationController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IOrgLogOperationService orgLogOperationService;
    private static final String acPrefix = "/boss/account/staff/org/orgLogOperation/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("orgLogOperation:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  OrgLogOperationDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("OrgLogOperationController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new OrgLogOperationDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.set(0);
            // 信息列表
            result.data = PageUtil.copy(orgLogOperationService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("orgLogOperation:edit")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("OrgLogOperationController info.........");
        Response result = new Response();
        try {
            OrgLogOperationDto dto = new OrgLogOperationDto();
            if (id!=null) {
                dto.setId(id);
                dto.set(0);
                result.data = orgLogOperationService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"orgLogOperation:add", "orgLogOperation:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "组织架构_员工操作日志")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute OrgLogOperationDto dto, BindingResult bindingResult) {
        log.info("OrgLogOperationController save.........");
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
                result = orgLogOperationService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}