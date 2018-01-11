package com.hsd.account.finance.dao;

import com.hsd.account.finance.entity.Account;
import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
     * 根据查询账户信息
     * @param Account
     * @return
     * @throws Exception
     */
    List<Account> selectAccount(Account Account) throws Exception;

    /** 冲正/抵扣 */
    @Update("update account set state=1 where id = #{id}")
    int reverse(IEntity entity) throws Exception;
    /** 冻结 */
    @Update("update account set available_money=total_money-freeze_money-#{freezeMoney},freeze_money=freeze_money+#{freezeMoney} where id = #{id} and available_money>=#{freezeMoney}")
    int freeze(IEntity entity) throws Exception;
    /** 解冻 */
    @Update("update account set available_money=total_money-freeze_money+#{freezeMoney},freeze_money=freeze_money-#{freezeMoney} where id = #{id} and freeze_money>=#{freezeMoney}")
    int unfreeze(IEntity entity) throws Exception;
}