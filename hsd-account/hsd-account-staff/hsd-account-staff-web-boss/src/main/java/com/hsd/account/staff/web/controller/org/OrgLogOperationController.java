package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.org.IOrgLogOperationService;
import com.hsd.account.staff.dto.org.OrgLogOperationDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
               dto = new OrgLogOperationDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
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
    @RequiresPermissions("orgLogOperation:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("OrgLogOperationController info.........");
        Response result = new Response(0, "success");
        try {
            OrgLogOperationDto dto = new OrgLogOperationDto();
            if (id!=null) {
                dto.setId(id);
                result.data = orgLogOperationService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}