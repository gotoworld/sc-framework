package com.hsd.account.dao.sys;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>系统_域 数据库处理接口类。
 */
@Mapper
public interface ISysDomainDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_domain where  code = #{code} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update sys_domain set version=version+1, bi_update_ts=now(), del_flag=1 where  code = #{code} ")
    int deleteById(IEntity entity) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from sys_domain where  code = #{code} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
}