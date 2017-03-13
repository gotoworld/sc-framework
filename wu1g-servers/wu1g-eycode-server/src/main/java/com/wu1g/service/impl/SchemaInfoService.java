package com.wu1g.service.impl;

import com.wu1g.dao.ISchemaInfoDao;
import com.wu1g.domain.SchemaInfo;
import com.wu1g.framework.service.BaseService;
import com.wu1g.service.ISchemaInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String templateRender(SchemaInfo dto) throws Exception{
        String msg="success";

        return msg;
    }
}

