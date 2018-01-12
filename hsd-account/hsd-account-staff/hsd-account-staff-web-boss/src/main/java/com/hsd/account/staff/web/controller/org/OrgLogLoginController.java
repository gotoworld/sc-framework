package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.org.IOrgLogLoginService;
import com.hsd.account.staff.dto.org.OrgLogLoginDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "组织架构_员工登录日志")
@RestController
@Slf4j
public class OrgLogLoginController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IOrgLogLoginService orgLogLoginService;
    private static final String acPrefix = "/boss/account/staff/org/orgLogLogin/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("orgLogLogin:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  OrgLogLoginDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("OrgLogLoginController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
               dto = new OrgLogLoginDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            // 信息列表
            result.data = PageUtil.copy(orgLogLoginService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("orgLogLogin:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("OrgLogLoginController info.........");
        Response result = new Response(0, "success");
        try {
            OrgLogLoginDto dto = new OrgLogLoginDto();
            if (id!=null) {
                dto.setId(id);
                result.data = orgLogLoginService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}