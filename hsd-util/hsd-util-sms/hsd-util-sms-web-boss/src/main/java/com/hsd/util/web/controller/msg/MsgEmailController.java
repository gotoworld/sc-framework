package com.hsd.util.web.controller.msg;

import com.hsd.util.api.msg.IMsgEmailService;
import com.hsd.util.dto.msg.MsgEmailDto;
import com.hsd.framework.Response;
import com.hsd.framework.PageUtil;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            if (id!=null) {throw new RuntimeException("参数异常!");};
            MsgEmailDto dto = new MsgEmailDto(){{
                setId(id);
            
            }};
            result.data = msgEmailService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>物理删除。
     */
    @RequiresPermissions("msgEmail:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "邮件推送-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") Long id) {
        log.info("MsgEmailController phydel.........");
        Response result = new Response();
        try {
           if (id==null) {throw new RuntimeException("参数异常!");};
           MsgEmailDto dto = new MsgEmailDto(){{
            setId(id);
           }};
            result.message = msgEmailService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"msgEmail:add", "msgEmail:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "邮件推送")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute MsgEmailDto dto, BindingResult bindingResult) {
        log.info("MsgEmailController save.........");
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
                result = msgEmailService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}