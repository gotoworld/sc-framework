package com.hsd.account.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserSignContractService;
import com.hsd.account.actor.dto.user.UserSignContractDto;
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

@Api(description = "客户网签协议记录")
@RestController
@Slf4j
public class UserSignContractController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserSignContractService userSignContractService;
    private static final String acPrefix = "/boss/account/actor/user/userSignContract/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("userSignContract:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  UserSignContractDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("UserSignContractController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
               dto = new UserSignContractDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);

            // 信息列表
            result.data = PageUtil.copy(userSignContractService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("userSignContract:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("UserSignContractController info.........");
        Response result = new Response(0, "success");
        try {
            UserSignContractDto dto = new UserSignContractDto();
            if (id!=null) {
                dto.setId(id);

                result.data = userSignContractService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}