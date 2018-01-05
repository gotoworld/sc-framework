package com.hsd.account.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserSnapshotService;
import com.hsd.account.actor.dto.user.UserSnapshotDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "客户照表")
@RestController
@Slf4j
public class UserSnapshotController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserSnapshotService userSnapshotService;
    private static final String acPrefix = "/boss/account/actor/user/userSnapshot/";


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("userSnapshot:edit")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("UserSnapshotController info.........");
        Response result = new Response();
        try {
            UserSnapshotDto dto = new UserSnapshotDto();
            if (userId!=null) {
                dto.setUserId(userId);
                result.data = userSnapshotService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}