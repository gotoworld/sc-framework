package com.wu1g.api.auth;

import com.wu1g.vo.auth.AuthPerm;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息   业务处理接口类。
 */
public interface IAuthPermService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(AuthPerm bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(AuthPerm bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    public String recoveryDataById(AuthPerm bean) throws Exception;

    /**
     * <p>逻辑删除。
     */
    public String deleteDataById(AuthPerm bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<AuthPerm> findDataIsPage(AuthPerm bean);

    /**
     * <p>信息列表。
     */
    public List<AuthPerm> findDataIsList(AuthPerm bean);

    /**
     * <p>信息详情。
     */
    public AuthPerm findDataById(AuthPerm bean);

    /**
     * <p>信息树。
     */
    public List<AuthPerm> findDataTree(AuthPerm bean);

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    public List<AuthPerm> findPermDataIsListByRoleId(Map dto);

}