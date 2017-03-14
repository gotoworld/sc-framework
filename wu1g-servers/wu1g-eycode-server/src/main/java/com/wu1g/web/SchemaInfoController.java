package com.wu1g.web;

import com.wu1g.domain.SchemaInfo;
import com.wu1g.framework.Response;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.service.ISchemaInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

@Controller
@Slf4j
public class SchemaInfoController extends BaseController{
	@Autowired
	protected ISchemaInfoService schemaInfoService;
	private static final String acPrefix="/";

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix)
	public String init(SchemaInfo dto) {
		log.info("SchemaInfoController init.........");
		Response result=new Response();
		try {
			result.data=schemaInfoService.findDbIsList(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		request.setAttribute("result",result);
		return "init";
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"table")
	public String table(SchemaInfo dto) {
		log.info("SchemaInfoController table.........");
		Response result=new Response();
		try {
			result.data=schemaInfoService.findTableIsList(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		request.setAttribute("result",result);
		request.setAttribute("dbs",dto.getDbs());
		return "table";
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"column")
	public String column(SchemaInfo dto) {
		log.info("SchemaInfoController column.........");
		Response result=new Response();
		try {
			result.data=schemaInfoService.findColumnIsList(dto);
		} catch (Exception e) {
			result=Response.error(e.getMessage());
		}
		request.setAttribute("result",result);
		request.setAttribute("dbs",dto.getDbs());
		request.setAttribute("tables",dto.getTables());
		return "column";
	}
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"templateRender")
	@RfAccount2Bean
	public String templateRender(@Validated SchemaInfo bean, BindingResult bindingResult, RedirectAttributesModelMap modelMap) {
		log.info("SchemaInfoController templateRender.........");
		Response result = new Response();
		if(bean!=null){
			try {
				if ("1".equals(request.getSession().getAttribute(acPrefix + "templateRender." + bean.getToken()))) {
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
					result.message=schemaInfoService.templateRender(bean);
					request.getSession().setAttribute(acPrefix + "templateRender." + bean.getToken(), "1");
				}
			} catch (Exception e) {
				result = Response.error(e.getMessage());
			}
		} else {
			result = Response.error("代码生成失败!");
		}
		modelMap.addFlashAttribute("msg", result);
		return "redirect:"+acPrefix;
	}
}