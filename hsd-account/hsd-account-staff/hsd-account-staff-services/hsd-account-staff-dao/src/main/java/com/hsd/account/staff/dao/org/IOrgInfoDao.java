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
     * 获取用户所在组织集合>根据用户id
     */
    List<?> getOrgListByUserId(Map dto) throws Exception;
}