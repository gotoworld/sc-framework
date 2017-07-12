package com.hsd.service;

import com.hsd.domain.SchemaInfo;

import java.util.List;

public interface ISchemaInfoService {

    public List<SchemaInfo> findDbIsList(SchemaInfo dto) throws Exception;
    public List<SchemaInfo> findTableIsList(SchemaInfo dto) throws Exception;
    public List<SchemaInfo> findColumnIsList(SchemaInfo dto) throws Exception;
    public String templateRender(SchemaInfo dto) throws Exception;

}