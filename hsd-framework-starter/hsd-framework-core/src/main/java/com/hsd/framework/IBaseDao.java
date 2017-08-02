package com.hsd.framework;

import java.util.List;

public interface IBaseDao {
    /**
     * 根据主键 物理删除
     */
    int deleteByPrimaryKey(IEntity dto) throws Exception;
    /**
     * 新增
     */
    int insert(IEntity dto) throws Exception;
    /**
     * 新增 批量
     */
    int insertBatch(List<?> dtos) throws Exception;
    /**
     * 详情
     */
    Object selectByPrimaryKey(IEntity dto) throws Exception;
    /**
     * 更新
     */
    int update(IEntity dto) throws Exception;
    /**
     * 判断是否存在
     */
    int isDataYN(IEntity dto) throws Exception;
    /**
     * 逻辑删除
     */
    int deleteById(IEntity dto) throws Exception;
    /**
     * 恢复逻辑删除的数据
     */
    int recoveryDataById(IEntity dto) throws Exception;
    /**
     * <p>信息列表 分页。
     */
    List<?> findDataIsPage(IEntity dto) throws Exception;
    /**
     * <p>信息列表。
     */
    List<?> findDataIsList(IEntity dto) throws Exception;

}