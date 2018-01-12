package com.hsd.msg.web.controller.msg;

import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import com.hsd.msg.api.msg.IMsgVerifyService;
import com.hsd.msg.dto.msg.MsgVerifyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "信息验证")
@RestController
@Slf4j
public class MsgVerifyController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IMsgVerifyService msgVerifyService;
    private static final String acPrefix = "/boss/util/msg/msgVerify/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("msgVerify:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  MsgVerifyDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("MsgVerifyController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) dto = new MsgVerifyDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            
            result.data = PageUtil.copy(msgVerifyService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("msgVerify:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("MsgVerifyController info.........");
        Response result = new Response(0, "success");
        try {
            if (id!=null) {throw new RuntimeException("参数异常!");}
            MsgVerifyDto dto = new MsgVerifyDto(){{
                setId(id);
            
            }};
            result.data = msgVerifyService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}