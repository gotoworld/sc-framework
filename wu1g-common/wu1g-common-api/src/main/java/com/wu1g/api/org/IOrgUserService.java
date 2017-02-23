/*	
 * 组织架构_用户   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.api.org;


import com.wu1g.vo.auth.AuthRole;
import com.wu1g.vo.org.OrgDepartment;
import com.wu1g.vo.org.OrgUser;

import java.util.List;

/**
 * <p>组织架构_用户   业务处理接口类。
 */
public interface IOrgUserService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(OrgUser bean) throws Exception;

    /**
     * <p>信息编辑。
     */
    public String updateData(OrgUser bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(OrgUser bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(OrgUser bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(OrgUser bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<OrgUser> findDataIsPage(OrgUser bean);

    /**
     * <p>信息列表。
     */
    public List<OrgUser> findDataIsList(OrgUser bean);

    /**
     * <p>信息详情。
     */
    public OrgUser findDataById(OrgUser bean);

    /**
     * <p>获取用户角色集合。
     */
    public List<AuthRole> findRoleDataIsList(OrgUser bean);

    /**
     * <p>获取用户所在部门集合。
     */
    public List<OrgDepartment> findDeptDataIsList(OrgUser bean);

    /**
     * <p>某一种角色所有用户。
     */
    public List<OrgUser> findUserList(OrgUser userBean);

    /**
     * <p>某一种角色所有用户。
     */
    public List<OrgUser> findUserIsPage(OrgUser userBean);

    /**
     * <p>判断用户id是否存在
     */
    public String isUidYN(String uid);
}