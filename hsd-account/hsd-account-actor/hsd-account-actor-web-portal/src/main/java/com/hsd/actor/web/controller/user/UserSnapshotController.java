package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserSnapshotService;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.dto.user.UserSnapshotDto;
import com.hsd.framework.Response;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "客户账户快照")
@RestController
@Slf4j
public class UserSnapshotController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserSnapshotService userSnapshotService;
    private static final String acPrefix = "/api/account/actor/user/userSnapshot/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info")
    @ApiOperation(value = "信息详情")
    public Response info() {
        log.info("UserSnapshotController info.........");
        Response result = new Response(0, "success");
        try {
            UserSnapshotDto dto = new UserSnapshotDto(){{setUserId(JwtUtil.getSubject(UserDto.class).getId());}};
            result.data = userSnapshotService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}