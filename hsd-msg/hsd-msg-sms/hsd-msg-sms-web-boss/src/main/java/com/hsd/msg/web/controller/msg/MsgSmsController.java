package com.hsd.msg.web.controller.msg;

import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import com.hsd.msg.api.msg.IMsgSmsService;
import com.hsd.msg.dto.msg.MsgSmsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "短信推送")
@RestController
@Slf4j
public class MsgSmsController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IMsgSmsService msgSmsService;
    private static final String acPrefix = "/boss/util/msg/msgSms/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("msgSms:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  MsgSmsDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("MsgSmsController page.........");
        Response result = new Response();
        try {
            if (dto == null) dto = new MsgSmsDto(){{ setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT); }};
            dto.setPageNum(pageNum);
            
            result.data = PageUtil.copy(msgSmsService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("msgSms:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("MsgSmsController info.........");
        Response result = new Response();
        try {
            if (id!=null) {throw new RuntimeException("参数异常!");}
            MsgSmsDto dto = new MsgSmsDto(){{
                setId(id);
            
            }};
            result.data = msgSmsService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}