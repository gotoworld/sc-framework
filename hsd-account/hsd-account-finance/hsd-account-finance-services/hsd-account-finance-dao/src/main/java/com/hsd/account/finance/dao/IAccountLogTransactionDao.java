package com.hsd.account.finance.dao;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>账户-日志-交易记录 数据库处理接口类。
 */
@Mapper
public interface IAccountLogTransactionDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account_log_transaction where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;



}