package com.vr.dao.sys;

import com.vr.framework.IBaseDao;
import com.vr.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p> 系统_管理员操作日志 数据库处理接口类。
 */
@Mapper
public interface ISysUserLogDao extends IBaseDao {

    /**
     * 根据过期天数 物理删除
     */
    @Delete("delete from sys_user_log where  IFNULL(datediff(DATE_FORMAT(now(),'%Y%m%d'),DATE_FORMAT(date_created,'%Y%m%d')),999)>#{expireDay} ")
    int deleteByExpireDay(Integer expireDay) throws Exception;
}