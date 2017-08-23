package com.hsd.account.actor.dao.identity;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>用户实名认证日志 数据库处理接口类。
 */
@Mapper
public interface IIdentityLogDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from identity_log where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete from identity_log where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

}