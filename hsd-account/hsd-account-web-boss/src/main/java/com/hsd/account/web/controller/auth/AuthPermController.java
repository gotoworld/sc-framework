package com.hsd.account.web.controller.auth;

import com.hsd.account.api.auth.IAuthPermService;
import com.hsd.account.dto.auth.AuthPermDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.annotation.RfAccount2Bean;
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

@Api(description = "权限_权限信息")
@RestController
@Slf4j
public class AuthPermController extends BaseController {

	private static final String acPrefix="/boss/account/auth/perm/";

	@Autowired
	private IAuthPermService AuthPermService;
	/**
	 * <p> 信息树json。
	 */
	@RequiresPermissions("AuthPermDto:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
	@ApiOperation(value = "信息树")
	@ResponseBody
	public Response jsonTree() {
		log.info("AuthPermDtoController jsonTree.........");
		Response result = new Response(0,"seccuss");
		try {
			result.data=AuthPermService.findDataIsTree(new AuthPermDto());
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
	public Response info(@PathVariable("id") String id) {
		log.info("AuthPermDtoController info.........");
		Response result = new Response();
		try {
			AuthPermDto dto=new AuthPermDto();
			dto.setId(id);
			result.data=AuthPermService.findDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("AuthPermDto:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="权限信息")
	@ApiOperation(value = "逻辑删除")
	public Response del(@PathVariable("id") String id) {
		log.info("AuthPermDtoController del.........");
		Response result = new Response(0,"seccuss");
		try {
			AuthPermDto dto=new AuthPermDto();
			dto.setId(id);//权限id
			result.message=AuthPermService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"AuthPermDto:edit","AuthPermDto:add"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="权限信息")
	@ApiOperation(value = "信息保存")
	public Response save(@Validated AuthPermDto dto, BindingResult bindingResult) {
		log.info("AuthPermDtoController save.........");
		Response result = null;
		try {
			if(dto==null) throw new RuntimeException("参数异常!");
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
			}else{
				result=AuthPermService.saveOrUpdateData(dto);
				request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
			}
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
}