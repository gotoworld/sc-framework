package com.hsd.account.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserLoginLogService;
import com.hsd.account.actor.dto.user.UserLoginLogDto;
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

@Api(description = "客户登录日志")
@RestController
@Slf4j
public class UserLoginLogController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserLoginLogService userLoginLogService;
    private static final String acPrefix = "/boss/account/actor/user/userLoginLog/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("userLoginLog:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute UserLoginLogDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("UserrLoginLogController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
               dto = new UserLoginLogDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);

            // 信息列表
            result.data = PageUtil.copy(userLoginLogService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("userLoginLog:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("UserrLoginLogController info.........");
        Response result = new Response(0, "success");
        try {
            UserLoginLogDto dto = new UserLoginLogDto();
            if (id!=null) {
                dto.setId(id);

                result.data = userLoginLogService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}