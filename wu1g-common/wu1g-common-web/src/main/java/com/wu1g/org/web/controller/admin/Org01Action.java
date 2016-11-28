/*	
 * 组织架构_部门  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.web.controller.admin;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.org.api.IOrgDepartmentService;
import com.wu1g.org.vo.OrgDepartment;
import com.wu1g.org.vo.OrgUser;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>组织架构_部门  ACTION类。</p>	
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
public class Org01Action extends BaseController {

	private static final long serialVersionUID = -905929872130603565L;
	private static final transient Logger log = LoggerFactory.getLogger(Org01Action.class);
	/**组织架构_部门 业务处理*/
	@Autowired
	private IOrgDepartmentService orgDepartmentService;
	
	//组织架构_部门 管理
	private static final String acPrefix="/org01.";
	private static final String init = "admin/org/org01";
	private static final String edit = "admin/org/org01_01";
	private static final String infoList = "admin/org/org01_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgDept:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("Org01Action init.........");
		request.setAttribute( "beans", orgDepartmentService.findDataTree(null) );
		return init;
	}
	/**
	 * <p> 编辑。</p>
	 * <ol>
	 * [功能概要] 
	 * <li>编辑。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgDept:edit")
	@RequestMapping(value=acPrefix+"edit/{id}")
	public String edit(OrgDepartment bean, @PathVariable("id") String id) {
		log.info("Org01Action edit.........");
		int pageNum = 0;
		if(bean!=null && bean.getPageNum()!=null){
			pageNum=bean.getPageNum();
		}
		if(ValidatorUtil.notEmpty(id)){
			OrgDepartment bean1=new OrgDepartment();
			bean1.setId(id);//ID
			bean=orgDepartmentService.findDataById(bean1);
		}
		if(bean==null){
			bean=new OrgDepartment();
			bean.setId(IdUtil.createUUID(32));//ID
		}
		bean.setPageNum(pageNum);
		request.setAttribute( "bean", bean );
		//--部门树
		request.setAttribute( "beans", orgDepartmentService.findDataTree(null) );
		return edit;
	}
	/**
	 * <p> 删除。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>逻辑删除。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("orgDept:del")
	@RequestMapping(value=acPrefix+"del/{id}")
	public String del(@PathVariable("id") String id) {
		log.info("Org01Action del.........");
		OrgDepartment bean1=new OrgDepartment();
		bean1.setId(id);//ID
		String msg="1";
		try {
			msg=orgDepartmentService.deleteDataById(bean1);
		} catch (Exception e) {
			msg=e.getMessage();
		}
		request.setAttribute("msg",msg);
		
		return success;
	}
//	/**
//	 * <p> 删除。 </p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>物理删除。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("orgDept:delph")
//	@RequestMapping(value=acPrefix+"delph/{id}")
//	public String delph(@PathVariable("id") String id) {
//		log.info("Org01Action del ph.........");
//		OrgDepartment bean1=new OrgDepartment();
//		bean1.setId(id);//ID
//		String msg="1";
//		try {
//			msg=orgDepartmentService.deleteData(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
	/**
	 * <p> 信息保存 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>新增。
	 * <li>修改。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions(value={"orgDept:add","orgDept:edit"},logical=Logical.OR)
	@RequestMapping(value=acPrefix+"save")
	public String save(OrgDepartment bean) {
		log.info("Org01Action save.........");
		if(bean!=null){
			String msg="1";
			try {
				if(ValidatorUtil.isEmpty(bean.getName())){
					msg="保存失败!信息为空!";
				}else{
					OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
					if(user!=null){
						bean.setCreateIp(getIpAddr());
						bean.setCreateId(user.getId());
						bean.setUpdateIp(getIpAddr());
						bean.setUpdateId(user.getId());
					}
					msg=orgDepartmentService.saveOrUpdateData(bean);
				}
			} catch (Exception e) {
				msg=e.getMessage();
			}
			request.setAttribute("msg",msg);
		}else{
			request.setAttribute("msg", "信息保存失败!");
		}
		return success;
	}
//	/**
//	 * <p> 预览。</p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>预览。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("orgDept:view")
//	@RequestMapping(value=acPrefix+"view/{id}")
//	public String view(@PathVariable("id") String id) {
//		log.info("Org01Action view.........");
//		if(ValidatorUtil.notEmpty(id)){
//			OrgDepartment bean1=new OrgDepartment();
//			bean1.setId(id);//ID
//			request.setAttribute( "bean",orgDepartmentService.findDataById(bean1));
//		}
//		return "view";
//	}
//	/**
//	 * <p> 回收站。</p>
//	 * <ol>
//	 * [功能概要] 
//	 * <li>回收站。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("orgDept:recyle")
//	@RequestMapping(value=acPrefix+"recycle")
//	public String recycle(OrgDepartment bean) {
//		log.info("Org01Action recycle.........");
//		bean.setPageSize(CommonConstant.PAGEROW_DEFAULT_COUNT);
//		//列表
//		PageInfo<?> page=new PageInfo<>(orgDepartmentService.findDataIsPage(bean));
//		request.setAttribute( "beans", page.getList() );
//		//分页对象-JSP标签使用-
//		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
//		return "recycle";
//	}
//	/**
//	 * <p> 恢复。</p>
//	 * <ol>[功能概要] 
//	 * <li>恢复逻辑删除的数据。
//	 * </ol>
//	 * @return 转发字符串
//	 */
//	@RequiresPermissions("orgDept:recovery")
//	@RequestMapping(value=acPrefix+"recovery/{id}")
//	public String recovery(@PathVariable("id") String id) {
//		log.info("Org01Action recovery.........");
//		//========创建bena对象=============
//		OrgDepartment bean1=new OrgDepartment();
//		bean1.setId(id);//ID
//		String msg="1";
//		try {
//			msg=orgDepartmentService.recoveryDataById(bean1);
//		} catch (Exception e) {
//			msg=e.getMessage();
//		}
//		request.setAttribute("msg",msg);
//		
//		return success;
//	}
}