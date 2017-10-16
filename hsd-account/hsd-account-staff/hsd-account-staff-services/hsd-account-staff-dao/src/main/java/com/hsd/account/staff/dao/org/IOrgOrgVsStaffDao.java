package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 组织架构_员工vs部门 数据库处理接口类。
 */
@Mapper
public interface IOrgOrgVsStaffDao extends IBaseDao {
    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_org_vs_staff where  staff_id = #{staffId} and  org_id = #{orgId} ")
    int isDataYN(IEntity entity) throws Exception;
    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_org_vs_staff where  staff_id = #{staffId} and  org_id = #{orgId} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;
    /**
     * 根据主键staff_id 物理删除
     */
    @Delete("delete from org_org_vs_staff where  staff_id = #{staffId}")
    int deleteBulkDataByStaffId(IEntity entity) throws Exception;
    /**
     * 根据主键org_id 物理删除
     */
    @Delete("delete from org_org_vs_staff where  org_id = #{orgId}")
    int deleteBulkDataByOrgId(IEntity entity) throws Exception;

    List findOrgStaffIsList(IEntity entity) throws Exception;
}