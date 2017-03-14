package com.wu1g.dao;

import com.wu1g.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISchemaInfoDao{

    public List<?> findDbIsList(IEntity dto) throws Exception;
    public List<?> findTableIsList(IEntity dto) throws Exception;
    public List<?> findColumnIsList(IEntity dto) throws Exception;

}