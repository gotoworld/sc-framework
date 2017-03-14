package com.wu1g.dao;

import com.wu1g.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ISchemaInfoDao{

    public List<?> findDbIsList(IEntity dto) throws Exception;
    public List<?> findTableIsList(IEntity dto) throws Exception;
    public List<?> findColumnIsList(IEntity dto) throws Exception;

    public List<?> findColumnIsList2(HashMap map) throws Exception;
    public List<?> findColumnPks(HashMap map) throws Exception;
    public List<HashMap> findSpringVO(HashMap map) throws Exception;
    public List<HashMap> findMybatisInsert(HashMap map) throws Exception;
    public List<HashMap> findMybatisSelect(HashMap map) throws Exception;
    public List<HashMap> findMybatisUpdate(HashMap map) throws Exception;
}