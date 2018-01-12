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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.account.channel.api.channel.IChannelInfoService;
import com.hsd.account.channel.dto.channel.ChannelInfoDto;
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

@Api(description = "渠道商信息")
@RestController
@Slf4j
public class ChannelInfoController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IChannelInfoService channelInfoService;
    private static final String acPrefix = "/boss/account/channel/channel/channelInfo/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("channelInfo:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  ChannelInfoDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("ChannelInfoController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new ChannelInfoDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(channelInfoService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("channelInfo:info")
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
     * <p>删除。
     */
   @RequiresPermissions("channelInfo:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "渠道商信息")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("ChannelInfoController del.........");
        Response result = new Response();
        try {
            ChannelInfoDto dto = new ChannelInfoDto();
            dto.setId(id);
            result.message = channelInfoService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"channelInfo:add", "channelInfo:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "渠道商信息")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute ChannelInfoDto dto, BindingResult bindingResult) {
        log.info("ChannelInfoController save.........");
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
                result = channelInfoService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * @param id
     * 信息恢复
     */
    @RequiresPermissions("channelInfo:recovery")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "recovery/{id}")
    @ALogOperation(type = "恢复", desc = "渠道商信息")
    @ApiOperation(value = "信息恢复")
    public Response recovery(@PathVariable("id") Long id){
    	log.info("ChannelInfoController recovery.........");
        Response result = new Response();
        try {
            ChannelInfoDto dto = new ChannelInfoDto();
            dto.setId(id);
            result.message = channelInfoService.recoveryData(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    @RequiresPermissions("channelInfo:reset")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "resetPwd/{id}")
    @ALogOperation(type = "重置", desc = "渠道商信息")
    @ApiOperation(value = "重置密码")
    public Response resetPwd(@PathVariable("id") Long id){
    	log.info("ChannelInfoController resetPwd........."); 
    	 Response result = new Response();
         try {
             ChannelInfoDto dto = new ChannelInfoDto();
             dto.setId(id);
             result.data = channelInfoService.resetPwd(dto);
         } catch (Exception e) {
             result = Response.error(e.getMessage());
         }
         return result;
    }
    /**
	 * <p> 批量新增。
	 */
	@RequiresPermissions("channelInfo:add:batch")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"add/batch")
	@ApiOperation(value = "批量上传")
	public Response addBatch(@RequestParam("fileUrl") String fileUrl) {
		log.info("ChannelInfoController addBatch.........");
		Response result = new Response();
		try {
			result=channelInfoService.addBatch(fileUrl);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
}