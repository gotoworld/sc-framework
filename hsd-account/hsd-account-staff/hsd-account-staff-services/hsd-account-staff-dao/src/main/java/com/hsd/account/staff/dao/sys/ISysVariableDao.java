package com.hsd.account.staff.dao.sys;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>系统_数据字典表 数据库处理接口类。
 */
@Mapper
public interface ISysVariableDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_variable where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;
    /**
     * 逻辑删除
     */
    @Update("update sys_variable set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from sys_variable where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    List findChildDataIsList(IEntity entity) throws Exception;

    @Select("select t1.code from sys_variable t1 INNER JOIN sys_variable t2 on (t2.`code`='stafflevel' and t1.parent_id=t2.id) where t1.del_flag=0 and t1.`name`=#{name}")
    String getCodeByVariableName(IEntity entity) throws Exception;
}