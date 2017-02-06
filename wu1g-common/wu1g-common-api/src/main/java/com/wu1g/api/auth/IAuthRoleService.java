package com.wu1g.api.auth;


import com.wu1g.vo.auth.AuthRole;

import java.util.List;

/**
 * <p>权限_角色信息   业务处理接口类。
 */
public interface IAuthRoleService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(AuthRole bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(AuthRole bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    public String recoveryDataById(AuthRole bean) throws Exception;

    /**
     * <p>逻辑删除。
     */
    public String deleteDataById(AuthRole bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<AuthRole> findDataIsPage(AuthRole bean);

    /**
     * <p>信息列表。
     */
    public List<AuthRole> findDataIsList(AuthRole bean);

    /**
     * <p>信息详情。
     */
    public AuthRole findDataById(AuthRole bean);
}