package com.wu1g.web.controller.member.pano;

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
import com.wu1g.api.sys.ISysMaterialService;
import com.wu1g.vo.sys.SysMaterial;

/**
 * <p>全景_素材。
 */
@Controller
@Slf4j
public class PanoMaterialController extends BaseController {
	@Autowired
	protected ISysMaterialService sysMaterialService;
	
	private static final String acPrefix="/h/pano/sysMaterial/";
	private static final String init = "member/pano/pano_material";
	private static final String edit = "member/pano/pano_material_edit";
	private static final String list = "member/pano/pano_material_list";
	private static final String success = "redirect:"+acPrefix+"init";
	
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("sysMaterial:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("sysMaterialController init.........");
		return init;
	}
	/**
	 * <p> 信息分页 (未删除)。
	 */
	@RequiresPermissions("sysMaterial:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"page")
	public String page(HttpServletRequest request, HttpServletResponse response,SysMaterial dto) {
		log.info("sysMaterialController page.........");
        Response result = new Response();
		try {
            if(dto==null)  dto = new SysMaterial();
			dto.setMember(true);
			dto.setCreateId(getUser().getId());//会员id

            PageInfo<?> page=new PageInfo<>(sysMaterialService.findDataIsPage(dto));
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
	@RequiresPermissions("sysMaterial:edit")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"edit/{id}")
	public String edit(SysMaterial dto, @PathVariable("id") Long id) {
		log.info("sysMaterialController edit.........");
		Response result = new Response();
		try {
            int pageNum=getPageSize(dto);
            if (id!=null) {
                if(dto==null)dto=new SysMaterial();
                dto.setId(id);
                dto=sysMaterialService.findDataById(dto);
            }
            if(dto==null||0==id){
                dto=new SysMaterial();
                dto.setNewFlag(1);
            }
            dto.setPageNum( pageNum );
			dto.setMember(true);
			dto.setCreateId(getUser().getId());//会员id
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
	@RequiresPermissions("sysMaterial:del")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"del/{id}")
	@ALogOperation(type="删除",desc="全景_素材")
	public String del(@PathVariable("id") Long id, RedirectAttributesModelMap modelMap) {
		log.info("sysMaterialController del.........");
		Response result = new Response();
		try {
			SysMaterial dto=new SysMaterial();
            dto.setId(id);
			dto.setMember(true);
			dto.setCreateId(getUser().getId());//会员id
            result.message = sysMaterialService.deleteDataById(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		modelMap.addFlashAttribute("result", result);
		return success;
	}
	/**
	 * <p> 信息保存
	 */
	@RequiresPermissions(value={"sysMaterial:add","sysMaterial:edit"},logical=Logical.OR)
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"save")
	@RfAccount2Bean
	@ALogOperation(type="修改",desc="全景_素材")
	public String save(@Validated SysMaterial dto, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
		log.info("sysMaterialController save.........");
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
					dto.setMember(true);
					dto.setCreateId(getUser().getId());//会员id
					result.message=sysMaterialService.saveOrUpdateData(dto);
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