package com.hsd.actor.web.controller.identity;

import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.framework.Response;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "实名认证表")
@RestController
@Slf4j
public class IdentityController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IIdentityService identityService;
    private static final String acPrefix = "/api/account/actor/identity/identity/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("IdentityController info.........");
        Response result = new Response();
        try {
            if (userId==null) {throw new RuntimeException("参数异常!");};
            IdentityDto dto = new IdentityDto(){{
                setUserId(userId);

            }};
            result.data = identityService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}