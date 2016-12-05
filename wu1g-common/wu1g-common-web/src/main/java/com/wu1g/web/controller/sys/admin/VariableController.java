/*	
 * 系统_数据字典  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.sys.admin;

import com.github.pagehelper.PageInfo;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.vo.OrgUser;
import com.wu1g.sys.api.IVariableService;
import com.wu1g.sys.vo.SysVariable;
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

import java.util.List;

/**
 * <p>系统_数据字典  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class VariableController extends BaseController {

	private static final long serialVersionUID = -184287954681953050L;
	@Autowired
	protected IVariableService variableService;
	//系统_数据字典 管理
	private static final String acPrefix="/sys/dic/";
	private static final String init = "admin/sys/sys_dic";
	private static final String edit = "admin/sys/sys_dic_edit";
	private static final String list = "admin/sys/sys_dic_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("VariableController init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。
	 * <ol>
	 * [功能概要] 
	 * <li>信息列表。
	 */
	@RequiresPermissions("sysDic:menu")
	@RequestMapping(value=acPrefix+"list")
	public String list(SysVariable bean) {
		log.info("VariableController list.........");
		if(bean==null){
			bean = new SysVariable();
		}
		PageInfo<?> page=new PageInfo<>(variableService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return list;
	}
	/**
	 * <p> 编辑。
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 */
	@RequiresPermissions("sysDic:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit(SysVariable bean,@PathVariable("id") String id) {
		log.info("VariableController edit.........");
		int pageNum = 1;
		if(bean!=null && bean.getPageNum()!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			SysVariable bean1=new SysVariable();
			bean1.setId(id);
			bean=variableService.findDataById(bean1);
		}
		if(bean==null||"add".equals(id)){
			bean=new SysVariable();
			bean.setId(IdUtil.createUUID(22));
            bean.setNewFlag("1");
		}
		bean.setPageNum( pageNum );
		request.setAttribute("bean",bean);
		return edit;
	}
	/**
	 * <p> 删除。
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 */
	@RequiresPermissions("sysDic:del")
	@RequestMapping(value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="数据字典信息")
	public String del(@PathVariable("id") String id, RedirectAttributesModelMap modelMap) {
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
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"sysDic:add","sysDic:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
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