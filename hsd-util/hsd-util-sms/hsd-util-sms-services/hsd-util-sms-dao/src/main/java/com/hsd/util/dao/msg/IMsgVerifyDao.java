package com.hsd.util.dao.msg;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 判断是否存在未使用的短讯(N分钟内)
     */
    @Select("select IFNULL(count(0),0) as count from msg_verify where sms_type=0 and is_used=0 and sms_address=#{smsAddress} and date_created<=date_add(date_created, interval 1 minute) ")
    int isNotUsedYN(IEntity entity) throws Exception;

    /**
     * 判断相同Ip当日内是否超过短信数量
     */
    @Select("select IFNULL(count(0),0) as count from msg_verify where sms_type=0 and is_used=0 and ip_address=#{ipAddress} and DATE_FORMAT(date_created, '%Y%m%d') = DATE_FORMAT(now(), '%Y%m%d') ")
    int isIpExceedYN(IEntity entity) throws Exception;

    /**
     * 去除过期验证信息
     */
    @Update("delete from msg_verify where is_used=1 or now()>=DATE_ADD(date_created,INTERVAL data_expire second) ")
    int clearVerifyExpire(IEntity entity) throws Exception;
    /**
     * 使用短信验证码
     */
    @Update("update msg_verify set is_used=1,date_using=now() where sms_type=#{smsType} and is_used=0 and now()>=DATE_ADD(date_created,INTERVAL data_expire second) and sms_address=#{smsAddress} ")
    int useVerifyCode(IEntity entity) throws Exception;

}