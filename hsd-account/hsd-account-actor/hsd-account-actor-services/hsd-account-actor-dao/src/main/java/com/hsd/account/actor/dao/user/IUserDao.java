package com.hsd.account.actor.dao.user;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.*;

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
     * 根据主键 物理删除
     */
    @Delete("delete from user where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 设置标签
     */
    @Update("update user set tags=#{tags} where id = #{id} ")
    int setTags(IEntity entity) throws Exception;

}