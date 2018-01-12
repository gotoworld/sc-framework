package com.hsd.msg.web.controller.msg;

import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import com.hsd.msg.api.msg.IMsgEmailService;
import com.hsd.msg.dto.msg.MsgEmailDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "邮件推送")
@RestController
@Slf4j
public class MsgEmailController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IMsgEmailService msgEmailService;
    private static final String acPrefix = "/boss/util/msg/msgEmail/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("msgEmail:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  MsgEmailDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("MsgEmailController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new MsgEmailDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            
            result.data = PageUtil.copy(msgEmailService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("msgEmail:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("MsgEmailController info.........");
        Response result = new Response();
        try {
            if (id!=null) {throw new RuntimeException("参数异常!");}
            MsgEmailDto dto = new MsgEmailDto(){{
                setId(id);
            
            }};
            result.data = msgEmailService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}