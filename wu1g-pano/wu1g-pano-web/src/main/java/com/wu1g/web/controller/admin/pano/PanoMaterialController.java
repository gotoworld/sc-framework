package com.wu1g.web.controller.admin.pano;

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
import com.wu1g.api.pano.IPanoMaterialService;
import com.wu1g.vo.pano.PanoMaterial;

/**
 * <p>全景_素材。
 */
@Controller
@Slf4j
public class PanoMaterialController extends BaseController {
	@Autowired
	protected IPanoMaterialService panoMaterialService;
	
	private static final String acPrefix="/h/pano/panoMaterial/";
	private static final String init = "admin/pano/pano_material";
	private static final String edit = "admin/pano/pano_material_edit";
	private static final String list = "admin/pano/pano_material_list";
	private static final String success = "redirect:"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("panoMaterial:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("PanoMaterialController init.........");
		return init;
	}
	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("panoMaterial:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page")
	public String page(HttpServletRequest request, HttpServletResponse response,PanoMaterial dto) {
		log.info("PanoMaterialController page.........");
        Response result = new Response();
		try {
            if(dto==null){
                dto = new PanoMaterial();
            }
            PageInfo<?> page=new PageInfo<>(panoMaterialService.findDataIsPage(dto));
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
	@RequiresPermissions("panoMaterial:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(PanoMaterial dto,@PathVariable("id") Long id) {
		log.info("PanoMaterialController edit.........");
		Response result = new Response();
		try {
            int pageNum=getPageSize(dto);
            if (id!=null) {
                if(dto==null)dto=new PanoMaterial();
                dto.setId(id);
                dto=panoMaterialService.findDataById(dto);
            }
            if(dto==null||0==id){
                dto=new PanoMaterial();
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
	@RequiresPermissions("panoMaterial:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="全景_素材")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
		log.info("PanoMaterialController del.........");
		Response result = new Response();
		try {
			PanoMaterial dto=new PanoMaterial();
            dto.setId(id);
            result.message = panoMaterialService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"panoMaterial:add","panoMaterial:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="全景_素材")
	public String save(@Validated PanoMaterial dto,BindingResult bindingResult,RedirectAttributesModelMap modelMap) {
		log.info("PanoMaterialController save.........");
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
					result.message=panoMaterialService.saveOrUpdateData(dto);
					result.data = dto.getId();
					request.getSession().setAttribute(acPrefix + "save." + dto.getToken(), "1");
				}
			} catch (Exception e) {
				result = Response.error(e.getMessage());
			}
		} else {
			result = Response.error("信息保存异常!");
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
}