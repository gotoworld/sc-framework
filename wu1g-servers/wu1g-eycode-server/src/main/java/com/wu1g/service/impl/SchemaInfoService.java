package com.wu1g.service.impl;

import com.wu1g.dao.ISchemaInfoDao;
import com.wu1g.domain.SchemaConf;
import com.wu1g.domain.SchemaInfo;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.BeetlUtils;
import com.wu1g.framework.util.DateUtil;
import com.wu1g.framework.util.StrUtil;
import com.wu1g.service.ISchemaInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SchemaInfoService extends BaseService implements ISchemaInfoService {
    @Autowired
    private ISchemaInfoDao schemaInfoDao;

    public List<SchemaInfo> findDbIsList(SchemaInfo dto) throws Exception {
        List<SchemaInfo> beans = null;
        try {
            beans = (List<SchemaInfo>) schemaInfoDao.findDbIsList(dto);
        } catch (Exception e) {
            log.error("库列表获取失败!", e);
        }
        return beans;
    }

    public List<SchemaInfo> findTableIsList(SchemaInfo dto) throws Exception {
        List<SchemaInfo> beans = null;
        try {
            beans = (List<SchemaInfo>) schemaInfoDao.findTableIsList(dto);
        } catch (Exception e) {
            log.error("表列表获取失败!", e);
        }
        return beans;
    }

    public List<SchemaInfo> findColumnIsList(SchemaInfo dto) throws Exception {
        List<SchemaInfo> beans = null;
        try {
            beans = (List<SchemaInfo>) schemaInfoDao.findColumnIsList(dto);
        } catch (Exception e) {
            log.error("字段列表获取失败!", e);
        }
        return beans;
    }

    public String templateRender(SchemaInfo dto) throws Exception {
        String msg = "success";
        String srcDir = "d:/schema_make_file/src/main/java/";
        String resouseDir = "d:/schema_make_file/src/main/resources/";
        //1.遍历表配置集合
        //2.获取表字段详情
        //3.获取生成配置
        dto.getTablesConf().forEach(tableConf -> {
            try {
                Map context = new HashMap();
                HashMap dtoMap = new HashMap();
                dtoMap.put("db", tableConf.getDbName());
                dtoMap.put("table", tableConf.getTableName());
                SchemaInfo tableObj = (SchemaInfo) schemaInfoDao.findTableByMap(dtoMap);
                tableConf.setTableComment(tableObj.getTableComment());
                SchemaConf schemaConf = tableConf.getSchemaConf();

                context.put("tcfgs", dto.getTablesConf());

                context.put("year", DateUtil.getCurDateStr("yyyy"));
                context.put("date", DateUtil.getCurDateStr("yyyy-MM-dd"));

                List<SchemaInfo> columns = (List<SchemaInfo>) schemaInfoDao.findColumnIsListByMap(dtoMap);
                List<SchemaInfo> pkcolumns = (List<SchemaInfo>) schemaInfoDao.findColumnPkByMap(dtoMap);
                if(pkcolumns!=null && pkcolumns.size()>0){
                    Map<String,String> pkMap=new HashMap<>();
                    pkcolumns.forEach(pk->{
                        pkMap.put(pk.getColumnName(),"1");
                    });
                    context.put("pkMap", pkMap);
                }
                if(columns!=null && columns.size()>0){
                    Map<String,String> columnMap=new HashMap<>();
                    columns.forEach(column->{
                        columnMap.put(column.getColumnNameFormat(),column.getJavaType());
                    });
                    context.put("columnMap", columnMap);
                }
                context.put("columns", columns);
                context.put("pks", pkcolumns);
                context.put("tcfg", tableConf);//tableConf
                context.put("scfg", schemaConf);//schemaConf
                context.put("tableNameL", StrUtil.toLowerCaseFirstOne(tableConf.getTableNameFormat()));
                context.put("beanVo", columns);//schemaInfoDao.findSpringVO(dtoMap));
                context.put("xmlInsert", columns);// schemaInfoDao.findMybatisInsert(dtoMap));
                context.put("xmlSelect", columns);// schemaInfoDao.findMybatisSelect(dtoMap));
                context.put("xmlUpdate", columns);// schemaInfoDao.findMybatisUpdate(dtoMap));
                //4.生成
                String verDir = schemaConf.get_template_dir();

                //domain
                if ("1".equals(tableConf.getSchemaConf().get_domain())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/Demo.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_domain_pkg()) + "/" + tableConf.getTableNameFormat() + ".java");
                }
                //dto
                if ("1".equals(tableConf.getSchemaConf().get_dto())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/DemoDto.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_dto_pkg()) + "/" + tableConf.getTableNameFormat() + "Dto.java");
                }
                //mybatis
                if ("1".equals(tableConf.getSchemaConf().get_mybatis())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/Demo.xml.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_mybatis_pkg()) + "/" + StrUtil.toLowerCaseFirstOne(tableConf.getTableNameFormat()) + ".xml");
                }
                //dao
                if ("1".equals(tableConf.getSchemaConf().get_dao())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/IDemoDao.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_dao_pkg()) + "/" + "I" + tableConf.getTableNameFormat() + schemaConf.get_dao_suffix()+".java");
                }
                //service
                if ("1".equals(tableConf.getSchemaConf().get_biz())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/DemoService.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_biz_pkg()) + "/" + tableConf.getTableNameFormat() + "Service.java");
                }
                //api
                if ("1".equals(tableConf.getSchemaConf().get_api())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/IDemoService.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_api_pkg()) + "/" + "I" + tableConf.getTableNameFormat() + "Service.java");
                }
                //web.rest
                if ("1".equals(tableConf.getSchemaConf().get_web_rest())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/RestDemoController.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_web_pkg()) + "/" + tableConf.getTableNameFormat() + "Controller.java");
                }
                //web.ctrl
                if ("1".equals(tableConf.getSchemaConf().get_web_ctrl())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/java/WebDemoController.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_web_pkg()) + "/" + tableConf.getTableNameFormat() + "Controller.java");
                }
                //view.ng
                if ("1".equals(tableConf.getSchemaConf().get_view_ng())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/view/ng/init.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/" + tableConf.getTableName() + ".html");
                    if ("1".equals(tableConf.getSchemaConf().get_insert()) || "1".equals(tableConf.getSchemaConf().get_update())) {
                        BeetlUtils.renderToFile("/btl/" + verDir + "/view/ng/edit.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/" + tableConf.getTableName() + "_edit.html");
                    }
                    if ("1".equals(tableConf.getSchemaConf().get_page())) {
                        BeetlUtils.renderToFile("/btl/" + verDir + "/view/ng/info.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/" + tableConf.getTableName() + "_info.html");
                    }
                }
                //view.btl
                if ("1".equals(tableConf.getSchemaConf().get_view_btl())) {
                    BeetlUtils.renderToFile("/btl/" + verDir + "/view/btl/init.html.btl", context, resouseDir +"/template/"+ StrUtil.getDir(schemaConf.get_view_pkg()) + "/" + tableConf.getTableName() + ".html");
                    if ("1".equals(tableConf.getSchemaConf().get_insert()) || "1".equals(tableConf.getSchemaConf().get_update())) {
                        BeetlUtils.renderToFile("/btl/" + verDir + "/view/btl/edit.html.btl", context, resouseDir +"/template/"+ StrUtil.getDir(schemaConf.get_view_pkg()) + "/" + tableConf.getTableName() + "_edit.html");
                    }
                    if ("1".equals(tableConf.getSchemaConf().get_page())) {
                        BeetlUtils.renderToFile("/btl/" + verDir + "/view/btl/list.html.btl", context, resouseDir +"/template/"+ StrUtil.getDir(schemaConf.get_view_pkg()) + "/" + tableConf.getTableName() + "_list.html");
                    }
                    BeetlUtils.renderToFile("/btl/" + verDir + "/view/btl/menu.html.btl", context, resouseDir+"/"+ schemaConf.get_my_pkg()+".menu.html");
                }
                //auth.sql
                BeetlUtils.renderToFile("/btl/" + verDir + "/java/auth.sql.btl", context, resouseDir+"/"+ schemaConf.get_my_pkg()+"_auth.sql");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return msg;
    }
}

