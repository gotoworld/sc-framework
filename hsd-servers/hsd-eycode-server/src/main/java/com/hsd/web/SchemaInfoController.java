package com.hsd.web;

import com.hsd.domain.SchemaConf;
import com.hsd.domain.SchemaInfo;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.service.ISchemaInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Response result = new Response(0, "success");
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
        Response result = new Response(0, "success");
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
        Response result = new Response(0, "success");
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
        request.setAttribute("dbs", dto.getDbs());
        request.setAttribute("tables", dto.getTables());
        return "column";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "templateRender")
    @RfAccount2Bean
    public String templateRender(SchemaInfo dto) {
        log.info("SchemaInfoController templateRender.........");
        Response result = new Response(0, "success");
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
                    schemaConf.set_dto(request.getParameter(tableName+"_dto"));//
                    schemaConf.set_mybatis(request.getParameter(tableName+"_mybatis"));//
                    schemaConf.set_dao(request.getParameter(tableName+"_dao"));//
                    schemaConf.set_dao_suffix(request.getParameter("_dao_suffix"));//
                    schemaConf.set_biz(request.getParameter(tableName+"_biz"));//
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
                    schemaConf.set_tree(request.getParameter(tableName+"_tree"));//
                    schemaConf.set_detail(request.getParameter(tableName+"_detail"));//

                    schemaConf.set_my_project(request.getParameter("_my_project"));//
                    schemaConf.set_my_pkg(request.getParameter("_my_pkg"));//
                    schemaConf.set_domain_pkg(request.getParameter(tableName+"_domain_pkg"));//
                    schemaConf.set_dto_pkg(request.getParameter(tableName+"_dto_pkg"));//
                    schemaConf.set_mybatis_pkg(request.getParameter(tableName+"_mybatis_pkg"));//
                    schemaConf.set_dao_pkg(request.getParameter(tableName+"_dao_pkg"));//
                    schemaConf.set_api_pkg(request.getParameter(tableName+"_api_pkg"));//
                    schemaConf.set_biz_pkg(request.getParameter(tableName+"_biz_pkg"));//
                    schemaConf.set_web_pkg(request.getParameter(tableName+"_web_pkg"));//
                    schemaConf.set_web_http(request.getParameter(tableName+"_web_http"));//
                    schemaConf.set_view_pkg(request.getParameter(tableName+"_view_pkg"));//

                    Map<String,String> _col_show=new HashMap<>();
                    String[] _col_show_arr=request.getParameterValues("tsuffix_"+tableName+"_col_show");
                    if(_col_show_arr!=null && _col_show_arr.length>0){
                        for (String s : _col_show_arr) {
                            _col_show.put(s,request.getParameter("tsuffix_"+tableName+"_"+s+"_comment"));
                        }
                    }
                    schemaConf.set_col_show(_col_show);

                    Map<String,String> _col_edit=new HashMap<>();
                    Map<String,String> _edit_type=new HashMap<>();
                    String[] _col_edit_arr=request.getParameterValues("tsuffix_"+tableName+"_col_edit");
                    if(_col_edit_arr!=null && _col_edit_arr.length>0){
                        for (String s : _col_edit_arr) {
                            _col_edit.put(s,request.getParameter("tsuffix_"+tableName+"_"+s+"_comment"));
                            _edit_type.put(s,request.getParameter("tsuffix_"+tableName+"_"+s+"_edit_type"));
                        }
                    }
                    schemaConf.set_col_edit(_col_edit);
                    schemaConf.set_edit_type(_edit_type);

                    schemaConf.set_col_created(request.getParameter("tsuffix_"+tableName+"_col_created"));
                    schemaConf.set_col_updated(request.getParameter("tsuffix_"+tableName+"_col_updated"));
                    schemaConf.set_col_del(request.getParameter("tsuffix_"+tableName+"_col_del"));
                    schemaConf.set_col_autopk(request.getParameter("tsuffix_"+tableName+"_col_autopk"));
                    schemaConf.set_col_version(request.getParameter("tsuffix_"+tableName+"_col_version"));
                    schemaConf.set_template_dir(request.getParameter("_template_dir"));

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