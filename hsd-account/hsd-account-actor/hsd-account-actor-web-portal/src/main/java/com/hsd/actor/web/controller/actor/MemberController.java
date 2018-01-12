package com.hsd.actor.web.controller.actor;

import com.hsd.account.actor.api.actor.IMemberService;
import com.hsd.account.actor.dto.actor.MemberDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.dto.user.UserExtInfoDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.StrUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(description = "会员信息表")
@RestController
@Slf4j
public class MemberController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IMemberService memberService;
    private static final String acPrefix = "/api/account/actor/actor/member/";

    /**
     * <p> 信息详情。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info")
    @ApiOperation(value = "信息详情")
    public Response info() {
        log.info("MemberController info.........");
        Response result = new Response();
        try {
            result.data = memberService.findDataById(new MemberDto(){{
                setUserId(JwtUtil.getSubject(UserDto.class).getId());
                setDelFlag(0);
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "save")
    @ApiOperation(value = "信息保存")
    @RfAccount2Bean
    public Response save(@Validated @ModelAttribute MemberDto dto, BindingResult bindingResult) {
        log.info("MemberController save.........");
        Response result = new Response();
        try {
            if (dto == null) return Response.error("参数获取异常!");
            if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + dto.getToken()))) {
                throw new RuntimeException("请不要重复提交!");
            }
            dto.setUserId(JwtUtil.getSubject(UserDto.class).getId());
            if (bindingResult.hasErrors()) {
                String errorMsg = "";
                List<ObjectError> errorList = bindingResult.getAllErrors();
                for (ObjectError error : errorList) {
                    errorMsg += (error.getDefaultMessage()) + ";";
                }
                result = Response.error(errorMsg);
            } else {
                String[] templateIds = request.getParameterValues("templateId");
                if (templateIds != null) {
                    if (dto.getExtInfos() == null) dto.setExtInfos(new ArrayList<>());
                    for (String templateId : templateIds) {
                        if (ValidatorUtil.notEmpty(templateId)) {
                            UserExtInfoDto extInfoDto = new UserExtInfoDto();
                            extInfoDto.setUserId(dto.getUserId());//客户ID
                            extInfoDto.setTemplateId(Long.parseLong(templateId));//所属模板id
                            StringBuffer attributeJson = new StringBuffer("{"); //模板值（json形式保存）
                            String[] attrs = request.getParameterValues("attr" + templateId);
                            if (attrs != null) {
                                for (int i = 0; i < attrs.length; i++) {
                                    attributeJson.append("\"" + StrUtil.replaceAll(attrs[i], "attr" + templateId, "") + "\":\"" + request.getParameter(attrs[i]) + "\"");
                                    if (attrs.length - i > 1) attributeJson.append(",");
                                }
                            }
                            attributeJson.append("}");
                            extInfoDto.setAttributeJson(attributeJson.toString());
                            dto.getExtInfos().add(extInfoDto);
                        }
                    }
                }
                result = memberService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}