package com.wu1g.web.controller.admin.sys;

import com.github.pagehelper.PageInfo;
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

import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.web.controller.BaseController;
import com.wu1g.api.sys.ISysCategoryService;
import com.wu1g.vo.sys.SysCategory;

/**
 * <p>系统_类目。
 */
@Controller
@Slf4j
public class SysPanoCategoryController extends BaseController {
	@Autowired
	protected ISysCategoryService sysCategoryService;
	
	private static final String acPrefix="/h/sys/sysCategory/";
	private static final String init = "admin/sys/sys_category";
	private static final String edit = "admin/sys/sys_category_edit";
	private static final String list = "admin/sys/sys_category_list";
	private static final String success = "redirect:"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("sysCategory:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("SysCategoryController init.........");
		return init;
	}
	/**
	 * <p> 信息树json。
	 */
	@RequiresPermissions("sysCategory:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"jsonTree")
	@ResponseBody
	public Response jsonTree() {
		log.info("SysCategoryController jsonTree.........");
		Response result=new Response();
		try {
			SysCategory sysCategory=new SysCategory();
			sysCategory.setType(2);
			result.data=sysCategoryService.findDataTree(sysCategory);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		return result;
	}
	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("sysCategory:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page")
	public String page(HttpServletRequest request, HttpServletResponse response,SysCategory dto) {
		log.info("SysCategoryController page.........");
        Response result = new Response();
		try {
            if(dto==null){
                dto = new SysCategory();
            }
			dto.setType(2);
            PageInfo<?> page=new PageInfo<>(sysCategoryService.findDataIsPage(dto));
            request.setAttribute( "beans", page.getList() );
            //分页对象
            request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		} catch (Exception e) {
           result = Response.error(e.getMessage());
        }
         request.setAttribute("result",result);
		return list;
	}

	/**
	 * <p> 编辑。
	 */
	@RequiresPermissions("sysCategory:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(SysCategory dto,@PathVariable("id") Long id) {
		log.info("SysCategoryController edit.........");
		Response result = new Response();
		try {
            int pageNum=getPageSize(dto);
            if (id!=null) {
                if(dto==null)dto=new SysCategory();
                dto.setId(id);
                dto=sysCategoryService.findDataById(dto);
			}
			if(dto==null||0==id){
				dto=new SysCategory();
				dto.setNewFlag(1);
			}
			dto.setType(2);
			dto.setPageNum( pageNum );
            request.setAttribute("bean",dto);
			request.setAttribute("nodes",sysCategoryService.findDataTree(dto));
		} catch (Exception e) {
           result = Response.error(e.getMessage());
        }
         request.setAttribute("result",result);
		return edit;
	}

	/**
	 * <li>删除。
	 */
	@RequiresPermissions("sysCategory:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="系统_类目")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
		log.info("SysCategoryController del.........");
		Response result = new Response();
		try {
			SysCategory dto=new SysCategory();
            dto.setId(id);
			dto.setType(2);
            result.message = sysCategoryService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"sysCategory:add","sysCategory:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="系统_类目")
	public String save(@Validated SysCategory dto,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
		log.info("SysCategoryController save.........");
		Response result = new Response();
		if(dto!=null){
			try {
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
					dto.setType(2);
					result.message=sysCategoryService.saveOrUpdateData(dto);
					result.data = dto.getId();
					request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
				}
			} catch (Exception e) {
				result = Response.error(e.getMessage());
			}
		} else {
			result = Response.error("信息保存失败!");
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
}