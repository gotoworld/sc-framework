package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>组织架构  数据库处理接口类。
 */
@Mapper
public interface IOrgInfoDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_info where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;
    /**
     * 逻辑删除
     */
    @Update("update org_info set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_info where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 恢复逻辑删除的数据
     */
    @Update("update org_info set version=version+1 , date_updated=now(),del_flag=0 where  id = #{id} ")
    int recoveryDataById(IEntity dto) throws Exception;

    /**
     * 获取员工所在组织集合>根据员工id
     */
    List<?> getOrgListByStaffId(Map dto) throws Exception;
    List<?> findBriefDataIsPage(IEntity dto) throws Exception;

    /**
     * 设置部门负责人
     */
    @Update("update org_info set version=version+1 ,manager=#{manager}, date_updated=now() where  id = #{id} ")
    int setManager(IEntity dto) throws Exception;

    /** 根据code获取组织id */
    @Select("select id from org_info where  code = #{code} ")
    Long getIdByPCode(IEntity entity) throws Exception;
    /** 根据name获取组织id */
    @Select("select id from org_info where  name = #{name} ")
    Long getIdByName(IEntity entity) throws Exception;

    /** 根据父id获取组织id */
    @Select("select id from org_info where  parent_id = #{id} ")
    Long getIdByPId(IEntity entity) throws Exception;

    Object getDataByPCode(IEntity entity) throws Exception;
    Object selectByCode(IEntity entity) throws Exception;
    Object getManager(IEntity entity) throws Exception;
}