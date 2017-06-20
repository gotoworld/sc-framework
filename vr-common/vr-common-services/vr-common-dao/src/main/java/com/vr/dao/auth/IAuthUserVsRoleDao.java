package com.vr.dao.auth;

import com.vr.framework.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>权限_用户vs角色  数据库处理接口类。
 */
@Mapper
public interface IAuthUserVsRoleDao extends IBaseDao {
    /**
     * 根据用户id清空用户关联角色信息
     */
    int deleteDataByUid(Map dto) throws Exception;
}