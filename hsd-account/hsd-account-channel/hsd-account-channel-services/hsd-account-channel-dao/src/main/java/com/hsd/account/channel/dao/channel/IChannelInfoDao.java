package com.hsd.account.channel.dao.channel;

import com.hsd.account.channel.entity.channel.ChannelInfo;
import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>渠道商信息 数据库处理接口类。
 */
@Mapper
public interface IChannelInfoDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from channel_info where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 逻辑删除
     */
    @Update("update channel_info set  bi_update_ts=now(), del_flag=1 where  id = #{id} ")
    int deleteById(IEntity entity) throws Exception;


    /**
     * 根据主键 物理删除
     */
    @Delete("delete from channel_info where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 信息恢复
     */
    @Update("update channel_info set  bi_update_ts=now(), staus=1 where  id = #{id} ")
    int recoveryData(IEntity entity) throws Exception;

    /**
     * 重置密码
     */
    @Update("update channel_info set  pwd=#{pwd}, bi_update_ts=now() where  id = #{id} ")
	int resetPwd(IEntity entity) throws Exception;

	/**
	 * @param dto
	 * 根据账号获取渠道商信息
	 */
	Object findUserByAccount(Map dto) throws Exception;

	/**
     * <p>密码修改
     */
    @Update("update channel_info set pwd=#{pwd} where id = #{id}")
	int updateLoginPwd(ChannelInfo entity);
}