package com.hsd.account.finance.dao;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>账户模板 数据库处理接口类。
 */
@Mapper
public interface IAccountTemplateDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account_template where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update account_template set   del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;

    /**
     * 恢复逻辑删除的数据
     */
    @Update("update account_template set    del_flag=0 where  id = #{id} ")
    int recoveryDataById(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from account_template where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
    /**
     * 根据模板id 物理删除
     */
    @Delete("delete from account_template_attribute where  template_id = #{templateId} ")
    int deleteByTemplateId(IEntity entity) throws Exception;
}