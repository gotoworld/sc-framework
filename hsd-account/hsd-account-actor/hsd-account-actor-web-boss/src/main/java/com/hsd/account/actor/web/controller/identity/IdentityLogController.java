package com.hsd.account.actor.web.controller.identity;

import com.hsd.account.actor.api.identity.IIdentityLogService;
import com.hsd.account.actor.dto.identity.IdentityLogDto;
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

@Api(description = "客户实名认证日志")
@RestController
@Slf4j
public class IdentityLogController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IIdentityLogService identityLogService;
    private static final String acPrefix = "/boss/account/actor/identity/identityLog/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("identityLog:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  IdentityLogDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("IdentityLogController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new IdentityLogDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);

            result.data = PageUtil.copy(identityLogService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("identityLog:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("IdentityLogController info.........");
        Response result = new Response(0, "success");
        try {
            if (id==null) {throw new RuntimeException("参数异常!");};
            IdentityLogDto dto = new IdentityLogDto(){{
                setId(id);

            }};
            result.data = identityLogService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}