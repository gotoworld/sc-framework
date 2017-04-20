/*	
 * 组织架构_用户  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.web.controller.member.org;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wu1g.api.auth.IAuthRoleService;
import com.wu1g.api.org.IOrgDeptService;
import com.wu1g.api.org.IOrgUserService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.vo.org.OrgUser;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>组织架构_用户  ACTION类。
 */
@Controller
@Slf4j
public class MemOrgUserController extends BaseController {
	@Autowired
	private IOrgUserService orgUserService;
	
	//组织架构_用户 管理
	private static final String acPrefix="/m/org/user/";
	private static final String editUser = "member/org/org_user_myinfo";
	private static final String editPwd = "member/org/org_user_editpwd";
	private static final String success = "redirect:"+acPrefix+"init";
	
	/**
	 * <p> 编辑。
	 */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"editUser/{id}")
	public String editUser(@PathVariable("id") Long id) {
		log.info("OrgUserController editUser.........");
		if(0!=id){
			OrgUser bean=new OrgUser();
			bean.setId(id);
			bean=orgUserService.findDataById(bean);
			request.setAttribute( "bean", bean );
		}
		return editUser;
	}
	/**判断用户id是否存在
	 * @throws IOException */
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"isUidYN/{uid}")
	public String isUidYN(@PathVariable("uid") String uid) throws IOException{
		String msg="true";
		JSONObject json = new JSONObject();
	    PrintWriter out=response.getWriter();
	    response.setContentType("application/json");
		try {
			msg=orgUserService.isUidYN(uid);
		} catch (Exception e) {
			//msg=e.getMessage();
		}
		 if("0".equals(msg)){
			 try {
	            json.put("valid",true);
	         } catch (JSONException e) {}
	    }else {
	        try {
	            json.put("valid",false);
	        } catch (JSONException e) {}
	    }
	    out.print(json);
		return null;
	}

	/**
	 * <p> 密码修改。
	 */
	@RequiresPermissions(value={"memOrgUser:editPwd"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"editPwd/{id}")
	public String editPwd(@PathVariable("id") Long id) {
		log.info("OrgUserController editPwd.........");
		if(0!=id){
			OrgUser bean=new OrgUser();
			bean.setId(id);
			bean=orgUserService.findDataById(bean);
			request.setAttribute( "bean", bean );
		}
		return editPwd;
	}
	/**
	 * <p> 密码修改
	 */
	@RequiresPermissions(value={"memOrgUser:editPwd"})
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"updatePwd")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="用户密码")
	@ResponseBody
	public Response updatePwd(@Validated OrgUser bean) throws IOException {
		log.info("OrgUserController updatePwd.........");
		if(bean==null||ValidatorUtil.isNullEmpty(bean.getOldpwd())||ValidatorUtil.isNullEmpty(bean.getNewpwd())||ValidatorUtil.isNullEmpty(bean.getConfirmpwd())) return Response.error("参数异常!");

		Response result = new Response();
		try {
			if(!bean.getNewpwd().equals(bean.getConfirmpwd()))throw new RuntimeException("两次密码不一致!");
			if ("1".equals(request.getSession().getAttribute(acPrefix + "updatePwd." + bean.getToken()))) {
				throw new RuntimeException("请不要重复提交!");
			}
			OrgUser orgUser = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
			if(orgUser ==null){
				throw new RuntimeException("登陆超时!");
			}
			result.message=orgUserService.updatePwd(bean);
			request.getSession().setAttribute(acPrefix + "updatePwd." + bean.getToken(), "1");
		} catch (Exception e) {
			result = Response.error(e.getMessage());
		}
		return result;
	}
}