package com.hsd.account.staff.dao.auth;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>角色vs应用 数据库处理接口类。
 */
@Mapper
public interface IAuthRoleVsAppDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from auth_role_vs_app where  role_id = #{roleId} and  app_id = #{appId} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from auth_role_vs_app where  role_id = #{roleId} and  app_id = #{appId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

     /**
      * 根据主键role_id 物理删除
      */
     @Delete("delete from auth_role_vs_app where  role_id = #{roleId}")
     int deleteBulkDataByRoleId(IEntity entity) throws Exception;

     /**
      * 根据主键app_id 物理删除
      */
     @Delete("delete from auth_role_vs_app where  app_id = #{appId}")
     int deleteBulkDataByAppId(IEntity entity) throws Exception;

     /** 根据角色获取应用列表 */
     List<?> findAppIsList(IEntity entity) throws Exception;
}