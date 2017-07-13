package com.hsd.dao.org;

import com.hsd.framework.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


/**
 * <p>
 * 组织架构_用户vs部门 数据库处理接口类。
 */
@Mapper
public interface IOrgDeptVsUserDao extends IBaseDao {
    /**
     * 根据用户id清空用户关联部门信息
     */
    int deleteDataByUid(Map dto) throws Exception;
}