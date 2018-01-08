package com.hsd.account.actor.dao.user;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>APP用户表 数据库处理接口类。
 */
@Mapper
public interface IUserAppDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from user_app where  user_id = #{userId} AND  app_id =#{appId}")
    int isDataYN(IEntity entity) throws Exception;

    Object findDataByAppIdAndUserId(IEntity entity) throws Exception;
}