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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.ALogOperation;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.web.controller.BaseController;
import com.wu1g.api.sys.ISysPanoPluginsService;
import com.wu1g.vo.sys.SysPanoPlugins;

/**
 * <p>系统_全景插件。
 */
@Controller
@Slf4j
public class SysPanoPluginsController extends BaseController {
	@Autowired
	protected ISysPanoPluginsService sysPanoPluginsService;
	
	private static final String acPrefix="/h/sys/sysPanoPlugins/";
	private static final String init = "admin/sys/sys_pano_plugins";
	private static final String edit = "admin/sys/sys_pano_plugins_edit";
	private static final String list = "admin/sys/sys_pano_plugins_list";
	private static final String success = "redirect:"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("sysPanoPlugins:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("SysPanoPluginsController init.........");
		return init;
	}
	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("sysPanoPlugins:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page")
	public String page(HttpServletRequest request, HttpServletResponse response,SysPanoPlugins dto) {
		log.info("SysPanoPluginsController page.........");
        Response result = new Response();
		try {
            if(dto==null){
                dto = new SysPanoPlugins();
            }
            PageInfo<?> page=new PageInfo<>(sysPanoPluginsService.findDataIsPage(dto));
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
	@RequiresPermissions("sysPanoPlugins:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(SysPanoPlugins dto,@PathVariable("id") Long id) {
		log.info("SysPanoPluginsController edit.........");
		Response result = new Response();
		try {
            int pageNum= getPageNum(dto);
            if (id!=null) {
                if(dto==null)dto=new SysPanoPlugins();
                dto.setId(id);
                dto=sysPanoPluginsService.findDataById(dto);
            }
            if(dto==null||0==id){
                dto=new SysPanoPlugins();
                dto.setNewFlag(1);
            }
            dto.setPageNum( pageNum );
            request.setAttribute("bean",dto);
		} catch (Exception e) {
           result = Response.error(e.getMessage());
        }
         request.setAttribute("result",result);
		return edit;
	}

	/**
	 * <li>删除。
	 */
	@RequiresPermissions("sysPanoPlugins:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="系统_全景插件")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
		log.info("SysPanoPluginsController del.........");
		Response result = new Response();
		try {
			SysPanoPlugins dto=new SysPanoPlugins();
            dto.setId(id);
            result.message = sysPanoPluginsService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"sysPanoPlugins:add","sysPanoPlugins:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="系统_全景插件")
	public String save(@Validated SysPanoPlugins dto,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
		log.info("SysPanoPluginsController save.........");
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
					result.message=sysPanoPluginsService.saveOrUpdateData(dto);
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