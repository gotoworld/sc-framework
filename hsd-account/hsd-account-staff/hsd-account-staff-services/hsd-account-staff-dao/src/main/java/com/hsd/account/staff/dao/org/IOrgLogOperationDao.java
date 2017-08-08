package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>组织架构_员工操作日志 数据库处理接口类。
 */
@Mapper
public interface IOrgLogOperationDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_log_operation where  id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;



    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_log_operation where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    /**
     * 根据过期天数 物理删除
     */
    @Delete("delete from sys_user_log where  IFNULL(datediff(DATE_FORMAT(now(),'%Y%m%d'),DATE_FORMAT(date_created,'%Y%m%d')),999)>#{expireDay} ")
    int deleteByExpireDay(Integer expireDay) throws Exception;
}