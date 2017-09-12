package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.auth.IAuthRoleService;
import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.api.org.IOrgStaffService;
import com.hsd.account.staff.dto.org.AuthStaffVsRoleDto;
import com.hsd.account.staff.dto.org.OrgOrgVsStaffDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
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

import java.io.IOException;
import java.util.List;

@Api(description = "组织架构_员工")
@RestController
@Slf4j
public class OrgStaffController extends BaseController {
	private static final String acPrefix="/boss/account/staff/org/orgStaff/";

	@Autowired
	private IOrgStaffService orgStaffService;
	@Autowired
	private IAuthRoleService authRoleService;

	@Autowired
	private IOrgInfoService orgDepartmentService;

	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("orgStaff:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page/{pageNum}")
	@ApiOperation(value = "信息分页")
	public Response page(@ModelAttribute OrgStaffDto dto, @PathVariable("pageNum") Integer pageNum) {
		log.info("OrgStaffController page.........");
		Response result = new Response();
		try {
			if (dto == null) {
				dto = new OrgStaffDto();
				dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
			}
			dto.setPageNum(pageNum);
			dto.setDelFlag(0);
			result.data=getPageDto(orgStaffService.findDataIsPage(dto));
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 回收站 (已删除)。
	 */
	@RequiresPermissions("orgStaff:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recyclePage/{pageNum}")
	@ApiOperation(value = "回收站 分页")
	public Response recyclePage(@ModelAttribute OrgStaffDto dto, @PathVariable("pageNum") Integer pageNum) {
		log.info("OrgStaffController recyclePage.........");
		Response result = new Response();
		try {
			if (dto == null) {
				dto = new OrgStaffDto();
				dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
			}
			dto.setPageNum(pageNum);
			dto.setDelFlag(1);
			result.data=getPageDto(orgStaffService.findDataIsPage(dto));
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
		log.info("OrgStaffController info.........");
		Response result = new Response();
		try {
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(id);
			result.data=orgStaffService.findDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("orgStaff:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type = "删除", desc = "组织架构_员工")
	@ApiOperation(value = "逻辑删除")
	public Response del(@PathVariable("id") Long id) {
		log.info("OrgStaffController del.........");
		Response result = new Response();
		try {
			OrgStaffDto orgStaff = JwtUtil.getSubject(OrgStaffDto.class);
			if(orgStaff.getId().equals(id)){
				throw new RuntimeException("不能删除自己!");
			}
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(id);
			result.message=orgStaffService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <li>恢复。
	 */
	@RequiresPermissions("orgStaff:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"recovery/{id}")
	@ALogOperation(type = "恢复", desc = "组织架构_员工")
	@ApiOperation(value = "恢复")
	public Response recovery(@PathVariable("id") Long id) {
		log.info("OrgStaffController del.........");
		Response result = new Response();
		try {
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(id);
			result.message=orgStaffService.recoveryDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**判断员工id是否存在 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"isAccountYN/{account}")
	@ApiOperation(value = "判断员工id是否存在")
	public Response isAccountYN(@PathVariable("account") String account) throws IOException{
		Response result = new Response();
		try {
			result.data=orgStaffService.isAccountYN(account);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"orgStaff:add","orgStaff:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type = "修改", desc = "组织架构_员工")
	@ApiOperation(value = "信息保存")
	public Response save(@Validated OrgStaffDto dto, BindingResult bindingResult) {
		log.info("OrgStaffController save.........");
		Response result = new Response();
		try {
			if (dto == null)throw new RuntimeException("参数异常");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + dto.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			if (bindingResult.hasErrors()) {
				String errorresult = "";
				List<ObjectError> errorList = bindingResult.getAllErrors();
				for (ObjectError error : errorList) {
					errorresult += (error.getDefaultMessage()) + ";";
				}
				result = Response.error(errorresult);
			}else{
				OrgStaffDto orgStaff = (OrgStaffDto) JwtUtil.getSubject(OrgStaffDto.class);
				if(orgStaff !=null){
					if(ValidatorUtil.isEmpty(dto.getAccount())){
						dto.setAccount(orgStaff.getAccount());
					}
				}
				if(null==dto.getState()) dto.setState(0);//启用
				result=orgStaffService.saveOrUpdateData(dto);
				request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
			}
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}

	/**
	 * <p> 密码修改
	 */
	@RequiresPermissions("orgStaff:edit:pwd")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"update/pwd")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="员工密码")
	@ResponseBody
	@ApiOperation(value = "密码修改")
	public Response updatePwd(@ModelAttribute @Validated OrgStaffDto dto) throws IOException {
		log.info("OrgStaffController updatePwd.........");
		Response result = new Response();
		try {
			if(dto==null||ValidatorUtil.isNullEmpty(dto.getOldpwd())||ValidatorUtil.isNullEmpty(dto.getNewpwd())||ValidatorUtil.isNullEmpty(dto.getConfirmpwd())) return Response.error("参数异常!");
			if(!dto.getNewpwd().equals(dto.getConfirmpwd()))throw new RuntimeException("两次密码不一致!");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "updatePwd." + dto.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			dto.setId(JwtUtil.getSubject(OrgStaffDto.class).getId());
			result.message=orgStaffService.updatePwd(dto);
			request.getSession().setAttribute(acPrefix + "updatePwd." + dto.getToken(), "1");
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}

	@RequiresPermissions("orgStaff:edit:resetpwd")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"reset/pwd/{id}")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="员工密码重置")
	@ResponseBody
	@ApiOperation(value = "员工密码重置")
	public Response resetPwd(@PathVariable("id") Long id) throws IOException {
		log.info("OrgStaffController resetPwd.........");
		Response result = new Response();
		try {
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(id);
			result.data=orgStaffService.resetPwd(dto);
			request.getSession().setAttribute(acPrefix + "updatePwd." + dto.getToken(), "1");
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}

	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("orgStaff:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"/briefPage/{pageNum}")
	@ApiOperation(value = "信息分页(精简字段)")
	public Response briefPage(@ModelAttribute OrgStaffDto dto, @PathVariable("pageNum") Integer pageNum) {
		log.info("OrgStaffController briefPage.........");
		Response result = new Response();
		try {
			if (dto == null) {
				dto = new OrgStaffDto();
				dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
			}
			dto.setPageNum(pageNum);
			dto.setDelFlag(0);
			result.data=getPageDto(orgStaffService.findBriefDataIsPage(dto));
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/org/{staffId}")
	@ApiOperation(value = "获取所属组织")
	public Response getStaff(@PathVariable("staffId") Long staffId) {
		log.info("OrgStaffController getStaff.........");
		Response result=new Response();
		try {
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(staffId);
			result.data=orgStaffService.findOrgIsList(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	@RequiresPermissions("orgStaff:edit:org")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "add/org")
	@ALogOperation(type = "添加组织", desc = "组织架构_员工")
	@ApiOperation(value = "添加组织")
	public Response addOrg(@ModelAttribute OrgOrgVsStaffDto dto) {
		log.info("OrgStaffController addOrg.........");
		Response result = new Response();
		try {
			if (dto == null)throw new RuntimeException("参数异常");
			result = orgStaffService.addOrg(dto);
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
	@RequiresPermissions("orgStaff:edit:org")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/org")
	@ALogOperation(type = "删除组织", desc = "组织架构_员工")
	@ApiOperation(value = "删除组织")
	public Response delOrg(@ModelAttribute OrgOrgVsStaffDto dto) {
		log.info("OrgStaffController delOrg.........");
		Response result = new Response();
		try {
			if (dto == null)throw new RuntimeException("参数异常");
			result = orgStaffService.delOrg(dto);
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 批量新增。
	 */
	@RequiresPermissions("orgStaff:add:batch")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"add/batch")
	@ApiOperation(value = "批量新增")
	public Response addBatch(@RequestParam("fileUrl") String fileUrl) {
		log.info("OrgStaffController addBatch.........");
		Response result = new Response();
		try {
			result=orgStaffService.addBatch(fileUrl);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/role/staff/{staffId}")
	@ApiOperation(value = "获取个人已设置角色")
	public Response getStaffRole(@PathVariable("staffId") Long staffId) {
		log.info("OrgStaffController getStaffRole.........");
		Response result=new Response();
		try {
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(staffId);
			result.data=orgStaffService.findStaffRoleIsList(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"get/role/org/{staffId}")
	@ApiOperation(value = "获取组织已设置角色")
	public Response getOrgRole(@PathVariable("staffId") Long staffId) {
		log.info("OrgStaffController getOrgRole.........");
		Response result=new Response();
		try {
			OrgStaffDto dto=new OrgStaffDto();
			dto.setId(staffId);
			result.data=orgStaffService.findOrgRoleIsList(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}

	@RequiresPermissions("orgStaff:edit:role")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "add/role")
	@ALogOperation(type = "添加角色", desc = "组织架构_员工")
	@ApiOperation(value = "添加角色")
	public Response addRole(@ModelAttribute AuthStaffVsRoleDto dto) {
		log.info("OrgStaffController addRole.........");
		Response result = new Response();
		try {
			if (dto == null)throw new RuntimeException("参数异常");
			result = orgStaffService.addRole(dto);
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
	@RequiresPermissions("orgStaff:edit:role")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix + "del/role")
	@ALogOperation(type = "删除角色", desc = "组织架构_员工")
	@ApiOperation(value = "删除角色")
	public Response delRole(@ModelAttribute AuthStaffVsRoleDto dto) {
		log.info("OrgStaffController delRole.........");
		Response result = new Response();
		try {
			if (dto == null)throw new RuntimeException("参数异常");
			result = orgStaffService.delRole(dto);
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
}