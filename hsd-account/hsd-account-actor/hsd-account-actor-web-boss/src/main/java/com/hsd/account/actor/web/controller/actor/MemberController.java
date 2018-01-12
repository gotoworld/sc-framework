package com.hsd.account.actor.web.controller.actor;

import com.hsd.account.actor.api.actor.IMemberService;
import com.hsd.account.actor.dto.actor.MemberDto;
import com.hsd.account.actor.dto.user.UserExtInfoDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "会员信息表")
@RestController
@Slf4j
public class MemberController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IMemberService memberService;
    private static final String acPrefix = "/boss/account/actor/actor/member/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("member:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute MemberDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("MemberController page.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                dto = new MemberDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(memberService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("member:edit")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{userId}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("userId") Long userId) {
        log.info("MemberController info.........");
        Response result = new Response(0, "success");
        try {
            MemberDto dto = new MemberDto();
            if (userId != null) {
                dto.setUserId(userId);
                dto.setDelFlag(0);
                result.data = memberService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

//    /**
//     * <p>删除。
//     */
//    @RequiresPermissions("member:del")
//    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{userId}")
//    @ALogOperation(type = "删除", desc = "会员信息表")
//    @ApiOperation(value = "信息删除")
//    public Response del(@PathVariable("userId") Long userId) {
//        log.info("MemberController del.........");
//        Response result = new Response(0, "success");
//        try {
//            MemberDto dto = new MemberDto();
//            dto.setUserId(userId);
//            result.message = memberService.deleteDataById(dto);
//        } catch (Exception e) {
//            result = Response.error(e.getMessage());
//        }
//        return result;
//    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"member:add", "member:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "save")
    @ALogOperation(type = "修改", desc = "会员信息表")
    @ApiOperation(value = "信息保存")
    @RfAccount2Bean
    public Response save(@Validated @ModelAttribute MemberDto dto, BindingResult bindingResult) {
        log.info("MemberController save.........");
        Response result = new Response(0, "success");
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