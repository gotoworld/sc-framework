package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>权限_组织vs角色 数据库处理接口类。
 */
@Mapper
public interface IOrgOrgVsRoleDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_org_vs_role where  org_id = #{orgId} and  role_id = #{roleId} ")
    int isDataYN(IEntity entity) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_org_vs_role where  org_id = #{orgId} and  role_id = #{roleId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

     /**
      * 根据主键org_id 物理删除
      */
     @Delete("delete from org_org_vs_role where  org_id = #{orgId}")
     int deleteBulkDataByOrgId(IEntity entity) throws Exception;
     /**
      * 根据主键role_id 物理删除
      */
     @Delete("delete from org_org_vs_role where  role_id = #{roleId}")
     int deleteBulkDataByRoleId(IEntity entity) throws Exception;

     List<?> findRoleIsList(IEntity entity) throws Exception;
}