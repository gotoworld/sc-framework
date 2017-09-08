package com.hsd.account.actor.dao.actor;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>会员信息表 数据库处理接口类。
 */
@Mapper
public interface IMemberDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from member where  user_id = #{userId} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update member set  date_updated=now(), del_flag=1 where  user_id = #{userId} ")
    int deleteById(IEntity entity) throws Exception;

    /**
     * 恢复逻辑删除的数据
     */
    @Update("update member set   date_updated=now(), del_flag=0 where  user_id = #{userId} ")
    int recoveryDataById(IEntity entity) throws Exception;
    /**
     * 根据主键 物理删除
     */
    @Delete("delete from member where  user_id = #{userId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 更新客户身份证号
     */
    @Update("update member set date_updated=now(),credential_number = #{credentialNumber} where user_id = #{userId} ")
    int updateCredentialNumber(IEntity entity) throws Exception;
}