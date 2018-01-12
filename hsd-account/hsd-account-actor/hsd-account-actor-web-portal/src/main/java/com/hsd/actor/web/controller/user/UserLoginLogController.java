package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserLoginLogService;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.dto.user.UserLoginLogDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
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
    private static final String acPrefix = "/api/account/actor/user/userLoginLog/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute UserLoginLogDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("UserrLoginLogController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new UserLoginLogDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setUserId(JwtUtil.getSubject(UserDto.class).getId());
            // 信息列表
            result.data = PageUtil.copy(userLoginLogService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}