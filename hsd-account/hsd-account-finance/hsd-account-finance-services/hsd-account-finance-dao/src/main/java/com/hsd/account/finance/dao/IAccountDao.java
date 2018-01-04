package com.hsd.account.finance.dao;

import com.hsd.account.finance.entity.Account;
import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>支付账户 数据库处理接口类。
 */
@Mapper
public interface IAccountDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 根据用户Id查询账户信息
     * @param Account
     * @return
     * @throws Exception
     */
    Account selectByUserId(Account Account) throws Exception;


}