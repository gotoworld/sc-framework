package com.hsd.account.finance.dao;

import com.hsd.account.finance.entity.AccountSubGold;
import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>子账户-实物贵金属 数据库处理接口类。
 */
@Mapper
public interface IAccountSubGoldDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account_sub_gold where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 根据用户Id查询账户信息
     * @param accountSubGold
     * @return
     * @throws Exception
     */
    AccountSubGold selectByUserId(AccountSubGold accountSubGold) throws Exception;

}