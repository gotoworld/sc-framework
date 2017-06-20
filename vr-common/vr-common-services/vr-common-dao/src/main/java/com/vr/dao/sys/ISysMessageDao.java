package com.vr.dao.sys;

import com.vr.framework.IBaseDao;
import com.vr.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>系统_站内信 数据库处理接口类。
 */
@Mapper
public interface ISysMessageDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_message where  id = #{id} ")
    int isDataYN(IEntity dto) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update sys_message set  date_updated=now(), =1 where  id = #{id} ")
    int deleteById(IEntity dto) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from sys_message where  id = #{id} ")
    int deleteByPrimaryKey(IEntity dto) throws Exception;
}