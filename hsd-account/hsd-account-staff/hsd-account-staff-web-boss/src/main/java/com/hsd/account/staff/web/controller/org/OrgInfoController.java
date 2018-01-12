package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsRoleDto;
import com.hsd.account.staff.dto.org.OrgOrgVsStaffDto;
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
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                dto = new OrgInfoDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
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
     * <p>信息分页 (未删除)。
     */
    @RequiresPermissions("orgInfo:menu")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "briefPage/{pageNum}")
    @ApiOperation(value = "信息分页(精简信息)")
    public Response briefPage(@ModelAttribute  OrgInfoDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("OrgInfoController briefPage.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                dto = new OrgInfoDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(0);
            // 信息列表
            result.data = PageUtil.copy(orgInfoService.findBriefDataIsPage(dto));
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p> 回收站 (已删除)。
     */
    @RequiresPermissions("orgInfo:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recyclePage/{pageNum}")
    @ApiOperation(value = "回收站 分页")
    public Response recyclePage(@ModelAttribute OrgInfoDto dto, @PathVariable("pageNum") Integer pageNum) {
        log.info("OrgInfoController recyclePage.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null) {
                dto = new OrgInfoDto();
                dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
            }
            dto.setPageNum(pageNum);
            dto.setDelFlag(1);
            result.data=getPageDto(orgInfoService.findDataIsPage(dto));
        } catch (Exception e) {
            result=Response.error(e.getMessage());
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
        Response result=new Response(0, "success");
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
        Response result = new Response(0, "success");
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
        Response result = new Response(0, "success");
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
        Response result = new Response(0, "success");
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

    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/role/{orgId}")
    @ApiOperation(value = "获取组织已设置角色")
    public Response getOrgRole(@PathVariable("orgId") Long orgId) {
        log.info("OrgInfoController getOrgRole.........");
        Response result=new Response(0, "success");
        try {
            OrgInfoDto orgInfoDto=new OrgInfoDto();
            orgInfoDto.setId(orgId);
            result.data=orgInfoService.findOrgRoleIsList(orgInfoDto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("orgInfo:edit:role")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "add/role")
    @ALogOperation(type = "添加角色", desc = "组织机构")
    @ApiOperation(value = "添加角色")
    public Response addRole(@ModelAttribute OrgOrgVsRoleDto dto) {
        log.info("OrgInfoController addRole.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null)throw new RuntimeException("参数异常");
            result = orgInfoService.addRole(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgInfo:edit:role")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/role")
    @ALogOperation(type = "删除角色", desc = "组织机构")
    @ApiOperation(value = "删除角色")
    public Response delRole(@ModelAttribute OrgOrgVsRoleDto dto) {
        log.info("OrgInfoController delRole.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null)throw new RuntimeException("参数异常");
            result = orgInfoService.delRole(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/staff/{orgId}")
    @ApiOperation(value = "获取员工-根据组织")
    public Response getStaff(@PathVariable("orgId") Long orgId
                             ,@RequestParam(name = "level",required = false) String level) {
        log.info("OrgInfoController getStaff.........");
        Response result=new Response(0, "success");
        try {
            OrgOrgVsStaffDto orgVsStaffDto=new OrgOrgVsStaffDto();
            orgVsStaffDto.setOrgId(orgId);
            orgVsStaffDto.setLevel(level);
            result.data=orgInfoService.findOrgStaffIsList(orgVsStaffDto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgInfo:edit:staff")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "add/staff")
    @ALogOperation(type = "添加人员", desc = "组织机构")
    @ApiOperation(value = "添加人员")
    public Response setStaff(@ModelAttribute OrgOrgVsStaffDto dto) {
        log.info("OrgInfoController addStaff.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null)throw new RuntimeException("参数异常");
            result = orgInfoService.addStaff(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgInfo:edit:staff")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/staff")
    @ALogOperation(type = "删除人员", desc = "组织机构")
    @ApiOperation(value = "删除人员")
    public Response delStaff(@ModelAttribute OrgOrgVsStaffDto dto) {
        log.info("OrgInfoController delStaff.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null)throw new RuntimeException("参数异常");
            result = orgInfoService.delStaff(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgInfo:edit:setManager")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "set/manager")
    @ALogOperation(type = "设置部门负责人", desc = "组织机构")
    @ApiOperation(value = "设置部门负责人")
    public Response setManager(@RequestParam("orgId") Long orgId,@RequestParam("manager") Long manager) {
        log.info("OrgInfoController setManager.........");
        Response result = new Response(0, "success");
        try {
            if (orgId == null || manager==null)throw new RuntimeException("参数异常");
            OrgInfoDto dto=new OrgInfoDto(){{setId(orgId);setManager(manager);}};
            result = orgInfoService.setManager(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgInfo:menu")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "get/manager/{orgId}")
    @ApiOperation(value = "获取部门负责人")
    public Response getManager(@PathVariable("orgId") Long orgId) {
        log.info("OrgInfoController getManager.........");
        Response result = new Response(0, "success");
        try {
            result = orgInfoService.getManager(new OrgInfoDto(){{setId(orgId);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/staff/bycode/{orgCode}")
    @ApiOperation(value = "获取员工-根据组织CODE")
    public Response getStaffCode(@PathVariable("orgCode") String orgCode
            ,@RequestParam(name = "level",required = false) String level) {
        log.info("OrgInfoController getStaffCode.........");
        Response result=new Response(0, "success");
        try {
            OrgOrgVsStaffDto orgVsStaffDto=new OrgOrgVsStaffDto();
            orgVsStaffDto.setCode(orgCode);
            orgVsStaffDto.setLevel(level);
            result.data=orgInfoService.findOrgStaffByCodeIsList(orgVsStaffDto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/level/{orgCode}")
    @ApiOperation(value = "获取组织内员工所有职级-根据组织CODE")
    public Response findOrgLevel(@PathVariable("orgCode") String orgCode) {
        log.info("OrgInfoController findOrgLevel.........");
        Response result=new Response(0, "success");
        try {
            OrgOrgVsStaffDto orgVsStaffDto=new OrgOrgVsStaffDto();
            orgVsStaffDto.setCode(orgCode);
            result.data=orgInfoService.findOrgLevel(orgVsStaffDto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>获取组织列表-根据组织类型 (未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list/bytype/{type}")
    @ApiOperation(value = "获取组织列表-根据组织类型")
    public Response list(@PathVariable("type") Integer type) {
        log.info("OrgInfoController list bytype.........");
        Response result = new Response(0, "success");
        try {
            OrgInfoDto dto = new OrgInfoDto();
            dto.setType(type);//类型0企业1部门2组
            dto.setDelFlag(0);
            // 信息列表
            result.data = orgInfoService.findDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>根据上级机构获取下级机构(未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "list/sub/{orgCode}")
    @ApiOperation(value = "获取组织列表-根据上级机构")
    public Response listSub(@PathVariable("orgCode") String orgCode) {
        log.info("OrgInfoController listSub........");
        Response result = new Response(0, "success");
        try {
            OrgInfoDto dto = new OrgInfoDto();
            dto.setCode(orgCode);
            dto.setDelFlag(0);
            // 信息列表
            result.data = orgInfoService.findChildDataIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>获取组织及下级组织内人员->传入公司或部门的code(未删除)。
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/child/staff/{orgCode}")
    @ApiOperation(value = "获取组织及下级组织内人员->传入公司或部门的code")
    public Response findOrgChildStaffIsList(@PathVariable("orgCode") String orgCode) {
        log.info("OrgInfoController findOrgChildStaffIsList........");
        Response result = new Response(0, "success");
        try {
            OrgInfoDto dto = new OrgInfoDto();
            dto.setCode(orgCode);
            dto.setDelFlag(0);
            // 信息列表
            result.data = orgInfoService.findOrgChildStaffIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <li>恢复。
     */
    @RequiresPermissions("orgInfo:recovery")
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recovery/{id}")
    @ALogOperation(type = "恢复", desc = "组织架构")
    @ApiOperation(value = "恢复")
    public Response recovery(@PathVariable("id") Long id) {
        log.info("OrgInfoController del.........");
        Response result = new Response(0, "success");
        try {
            OrgInfoDto dto=new OrgInfoDto();
            dto.setId(id);
            result.message=orgInfoService.recoveryDataById(dto);
        } catch (Exception e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }

}