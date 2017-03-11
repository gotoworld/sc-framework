/*	
 * 系统_数据字典  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.web.controller.admin.sys;

import com.github.pagehelper.PageInfo;
import com.wu1g.api.sys.ISysVariableService;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysVariable;
import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>系统_数据字典  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class SysVariableController extends BaseController {
	@Autowired
	protected ISysVariableService variableService;
	//系统_数据字典 管理
	private static final String acPrefix="/sys/dic/";
	private static final String init = "admin/sys/sys_dic";
	private static final String edit = "admin/sys/sys_dic_edit";
	private static final String list = "admin/sys/sys_dic_list";
	private static final String list1 = "admin/sys/sys_dic_list1";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("VariableController init.........");



		return init;
	}
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
	@ResponseBody
	public Response jsonTree() {
		log.info("VariableController jsonTree.........");
		Response result=new Response();
		try {
			result.data=variableService.findDataTree(null);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息列表 (未删除)。
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"list")
	public String list(HttpServletRequest request, HttpServletResponse response,SysVariable bean) {
		log.info("VariableController list.........");
		if(bean==null){
			bean = new SysVariable();
		}
		PageInfo<?> page=new PageInfo<>(variableService.findDataIsPage(bean));
		System.out.println(page);
		request.setAttribute( "beans", page.getList() );
		//分页对象
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return list;
	}
	/**
	 * <p> 编辑。
	 */
	@RequiresPermissions("sysDic:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(SysVariable bean,@PathVariable("id") Long id) {
		log.info("VariableController edit.........");
		int pageNum=getPageSize(bean);
		if(0!=id){
			SysVariable bean1=new SysVariable();
			bean1.setId(id);
			bean=variableService.findDataById(bean1);
		}
		if(bean==null||0==id){
			bean=new SysVariable();
            bean.setNewFlag(1);
		}
		bean.setPageNum( pageNum );
		request.setAttribute("bean",bean);
		request.setAttribute("beans",variableService.findDataTree(null));
		return edit;
	}
	/**
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("sysDic:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="数据字典信息")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
		log.info("VariableController del.........");
		Response result = new Response();
		try {
			SysVariable bean1=new SysVariable();
			bean1.setId(id);
			result.message=variableService.deleteDataById(bean1);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("msg", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"sysDic:add","sysDic:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="数据字典信息")
	public String save(@Validated SysVariable bean,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
		log.info("VariableController save.........");
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
					result.message=variableService.saveOrUpdateData(bean);
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