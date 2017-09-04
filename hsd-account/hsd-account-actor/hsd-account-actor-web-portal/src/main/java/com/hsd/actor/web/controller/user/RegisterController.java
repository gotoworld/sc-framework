package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.RfAccount2Bean;
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

@Api(description = "客户注册")
@RestController
@Slf4j
public class RegisterController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserService userService;
    private static final String acPrefix = "/api/account/actor/register/";

    /**
     * <p> 注册
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "reg")
    @RfAccount2Bean
    @ApiOperation(value = "信息保存")
    public Response reg(@Validated @ModelAttribute UserDto dto, BindingResult bindingResult) {
        log.info("RegisterController reg.........");
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
                //验证码确认
                if(!"ufgb".equals(dto.getCaptcha())) return Response.error("验证码不正确!");
                if(dto.getPwd()==null || !dto.getPwd().equals(dto.getConfirmpwd()) ) return Response.error("两次密码不一致!");
                dto.setType(UserDto.userType.USER.getCode());
                dto.setState(1);
                result = userService.register(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}