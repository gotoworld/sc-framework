package com.hsd.account.finance.dao;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>用户账户-操作日志 数据库处理接口类。
 */
@Mapper
public interface IAccountLogOperationalDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account_log_operational where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;



}