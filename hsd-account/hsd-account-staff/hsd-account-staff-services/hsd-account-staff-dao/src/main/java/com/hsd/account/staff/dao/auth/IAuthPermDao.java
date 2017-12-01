package com.hsd.account.staff.dao.auth;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息  数据库处理接口类。
 */
@Mapper
public interface IAuthPermDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from auth_perm where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update auth_perm set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from auth_perm where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
    /**
     * 恢复逻辑删除的数据
     */
    @Update("update auth_perm set version=version+1, date_updated=now() ,del_flag=0 where  id = #{id} ")
    int recoveryDataById(IEntity dto) throws Exception;
    /**
     * 角色权限信息列表>根据员工id。
     */
    List getPermListByStaffId(Map dto) throws Exception;

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    List<?> findPermDataIsListByRoleId(Map dto) throws Exception;

    /** 获取禁止删除标志*/
    @Select("select IFNULL(count(0),0) as count from auth_perm where nodel_flag = 1")
    int findDataByNoDelFlag (IEntity entity)throws Exception;
    /** 禁止删除已存在的信息*/
    @Update("UPDATE auth_perm set nodel_flag = 1 where del_flag = 0")
    int noDelete(IEntity entity) throws Exception;
}