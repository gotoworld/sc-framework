package com.hsd.staff.dao.auth;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>权限_用户vs角色 数据库处理接口类。
 */
@Mapper
public interface IAuthUserVsRoleDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from auth_user_vs_role where  user_id = #{userId} and  role_id = #{roleId} ")
    int isDataYN(IEntity entity) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete from auth_user_vs_role where  user_id = #{userId} and  role_id = #{roleId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

     /**
      * 根据主键user_id 物理删除
      */
     @Delete("delete from auth_user_vs_role where  user_id = #{userId}")
     int deleteBulkDataByUserId(IEntity entity) throws Exception;
     /**
      * 根据主键role_id 物理删除
      */
     @Delete("delete from auth_user_vs_role where  role_id = #{roleId}")
     int deleteBulkDataByRoleId(IEntity entity) throws Exception;
}