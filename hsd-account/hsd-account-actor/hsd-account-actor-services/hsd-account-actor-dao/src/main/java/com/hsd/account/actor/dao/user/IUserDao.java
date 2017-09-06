package com.hsd.account.actor.dao.user;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * <p>客户表 数据库处理接口类。
 */
@Mapper
public interface IUserDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from user where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * <p>判断账号是否存在
     */
    @Select(" select count(0) from user where  account=#{account} ")
    int isAccountYN(@Param("account") String account) throws Exception;
    /**
     * <p>判断手机号是否存在
     */
    @Select(" select count(0) from user where  cellphone=#{cellphone} ")
    int isCellphoneYN(@Param("cellphone") String cellphone) throws Exception;
    /**
     * <p>判断邮箱是否存在
     */
    @Select(" select count(0) from user where  email=#{email} ")
    int isEmailYN(@Param("email") String email) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from user where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 设置标签
     */
    @Update("update user set tags=#{tags} where id = #{id} ")
    int setTags(IEntity entity) throws Exception;
    /**
     * 设置黑名单
     */
    @Update("update user set state=3 where id = #{id} and state=1 ")
    int setBlacklist(IEntity entity) throws Exception;
    /**
     * 移除黑名单
     */
    @Update("update user set state=1 where id = #{id} and state=3 ")
    int delBlacklist(IEntity entity) throws Exception;

    /**
     * <p>获取用户信息>根据登录名。
     */
    Object findUserByAccount(Map dto) throws Exception;
    /**
     * <p>最后登陆记录
     */
    @Update("update user set count=count+1,last_login=now() where id = #{id}")
    int lastLogin(IEntity entity) throws Exception;
    /**
     * <p>获取用户[id,手机号,邮箱]>根据登录名。找回密码
     */
    Object getAccount(IEntity dto) throws Exception;
    /**
     * <p>密码修改
     */
    @Update("update user set count=count+1,pwd=#{pwd} where id = #{id}")
    int updatePwd(IEntity entity) throws Exception;
}