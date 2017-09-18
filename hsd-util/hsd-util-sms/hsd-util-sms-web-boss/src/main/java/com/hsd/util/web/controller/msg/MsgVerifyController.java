package com.hsd.util.web.controller.msg;

import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;
import com.hsd.util.api.msg.IMsgVerifyService;
import com.hsd.util.dto.msg.MsgVerifyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Response result = new Response();
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
        Response result = new Response();
        try {
            if (id!=null) {throw new RuntimeException("参数异常!");};
            MsgVerifyDto dto = new MsgVerifyDto(){{
                setId(id);
            
            }};
            result.data = msgVerifyService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>物理删除。
     */
    @RequiresPermissions("msgVerify:phydel")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "phydel/{id}")
    @ALogOperation(type = "删除", desc = "信息验证-物理删除")
    @ApiOperation(value = "物理删除")
    public Response phydel(@PathVariable("id") Long id) {
        log.info("MsgVerifyController phydel.........");
        Response result = new Response();
        try {
           if (id==null) {throw new RuntimeException("参数异常!");};
           MsgVerifyDto dto = new MsgVerifyDto(){{
            setId(id);
           }};
            result.message = msgVerifyService.deleteData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"msgVerify:add", "msgVerify:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "信息验证")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute MsgVerifyDto dto, BindingResult bindingResult) {
        log.info("MsgVerifyController save.........");
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
                result = msgVerifyService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}