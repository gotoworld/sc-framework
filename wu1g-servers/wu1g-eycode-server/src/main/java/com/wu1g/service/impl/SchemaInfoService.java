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
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/Demo.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_domain_pkg()) + "/" + tableConf.getTableNameFormat() + ".java");
                }
                //dto
                if ("1".equals(tableConf.getSchemaConf().get_dto())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/DemoDto.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_dto_pkg()) + "/" + tableConf.getTableNameFormat() + "Dto.java");
                }
                //mybatis
                if ("1".equals(tableConf.getSchemaConf().get_mybatis())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/Demo.xml.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_mybatis_pkg()) + "/" + StrUtil.toLowerCaseFirstOne(tableConf.getTableNameFormat()) + ".xml");
                }
                //dao
                if ("1".equals(tableConf.getSchemaConf().get_dao())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/IDemoDao.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_dao_pkg()) + "/" + "I" + tableConf.getTableNameFormat() + "Mapper.java");
                }
                //service
                if ("1".equals(tableConf.getSchemaConf().get_biz())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/DemoService.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_biz_pkg()) + "/" + tableConf.getTableNameFormat() + "Service.java");
                }
                //api
                if ("1".equals(tableConf.getSchemaConf().get_api())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/IDemoService.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_api_pkg()) + "/" + "I" + tableConf.getTableNameFormat() + "Service.java");
                }
                //web.rest
                if ("1".equals(tableConf.getSchemaConf().get_web_rest())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/RestDemoController.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_web_pkg()) + "/" + tableConf.getTableNameFormat() + "Controller.java");
                }
                //web.ctrl
                if ("1".equals(tableConf.getSchemaConf().get_web_ctrl())) {
                    BeetlUtils.renderToFile("/btl/java/" + verDir + "/WebDemoController.java.btl", context, srcDir + StrUtil.getDir(schemaConf.get_web_pkg()) + "/" + tableConf.getTableNameFormat() + "Controller.java");
                }
                //view.ng
                if ("1".equals(tableConf.getSchemaConf().get_view_ng())) {
                    BeetlUtils.renderToFile("/btl/view/" + verDir + "/ng/init.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/ng/" + tableConf.getTableName() + ".html");
                    if ("1".equals(tableConf.getSchemaConf().get_insert()) || "1".equals(tableConf.getSchemaConf().get_update())) {
                        BeetlUtils.renderToFile("/btl/view/" + verDir + "/ng/edit.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/ng/" + tableConf.getTableName() + "_edit.html");
                    }
                    if ("1".equals(tableConf.getSchemaConf().get_page())) {
                        BeetlUtils.renderToFile("/btl/view/" + verDir + "/ng/info.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/ng/" + tableConf.getTableName() + "_info.html");
                    }
                }
                //view.btl
                if ("1".equals(tableConf.getSchemaConf().get_view_btl())) {
                    BeetlUtils.renderToFile("/btl/view/" + verDir + "/btl/init.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/btl/" + tableConf.getTableName() + ".html");
                    if ("1".equals(tableConf.getSchemaConf().get_insert()) || "1".equals(tableConf.getSchemaConf().get_update())) {
                        BeetlUtils.renderToFile("/btl/view/" + verDir + "/btl/edit.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/btl/" + tableConf.getTableName() + "_edit.html");
                    }
                    if ("1".equals(tableConf.getSchemaConf().get_page())) {
                        BeetlUtils.renderToFile("/btl/view/" + verDir + "/btl/list.html.btl", context, resouseDir + StrUtil.getDir(schemaConf.get_view_pkg()) + "/btl/" + tableConf.getTableName() + "_list.html");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return msg;
    }
}

