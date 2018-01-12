package com.hsd.account.channel.web.controller.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.account.channel.api.channel.IChannelTypeService;
import com.hsd.account.channel.dto.channel.ChannelTypeDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.web.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(description = "渠道商类型")
@RestController
@Slf4j
public class ChannelTypeController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IChannelTypeService channelTypeService;
    private static final String acPrefix = "/boss/account/channel/channel/channelType/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("channelType:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  ChannelTypeDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("ChannelTypeController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new ChannelTypeDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(channelTypeService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>信息列表 (未删除)。
     */
    @RequiresPermissions("channelType:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list")
    @ApiOperation(value = "信息列表")
    public Response list(@ModelAttribute ChannelTypeDto dto) {
        log.info("ChannelTypeController list.........");
        Response result = new Response();
        try {
            if (dto == null) {
                dto = new ChannelTypeDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setDelFlag(0);
            // 信息列表
            result.data = channelTypeService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("channelType:edit")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("ChannelTypeController info.........");
        Response result = new Response();
        try {
            ChannelTypeDto dto = new ChannelTypeDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = channelTypeService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>删除。
     */
   @RequiresPermissions("channelType:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "渠道商类型")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("ChannelTypeController del.........");
        Response result = new Response(0,"success");
        try {
            ChannelTypeDto dto = new ChannelTypeDto();
            dto.setId(id);
            result.message = channelTypeService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"channelType:add", "channelType:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "渠道商类型")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute ChannelTypeDto dto, BindingResult bindingResult) {
        log.info("ChannelTypeController save.........");
        Response result = new Response(0,"success");
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
                result = channelTypeService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}