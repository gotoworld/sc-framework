package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.framework.PageUtil;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.util.CommonConstant;
import com.hsd.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "组织架构")
@RestController
@Slf4j
public class OrgInfoController extends BaseController {
    private static final String acPrefix = "/boss/account/staff/org/orgInfo/";

    @Autowired
    private IOrgInfoService orgInfoService;

    /**
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("orgInfo:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "page/{pageNum}")
    @ApiOperation(value = "信息分页")
    public Response page(@ModelAttribute  OrgInfoDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("OrgInfoController page.........");
        Response result = new Response();
        try {
            if (dto == null) {
                dto = new OrgInfoDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }

            OrgUserDto orgUserDto= (OrgUserDto) getAuth().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);

            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(orgInfoService.findDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息树json。
     */
    @RequiresPermissions("orgInfo:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"tree")
    @ResponseBody
    @ApiOperation(value = "信息树")
    public Response jsonTree() {
        log.info("OrgInfoController jsonTree.........");
        Response result=new Response();
        try {
            result.data=orgInfoService.findDataIsTree(new OrgInfoDto());
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 详情。
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"info/{id}")
    @ApiOperation(value = "详情")
    public Response info(@PathVariable("id") Long id) {
        log.info("OrgInfoController info.........");
        Response result = new Response();
        try {
            OrgInfoDto dto=new OrgInfoDto();
            dto.setId(id);
            result.data=orgInfoService.findDataById(dto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 删除。
     */
    @RequiresPermissions("orgInfo:del")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/{id}")
    @ALogOperation(type = "删除", desc = "组织机构")
    @ApiOperation(value = "删除")
    public Response del(@PathVariable("id") Long id) {
        log.info("OrgInfoController del.........");
        Response result = new Response();
        try {
            OrgInfoDto dto = new OrgInfoDto();
            dto.setId(id);
            result.message = orgInfoService.deleteDataById(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 信息保存
     */
    @RequiresPermissions(value = {"orgInfo:add", "orgInfo:edit"}, logical = Logical.OR)
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "save")
    @RfAccount2Bean
    @ALogOperation(type = "修改", desc = "组织机构")
    @ApiOperation(value = "信息保存")
    public Response save(@Validated OrgInfoDto dto, BindingResult bindingResult) {
        log.info("OrgInfoController save.........");
        Response result = new Response();
        try {
            if (dto == null)throw new RuntimeException("参数异常");
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
                result = orgInfoService.saveOrUpdateData(dto);
                request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
            }
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}