package com.wu1g.web;

import com.wu1g.domain.SchemaConf;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class SchemaInfoController extends BaseController {
    @Autowired
    protected ISchemaInfoService schemaInfoService;
    private static final String acPrefix = "/";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix)
    public String init(@ModelAttribute SchemaInfo dto) {
        log.info("SchemaInfoController init.........");
        Response result = new Response();
        try {
            result.data = schemaInfoService.findDbIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        request.setAttribute("result", result);
        return "init";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "table")
    public String table(@ModelAttribute SchemaInfo dto) {
        log.info("SchemaInfoController table.........");
        Response result = new Response();
        try {
            result.data = schemaInfoService.findTableIsList(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        request.setAttribute("result", result);
        request.setAttribute("dbs", dto.getDbs());
        return "table";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "column")
    public String column(@ModelAttribute SchemaInfo dto) {
        log.info("SchemaInfoController column.........");
        Response result = new Response();
        try {
            Map<String,List> resultMap=new HashMap<>();
            if(dto.getTables()!=null){
                dto.getTables().forEach(tableName->{
                    SchemaInfo tableSchemaInfo=new SchemaInfo();
                    tableSchemaInfo.setDbName(request.getParameter(tableName+"_db"));
                    tableSchemaInfo.setTableName(tableName);
                    try {
                        resultMap.put(tableName,schemaInfoService.findColumnIsList(tableSchemaInfo));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            result.data = resultMap;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        request.setAttribute("result", result);
//        request.setAttribute("dbs", dto.getDbs());
        request.setAttribute("tables", dto.getTables());
        return "column";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "templateRender")
    @RfAccount2Bean
    public String templateRender(SchemaInfo dto) {
        log.info("SchemaInfoController templateRender.........");
        Response result = new Response();
        if (dto != null && dto.getTables()!=null && dto.getTables().size()>0) {
            try {
                //1.获取表集合
                List<SchemaInfo> tablesConf=new ArrayList();
                dto.getTables().forEach(tableName->{
                    SchemaInfo tableSchemaInfo=new SchemaInfo();
                    tableSchemaInfo.setDbName(request.getParameter(tableName+"_db"));
                    tableSchemaInfo.setTableName(tableName);
                    tableSchemaInfo.setTableComment(request.getParameter(tableName+"_comment"));
                    SchemaConf schemaConf=new SchemaConf();
                    schemaConf.set_domain(request.getParameter(tableName+"_domain"));//
                    schemaConf.set_mybatis(request.getParameter(tableName+"_mybatis"));//
                    schemaConf.set_dao(request.getParameter(tableName+"_dao"));//
                    schemaConf.set_service(request.getParameter(tableName+"_service"));//
                    schemaConf.set_api(request.getParameter(tableName+"_api"));//
                    schemaConf.set_web_rest(request.getParameter(tableName+"_web_rest"));//
                    schemaConf.set_web_ctrl(request.getParameter(tableName+"_web_ctrl"));//
                    schemaConf.set_view_ng(request.getParameter(tableName+"_view_ng"));//
                    schemaConf.set_view_btl(request.getParameter(tableName+"_view_btl"));//
                    schemaConf.set_insert(request.getParameter(tableName+"_insert"));//
                    schemaConf.set_insertBatch(request.getParameter(tableName+"_insertBatch"));//
                    schemaConf.set_delLogic(request.getParameter(tableName+"_delLogic"));//
                    schemaConf.set_recovery(request.getParameter(tableName+"_recovery"));//
                    schemaConf.set_del(request.getParameter(tableName+"_del"));//
                    schemaConf.set_update(request.getParameter(tableName+"_update"));//
                    schemaConf.set_page(request.getParameter(tableName+"_page"));//
                    schemaConf.set_list(request.getParameter(tableName+"_list"));//
                    schemaConf.set_detail(request.getParameter(tableName+"_detail"));//

                    schemaConf.set_del_flag(request.getParameter(tableName+"_del_flag"));
                    schemaConf.set_date_created(request.getParameter(tableName+"_date_created"));
                    schemaConf.set_date_updated(request.getParameter(tableName+"_date_updated"));

                    tableSchemaInfo.setSchemaConf(schemaConf);
                    tablesConf.add(tableSchemaInfo);
                });
                dto.setTablesConf(tablesConf);
                result.message = schemaInfoService.templateRender(dto);
            } catch (Exception e) {
                result = Response.error(e.getMessage());
            }
        } else {
            result = Response.error("代码生成失败!参数不全!");
        }
        request.setAttribute("result", result);
        request.setAttribute("dbs", dto.getDbs());
        request.setAttribute("tables", dto.getTables());
        return "show";
    }
}