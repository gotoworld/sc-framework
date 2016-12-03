/*	
 * 权限_角色信息  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.auth.admin;

import com.github.pagehelper.PageInfo;
import com.wu1g.auth.api.IAuthPermService;
import com.wu1g.auth.api.IAuthRoleService;
import com.wu1g.auth.vo.AuthRole;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>权限_角色信息  ACTION类。
 * <ol>[功能概要] 
 * <li>初始化。 
 * <li>信息列表(未删除)。  
 * <li>编辑页面(页面)(新增or修改)。 
 * <li>信息保存(功能)(新增or修改)。 
 *</ol> 
 * @author easycode
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class AuthRoleController extends BaseController {

	private static final long serialVersionUID = -250454793558646875L;
	/**权限_角色信息 业务处理*/
	@Autowired
	private IAuthRoleService authRoleService;
	/**权限_权限信息 业务处理*/
	@Autowired
	private IAuthPermService authPermService;
	
	//权限_角色信息 管理
	private static final String acPrefix="/auth03/";
	private static final String init = "admin/auth/auth03";
	private static final String edit = "admin/auth/auth03_01";
	private static final String list = "admin/auth/auth03_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	/**
	 * <p> 初始化处理。
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authRole:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("AuthRoleController init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。
	 * <ol>
	 * [功能概要] 
	 * <li>信息列表。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authRole:menu")
	@RequestMapping(value=acPrefix+"list")
	public String list(AuthRole bean) {
		log.info("AuthRoleController list.........");
		if(bean==null){
			bean = new AuthRole();
		}
		//每页显示条数
		bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
		//信息列表
		PageInfo<?> page =new PageInfo<>(authRoleService.findDataIsPage(bean));
		//分页对象-JSP标签使用-
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return list;
	}
	/**
	 * <p> 编辑。
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authRole:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit(AuthRole bean,@PathVariable("id") String id){
		log.info("AuthRoleController edit.........");
		int pageNum = 0;
		if(bean!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			AuthRole bean1=new AuthRole();
			bean1.setId(id);//角色ID
			bean=authRoleService.findDataById(bean1);
			if(bean!=null){
				//获取当前角色所有的功能
				Map xdto=new HashMap();
				xdto.put("roleId", bean.getId());
				request.setAttribute("myPermList", authPermService.findPermDataIsListByRoleId(xdto));
			}
		}
		if(bean==null){
			bean=new AuthRole();
			bean.setId(IdUtil.createUUID(22));//角色ID
		}
		bean.setPageNum( pageNum );
		
		request.setAttribute("bean",bean);
		//权限信息树
		request.setAttribute("permTree",authPermService.findDataTree(null));
		
		return edit;
	}
	/**
	 * <p> 删除。
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("authRole:del")
	@ALogOperation(type="删除",desc="角色信息")
	public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
		log.info("AuthRoleController del.........");
		Response result = new Response();
		try {
			AuthRole bean1=new AuthRole();
			bean1.setId(id);//角色ID
			result.message=authRoleService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("msg", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"authRole:add","authRole:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="角色信息")
	public String save(@Validated AuthRole bean, RedirectAttributesModelMap modelMap, BindingResult bindingResult) {
		log.info("AuthRoleController save.........");
		Response result = new Response();
		if(bean!=null){
			try {
				if ("1".equals(request.getSession().getAttribute(acPrefix + "save." + bean.getToken()))) {
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
					result.message=authRoleService.saveOrUpdateData(bean);
					result.data = bean.getId();
					request.getSession().setAttribute(acPrefix + "save." + bean.getToken(), "1");
				}
			} catch (Exception e) {
				result = Response.error(e.getMessage());
			}
		} else {
			result = Response.error("信息保存失败!");
		}
		modelMap.addFlashAttribute("msg", result);
		return success;
	}
}