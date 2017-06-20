package com.vr.web.controller.member.pano;

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

import com.vr.framework.Response;
import com.vr.framework.annotation.ALogOperation;
import com.vr.framework.annotation.RfAccount2Bean;
import com.vr.framework.util.CommonConstant;
import com.vr.web.controller.BaseController;
import com.vr.api.sys.ISysMaterialService;
import com.vr.vo.sys.SysMaterial;

/**
 * <p>全景_素材。
 */
@Controller
@Slf4j
public class MemPanoMaterialController extends BaseController {
	@Autowired
	protected ISysMaterialService memMaterialService;
	
	private static final String acPrefix="/m/material/";
	private static final String init = "member/pano/pano_material";
	private static final String edit = "member/pano/pano_material_edit";
	private static final String list = "member/pano/pano_material_list";
	private static final String success = "redirect:"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("memMaterial:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("memMaterialController init.........");
		return init;
	}
	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("memMaterial:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page")
	public String page(HttpServletRequest request, HttpServletResponse response,SysMaterial dto) {
		log.info("memMaterialController page.........");
        Response result = new Response();
		try {
            if(dto==null)  dto = new SysMaterial();
			setMember(dto);//会员标记
            PageInfo<?> page=new PageInfo<>(memMaterialService.findDataIsPage(dto));
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
	@RequiresPermissions("memMaterial:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(SysMaterial dto, @PathVariable("id") Long id) {
		log.info("memMaterialController edit.........");
		Response result = new Response();
		try {
            int pageNum= getPageNum(dto);
            if (id!=null) {
                if(dto==null)dto=new SysMaterial();
                dto.setId(id);
                dto=memMaterialService.findDataById(dto);
            }
            if(dto==null||0==id){
                dto=new SysMaterial();
                dto.setNewFlag(1);
            }
            dto.setPageNum( pageNum );
			setMember(dto);//会员标记
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
	@RequiresPermissions("memMaterial:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="全景_素材")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
		log.info("memMaterialController del.........");
		Response result = new Response();
		try {
			SysMaterial dto=new SysMaterial();
            dto.setId(id);
			setMember(dto);//会员标记
            result.message = memMaterialService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"memMaterial:add","memMaterial:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="全景_素材")
	public String save(@Validated SysMaterial dto, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
		log.info("memMaterialController save.........");
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
					setMember(dto);//会员标记
					result.message=memMaterialService.saveOrUpdateData(dto);
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