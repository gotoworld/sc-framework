package com.wu1g.auth.dao;

import com.wu1g.auth.vo.AuthRole;
import com.wu1g.framework.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_角色信息  数据库处理接口类。
 */
@Mapper
public interface IAuthRoleDao extends IBaseDao {
    /**
     * 角色信息列表>根据用户id。
     */
    List<AuthRole> getRoleListByUId(Map dto) throws Exception;

    /**
     * 根据用户id,判断用户是否为超级管理员,要的就是特权.
     */
    int isSuperAdmin(Map dto) throws Exception;
}