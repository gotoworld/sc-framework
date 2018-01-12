package com.hsd.account.finance.dao;

import com.hsd.account.finance.entity.Account;
import com.hsd.account.finance.entity.AccountSubLoan;
import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>子账户-P2P网贷 数据库处理接口类。
 */
@Mapper
public interface IAccountSubLoanDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account_sub_loan where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 根据用户Id查询账户信息
     * @param accountSubLoan
     * @return
     * @throws Exception
     */
    AccountSubLoan selectByUserId(AccountSubLoan accountSubLoan) throws Exception;

    /** 冲正/抵扣 */
    @Update("update account_sub_loan set state=1 where id = #{id}")
    int reverse(IEntity entity) throws Exception;
    /** 冻结 */
    @Update("update account_sub_loan set available_money=total_money-freeze_money-#{freezeMoney},freeze_money=freeze_money+#{freezeMoney} where id = #{id} and available_money>=#{freezeMoney}")
    int freeze(IEntity entity) throws Exception;
    /** 解冻 */
    @Update("update account_sub_loan set available_money=total_money-freeze_money+#{freezeMoney},freeze_money=freeze_money-#{freezeMoney} where id = #{id} and freeze_money>=#{freezeMoney}")
    int unfreeze(IEntity entity) throws Exception;
    /** 充值*/
    @Update("update account_sub_loan set available_money = available_money + #{availableMoney},total_money = total_money + #{totalMoney} where id = #{id} and state = 0")
    int recharge(IEntity entity) throws Exception;
    /** 提现 */
    @Update("update account_sub_loan set available_money = available_money - #{availableMoney} ,total_money = total_money - #{totalMoney} where id = #{id} and state = 0 and available_money >= #{availableMoney}")
    int withdrawal(IEntity entity) throws Exception;
}