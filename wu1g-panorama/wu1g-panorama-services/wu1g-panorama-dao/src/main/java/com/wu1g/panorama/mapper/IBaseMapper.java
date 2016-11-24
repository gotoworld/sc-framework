package com.wu1g.panorama.mapper;

import com.wu1g.panorama.modal.IEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBaseMapper{
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
    int insertBatch(List<IEntity> dtos) throws Exception;
    /**
     * 详情
     */
    Object selectByPrimaryKey(IEntity dto) throws Exception;
    /**
     * 更新
     */
    int updateByPrimaryKeySelective(IEntity dto) throws Exception;
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
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>分页。
     * </ol>
     * @return 处理结果
     */
    public List<?> findDataIsPage(IEntity dto) throws Exception;
    /**
     * <p>信息列表。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     * </ol>
     * @return 处理结果
     */
    public List<?> findDataIsList(IEntity dto) throws Exception;

}