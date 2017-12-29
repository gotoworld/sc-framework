package com.hsd.account.staff.dao.sys;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>APP应用表 数据库处理接口类。
 */
@Mapper
public interface ISysAppDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from sys_app where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    @Select("select IFNULL(count(0),0) as count from sys_app where  id = #{id} and nodel_flag=1 ")
    int isNoDelFlag(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from sys_app where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update sys_app set date_updated=now(), del_flag=1 where id = #{id} ")
    int deleteById(IEntity entity) throws Exception;
    /**
     * 恢复逻辑删除的数据
     */
    @Update("update sys_app set date_updated=now(), del_flag=0 where id = #{id} ")
    int recoveryDataById(IEntity dto) throws Exception;

    Object findAppByName(String appname) throws Exception;

    /**
     * 角色应用信息列表>根据员工id。
     */
    List<?> getAppListByStaffId(Map dto) throws Exception;
}