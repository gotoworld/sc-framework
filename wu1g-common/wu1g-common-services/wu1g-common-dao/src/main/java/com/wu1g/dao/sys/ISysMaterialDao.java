package com.wu1g.dao.sys;

import com.wu1g.framework.IBaseDao;
import com.wu1g.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>全景_素材 数据库处理接口类。
 */
@Mapper
public interface ISysMaterialDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_material where  id = #{id} ")
    int isDataYN(IEntity dto) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update sys_material set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity dto) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from sys_material where  id = #{id} ")
    int deleteByPrimaryKey(IEntity dto) throws Exception;
}