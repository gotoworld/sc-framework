package com.hsd.account.dao.auth;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_角色信息  数据库处理接口类。
 */
@Mapper
public interface IAuthRoleDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from auth_role where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update auth_role set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from auth_role where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 角色信息列表>根据用户id。
     */
    List<?> getRoleListByUId(Map dto) throws Exception;

    /**
     * 根据用户id,判断用户是否为超级管理员,要的就是特权.
     */
    int isSuperAdmin(Map dto) throws Exception;
}