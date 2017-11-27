package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>组织架构_员工  数据库处理接口类。
 */
@Mapper
public interface IOrgStaffDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_staff where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update org_staff set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_staff where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
    /**
     * 恢复逻辑删除的数据
     */
    @Update("update org_staff set version=version+1 ,del_flag=0 where  id = #{id} ")
    int recoveryDataById(IEntity dto) throws Exception;

    /**
     * <p>获取员工信息>根据员工登录名。
     */
    Object findStaffByAccount(Map dto) throws Exception;
    /**
     * <p>获取员工信息>根据员工登录名。
     */
    Object findDataByAccount(IEntity entity) throws Exception;
    Object selectUserPwdByPrimaryKey(IEntity entity) throws Exception;
    /**
     * <p>判断员工账号是否存在
     */
    @Select(" select count(0) from org_staff where  account=#{account} ")
    int isAccountYN(@Param("account") String account) throws Exception;

    /**
     * <p>密码修改
     */
    @Update("update org_staff set version=version+1,date_updated=now(),pwd=#{confirmpwd} where id = #{id} and pwd=#{oldpwd}")
    int updatePwd(IEntity entity) throws Exception;

    /**
     * <p>密码重置
     */
    @Update("update org_staff set version=version+1,date_updated=now(),pwd=#{pwd} where id = #{id}")
    int resetPwd(IEntity entity) throws Exception;
    /**
     * <p>最后登陆记录
     */
    @Update("update org_staff set count=count+1,last_login=now() where id = #{id}")
    int lastLogin(IEntity entity) throws Exception;
    List<?> findBriefDataIsPage(IEntity dto) throws Exception;
    /**
     * <p>设置上级领导
     */
    @Update("update org_staff set leadership=#{leadership} where id = #{id}")
    int setLeadership(IEntity entity) throws Exception;

    /**
     * <p>获取员工-根据员工和上级级别。
     */
    Object getStaffByStaffIdAndleadershipLevel(IEntity entity) throws Exception;
    /**
     * <p>获取员工-所有上级。
     */
    List getStaffLeadershipAll(IEntity entity) throws Exception;
    /**
     * <p>获取用户及用户所在组织。
     */
    List findStaffAndOrgDataIsList(IEntity entity) throws Exception;
}