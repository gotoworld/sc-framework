package com.vr.dao.auth;

import com.vr.vo.auth.AuthPerm;
import com.vr.framework.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息  数据库处理接口类。
 */
@Mapper
public interface IAuthPermDao extends IBaseDao {
    /**
     * 角色权限信息列表>根据用户id。
     */
    List getPermListByUId(Map dto) throws Exception;

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    public List<AuthPerm> findPermDataIsListByRoleId(Map dto) throws Exception;
}