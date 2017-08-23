package com.hsd.account.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserExtInfoService;
import com.hsd.account.actor.dto.user.UserExtInfoDto;
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

@Api(description = "用户扩展数据")
@RestController
@Slf4j
public class UserExtInfoController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserExtInfoService userExtInfoService;
    private static final String acPrefix = "/boss/account/actor/user/userExtInfo/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("userExtInfo:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  UserExtInfoDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("UserExtInfoController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new UserExtInfoDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);

            // 信息列表
            result.data = PageUtil.copy(userExtInfoService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }



    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("userExtInfo:edit")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("UserExtInfoController info.........");
        Response result = new Response();
        try {
            UserExtInfoDto dto = new UserExtInfoDto();
            if (id!=null) {
                dto.setId(id);

                result.data = userExtInfoService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"userExtInfo:add", "userExtInfo:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "用户扩展数据")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute UserExtInfoDto dto, BindingResult bindingResult) {
        log.info("UserExtInfoController save.........");
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
                result = userExtInfoService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}