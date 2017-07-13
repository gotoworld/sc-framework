package com.hsd.dao.sys;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
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