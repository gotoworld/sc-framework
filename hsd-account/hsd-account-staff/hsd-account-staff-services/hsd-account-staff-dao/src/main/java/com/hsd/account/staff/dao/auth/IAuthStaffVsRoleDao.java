package com.hsd.account.staff.dao.auth;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>权限_员工vs角色 数据库处理接口类。
 */
@Mapper
public interface IAuthStaffVsRoleDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from auth_staff_vs_role where  staff_id = #{staffId} and  role_id = #{roleId} ")
    int isDataYN(IEntity entity) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete from auth_staff_vs_role where  staff_id = #{staffId} and  role_id = #{roleId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

     /**
      * 根据主键staff_id 物理删除
      */
     @Delete("delete from auth_staff_vs_role where  staff_id = #{staffId}")
     int deleteBulkDataByStaffId(IEntity entity) throws Exception;
     /**
      * 根据主键role_id 物理删除
      */
     @Delete("delete from auth_staff_vs_role where  role_id = #{roleId}")
     int deleteBulkDataByRoleId(IEntity entity) throws Exception;

     List<?> findStaffRoleIsList(IEntity entity) throws Exception;
     List<?> findOrgRoleIsList(IEntity entity) throws Exception;
}