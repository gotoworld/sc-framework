package com.wu1g.auth.dao;

import com.wu1g.framework.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>权限_角色vs权限  数据库处理接口类。
 */
@Mapper
public interface IAuthRoleVsPermDao extends IBaseDao {
    /**
     * 根据角色id清空角色关联权限信息
     */
    int deleteDataByRid(Map dto) throws Exception;
}