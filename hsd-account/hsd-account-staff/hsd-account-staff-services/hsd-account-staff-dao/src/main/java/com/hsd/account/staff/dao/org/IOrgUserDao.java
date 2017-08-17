package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>组织架构_用户  数据库处理接口类。
 */
@Mapper
public interface IOrgUserDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_user where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update org_user set version=version+1, date_updated=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_user where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
    /**
     * 恢复逻辑删除的数据
     */
    @Update("update org_user set version=version+1 ,del_flag=0 where  id = #{id} ")
    int recoveryDataById(IEntity dto) throws Exception;

    /**
     * <p>获取用户信息>根据用户登录名。
     */
    Object findUserByAccount(Map dto) throws Exception;

    /**
     * 获取某一种角色所有用户
     */
    List<?> getUserList(IEntity dto) throws Exception;

    /**
     * <p>判断用户账号是否存在
     */
    @Select(" select count(0) from org_user where  account=#{account} ")
    int isAccountYN(@Param("account") String account) throws Exception;

    /**
     * <p>密码修改
     */
    @Update("update org_user set version=version+1,date_updated=now(),pwd=#{confirmpwd} where id = #{id} and pwd=#{oldpwd}")
    int updatePwd(IEntity entity) throws Exception;
    /**
     * <p>密码重置
     */
    @Update("update org_user set version=version+1,date_updated=now(),pwd=#{pwd} where id = #{id}")
    int resetPwd(IEntity entity) throws Exception;
    /**
     * <p>最后登陆记录
     */
    @Update("update org_user set count=count+1,last_login=now() where id = #{id}")
    int lastLogin(IEntity entity) throws Exception;

    List<?> findUserIsPage(IEntity dto) throws Exception;
}