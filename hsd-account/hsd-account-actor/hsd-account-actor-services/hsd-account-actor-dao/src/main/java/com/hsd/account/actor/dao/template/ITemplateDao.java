package com.hsd.account.actor.dao.template;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>档案模板 数据库处理接口类。
 */
@Mapper
public interface ITemplateDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from template where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update template set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from template where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

}