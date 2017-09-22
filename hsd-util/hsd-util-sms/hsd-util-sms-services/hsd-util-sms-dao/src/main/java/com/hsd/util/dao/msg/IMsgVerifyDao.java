package com.hsd.util.dao.msg;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>信息验证 数据库处理接口类。
 */
@Mapper
public interface IMsgVerifyDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from msg_verify where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;
    /**
     * 根据主键 物理删除
     */
    @Delete("delete from msg_verify where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 判断是否存在未使用的短讯
     */
    @Select("select IFNULL(count(0),0) as count from msg_verify where  state=0 and date_created<=date_add(date_created, interval 1 minute) ")
    int isNotUsedYN(IEntity entity) throws Exception;
}