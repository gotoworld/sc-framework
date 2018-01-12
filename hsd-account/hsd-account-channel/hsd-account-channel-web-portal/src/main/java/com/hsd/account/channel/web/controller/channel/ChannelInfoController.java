package com.hsd.account.channel.web.controller.channel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.account.channel.api.channel.IChannelInfoService;
import com.hsd.account.channel.api.channel.IChannelTypeService;
import com.hsd.account.channel.dto.channel.ChannelInfoDto;
import com.hsd.account.channel.dto.channel.ChannelTypeDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(description = "渠道商信息")
@RestController
@Slf4j
public class ChannelInfoController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IChannelInfoService channelInfoService;
    @Autowired
    private IChannelTypeService channelTypeService;
    private static final String acPrefix = "/api/account/channel/channelInfo/";
    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("ChannelInfoController info.........");
        Response result = new Response();
        try {
            ChannelInfoDto dto = new ChannelInfoDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = channelInfoService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息完善
     */
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "edit")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "渠道商信息")
    @ApiOperation(value = "信息完善")
    public Response save(@Validated @ModelAttribute ChannelInfoDto dto, BindingResult bindingResult) {
        log.info("ChannelInfoController edit.........");
        Response result = new Response(0,"success");
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "edit." + dto.getToken()))) {
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
                result = channelInfoService.updataChannel(dto);
                request.getSession().setAttribute(acPrefix + "edit." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "渠道商类型列表")
    public Response list() {
        log.info("ChannelInfoController list.........");
        Response result = new Response();
        try {
           
        	ChannelTypeDto dto= new ChannelTypeDto();
            dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            dto.setDelFlag(0);
            // 信息列表
            result.data = channelTypeService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}