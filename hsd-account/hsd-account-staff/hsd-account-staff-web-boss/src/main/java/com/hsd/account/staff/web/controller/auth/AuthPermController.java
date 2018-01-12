package com.hsd.account.staff.web.controller.auth;

import com.hsd.account.staff.api.auth.IAuthPermService;
import com.hsd.account.staff.dto.auth.AuthPermDto;
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

@Api(description = "权限_功能信息")
@RestController
@Slf4j
public class AuthPermController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IAuthPermService authPermService;
    private static final String acPrefix = "/boss/account/staff/auth/authPerm/";

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("authPerm:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute AuthPermDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("AuthPermController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
               dto = new AuthPermDto();
               dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(authPermService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息树json
     */
    @RequiresPermissions("authPerm:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"tree")
    @ResponseBody
    @ApiOperation(value = "信息树")
    public Response jsonTree() {
        log.info("AuthPermController jsonTree.........");
        Response result=new Response(0,"success");
        try {
            result.data=authPermService.findDataIsTree(new AuthPermDto());
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }


    /**
     * <p> 信息详情。
     */
    @RequiresPermissions("authPerm:info")
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
    @ApiOperation(value = "信息详情")
    public Response info(@PathVariable("id") String id) {
        log.info("AuthPermController info.........");
        Response result = new Response();
        try {
            AuthPermDto dto = new AuthPermDto();
            if (id!=null) {
                dto.setId(id);
                dto.setDelFlag(0);
                result.data = authPermService.findDataById(dto);
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p>删除。
     */
   @RequiresPermissions("authPerm:del")
    @RequestMapping(method = RequestMethod.POST, value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "权限_功能信息")
    @ApiOperation(value = "信息删除")
    public Response del(@PathVariable("id") String id) {
        log.info("AuthPermController del.........");
        Response result = new Response(0,"success");
        try {
            result.message = authPermService.deleteDataById(new AuthPermDto(){{setId(id);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"authPerm:add", "authPerm:edit"}, logical = Logical.OR)
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT}, value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "权限_功能信息")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated @ModelAttribute AuthPermDto dto, BindingResult bindingResult) {
        log.info("AuthPermController save.........");
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
                result = authPermService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}