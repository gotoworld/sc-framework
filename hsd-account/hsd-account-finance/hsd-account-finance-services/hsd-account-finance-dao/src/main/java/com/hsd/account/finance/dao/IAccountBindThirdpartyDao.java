package com.hsd.account.finance.dao;

import com.hsd.account.finance.entity.AccountBindThirdparty;
import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>支付账户与三方账户绑定 数据库处理接口类。
 */
@Mapper
public interface IAccountBindThirdpartyDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from account_bind_thirdparty where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 根据用id获取绑卡信息
     * @param entity
     * @return
     */
    List<AccountBindThirdparty> selectBindThirdparty(AccountBindThirdparty entity);

    int updateByAccountAndCard(AccountBindThirdparty entity);

}