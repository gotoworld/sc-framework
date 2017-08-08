package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 * 组织架构_用户vs部门 数据库处理接口类。
 */
@Mapper
public interface IOrgOrgVsUserDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_org_vs_user where  user_id = #{userId} and  org_id = #{orgId} ")
    int isDataYN(IEntity entity) throws Exception;
    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_org_vs_user where  user_id = #{userId} and  org_id = #{orgId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
    /**
     * 根据主键user_id 物理删除
     */
    @Delete("delete from org_org_vs_user where  user_id = #{userId}")
    int deleteBulkDataByUserId(IEntity entity) throws Exception;
    /**
     * 根据主键org_id 物理删除
     */
    @Delete("delete from org_org_vs_user where  org_id = #{orgId}")
    int deleteBulkDataByOrgId(IEntity entity) throws Exception;
}