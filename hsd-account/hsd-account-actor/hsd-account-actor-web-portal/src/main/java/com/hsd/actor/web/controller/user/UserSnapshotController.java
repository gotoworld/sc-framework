package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserSnapshotService;
import com.hsd.account.actor.dto.user.UserSnapshotDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.RfAccount2Bean;
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

@Api(description = "客户照表")
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

    /**
     * <p> 信息保存
     */
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute UserSnapshotDto dto, BindingResult bindingResult) {
        log.info("UserSnapshotController save.........");
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
                result = userSnapshotService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}