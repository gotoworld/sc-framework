package com.hsd.account.staff.dao.org;

import com.hsd.framework.IBaseDao;
import com.hsd.framework.IEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>APP员工表 数据库处理接口类。
 */
@Mapper
public interface IOrgStaffAppDao extends IBaseDao {

    /**
     * 判断是否存在
     */
    @Select("select IFNULL(count(0),0) as count from org_staff_app where   id = #{id} ")
    int isDataYN(IEntity entity) throws Exception;

    /**
     * 根据主键 物理删除
     */
    @Delete("delete from org_staff_app where  id = #{id} ")
    int deleteByPrimaryKey(IEntity entity) throws Exception;

    Object findDataByAppIdAndStaffId(IEntity entity) throws Exception;
}