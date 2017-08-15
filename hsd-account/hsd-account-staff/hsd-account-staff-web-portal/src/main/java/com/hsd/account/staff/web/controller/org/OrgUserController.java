package com.hsd.account.staff.web.controller.org;

import com.hsd.account.staff.api.auth.IAuthRoleService;
import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.api.org.IOrgUserService;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.web.controller.BaseController;
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

@Api(description = "组织架构_用户")
@RestController
@Slf4j
public class OrgUserController extends BaseController {
	private static final String acPrefix="/api/account/staff/org/orgUser/";

	@Autowired
	private IOrgUserService orgUserService;
	@Autowired
	private IAuthRoleService authRoleService;

	@Autowired
	private IOrgInfoService orgDepartmentService;

	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("orgUser:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page/{pageNum}")
	@ApiOperation(value = "信息分页")
	public Response page(@RequestBody OrgUserDto dto, @PathVariable("pageNum") Integer pageNum) {
		log.info("OrgUserController page.........");
		Response result = new Response();
		try {
			if (dto == null) {
				dto = new OrgUserDto();
				dto.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
			}
			dto.setPageNum(pageNum);
			dto.setDelFlag(0);
			result.data=getPageDto(orgUserService.findDataIsPage(dto));
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 获取当前用户的角色集合。
	 */
	@RequiresPermissions("orgUser:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"myRoles/{id}")
	@ApiOperation(value = "获取当前用户的角色集合")
	public Response myRoles(@PathVariable("id") Long id) {
		log.info("OrgUserController myRoles.........");
		Response result = new Response();
		try {
			OrgUserDto dto=new OrgUserDto();
			dto.setId(id);
			dto=orgUserService.findDataById(dto);
			if(dto==null)throw new RuntimeException("用户不存在!");
			result.data=orgUserService.findRoleDataIsList(dto);
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
		log.info("OrgUserController info.........");
		Response result = new Response();
		try {
			OrgUserDto dto=new OrgUserDto();
			dto.setId(id);
			result.data=orgUserService.findDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("orgUser:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ApiOperation(value = "逻辑删除")
	public Response del(@PathVariable("id") Long id) {
		log.info("OrgUserController del.........");
		Response result = new Response();
		try {
			OrgUserDto orgUser = JwtUtil.getSubject(OrgUserDto.class);
			if(orgUser.getId().equals(id)){
				throw new RuntimeException("不能删除自己!");
			}
			OrgUserDto dto=new OrgUserDto();
			dto.setId(id);
			result.message=orgUserService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**判断用户id是否存在 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"isUidYN/{uid}")
	@ApiOperation(value = "判断用户id是否存在")
	public Response isUidYN(@PathVariable("uid") String uid) throws IOException{
		Response result = new Response();
		try {
			result.data=orgUserService.isUidYN(uid);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}


	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"orgUser:add","orgUser:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	@ApiOperation(value = "信息保存")
	public Response save(@Validated OrgUserDto dto, BindingResult bindingResult) {
		log.info("OrgUserController save.........");
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
				OrgUserDto orgUser = (OrgUserDto) JwtUtil.getSubject(OrgUserDto.class);
				if(orgUser !=null){
					if(ValidatorUtil.isEmpty(dto.getAccount())){
						dto.setAccount(orgUser.getAccount());
					}
				}
				if(null==dto.getState()) dto.setState(1);//禁用
				result=orgUserService.saveOrUpdateData(dto);
				if(dto.getId()==orgUser.getId()) {
					request.getSession().setAttribute(CommonConstant.SESSION_KEY_USER, dto);
					request.getSession().setAttribute(CommonConstant.SESSION_KEY_USER_ADMIN, dto);
				}
				request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
			}
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息修改
	 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"update")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户信息")
	@ApiOperation(value = "信息修改")
	public Response update(@Validated OrgUserDto dto, BindingResult bindingResult) throws IOException {
		log.info("OrgUserController update.........");
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
				OrgUserDto orgUser = JwtUtil.getSubject(OrgUserDto.class);
				if(orgUser !=null){
					if(ValidatorUtil.isEmpty(dto.getAccount())){
						dto.setAccount(orgUser.getAccount());
					}
				}
				result.message=orgUserService.updateData(dto);
				OrgUserDto newOrgUser=orgUserService.findDataById(dto);
				request.getSession().setAttribute(CommonConstant.SESSION_KEY_USER,newOrgUser);
				request.getSession().setAttribute(CommonConstant.SESSION_KEY_USER_ADMIN,newOrgUser);
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
	@RequiresPermissions(value={"orgUser:editPwd"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"updatePwd")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户密码")
	@ResponseBody
	@ApiOperation(value = "密码修改")
	public Response updatePwd(@Validated OrgUserDto dto) throws IOException {
		log.info("OrgUserController updatePwd.........");
		Response result = new Response();
		try {
			if(dto==null||ValidatorUtil.isNullEmpty(dto.getOldpwd())||ValidatorUtil.isNullEmpty(dto.getNewpwd())||ValidatorUtil.isNullEmpty(dto.getConfirmpwd())) return Response.error("参数异常!");
			if(!dto.getNewpwd().equals(dto.getConfirmpwd()))throw new RuntimeException("两次密码不一致!");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "updatePwd." + dto.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			OrgUserDto orgUser =JwtUtil.getSubject(OrgUserDto.class);
			if(orgUser ==null){
				throw new RuntimeException("登陆超时!");
			}
			result.message=orgUserService.updatePwd(dto);
			request.getSession().setAttribute(acPrefix + "updatePwd." + dto.getToken(), "1");
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
}