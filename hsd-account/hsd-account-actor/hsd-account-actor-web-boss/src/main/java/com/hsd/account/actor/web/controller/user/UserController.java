package com.hsd.account.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.user.UserDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "客户表")
@RestController
@Slf4j
public class UserController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserService userService;
    private static final String acPrefix = "/boss/account/actor/user/user/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("user:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute UserDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("UserController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
                dto = new UserDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);

            // 信息列表
            result.data = PageUtil.copy(userService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("user:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("UserController info.........");
        Response result = new Response();
        try {
            if(id==null) throw new RuntimeException("参数异常!");
            UserDto dto = new UserDto(){{setId(id);}};
            result.data = userService.findDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"user:add", "user:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "客户表")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute UserDto dto, BindingResult bindingResult) {
        log.info("UserController save.........");
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
                result = userService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 设置标签。
     */
    @RequiresPermissions("user:edit:tag")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "setTags")
    @ALogOperation(type = "修改", desc = "客户表-设置标签")
    @ApiOperation(value = "设置标签")
    public Response setTags(@ModelAttribute UserDto dto) {
        log.info("UserController setTags.........");
        Response result = new Response(0,"success");
        try {
            if(dto==null) throw new RuntimeException("参数异常");
            result.data = userService.setTags(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("user:edit:blacklist")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "set/blacklist/{id}")
    @ALogOperation(type = "修改", desc = "客户表-设置黑名单")
    @ApiOperation(value = "设置黑名单")
    public Response setBlacklist(@PathVariable("id") Long id) {
        log.info("UserController setBlacklist.........");
        Response result = new Response(0,"success");
        try {
            if(id==null) throw new RuntimeException("参数异常!");
            UserDto dto = new UserDto(){{setId(id);}};
            result.data = userService.setBlacklist(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("user:edit:blacklist")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/blacklist/{id}")
    @ALogOperation(type = "修改", desc = "客户表-取消黑名单")
    @ApiOperation(value = "取消黑名单")
    public Response delBlacklist(@PathVariable("id") Long id) {
        log.info("UserController delBlacklist.........");
        Response result = new Response(0,"success");
        try {
            if(id==null) throw new RuntimeException("参数异常!");
            UserDto dto = new UserDto(){{setId(id);}};
            result.data = userService.delBlacklist(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}