/*	
 * 组织架构_用户   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.api;


import com.wu1g.account.vo.AuthRole;
import com.wu1g.org.vo.OrgDepartment;
import com.wu1g.org.vo.OrgUser;

import java.util.List;

/**
 * <p>组织架构_用户   业务处理接口类。
 * <ol>[功能概要]
 * <li>编辑(新增or修改)。
 * <li>详情检索。
 * <li>分页检索。
 * <li>列表检索。
 * <li>逻辑删除。
 * <li>物理删除。
 * <li>恢复逻辑删除。
 * </ol>
 *
 * @author easycode
 */
public interface IOrgUserService {

    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>新增信息。
     * <li>修改信息。
     * </ol>
     *
     *
     */
    public String saveOrUpdateData(OrgUser bean) throws Exception;

    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>修改信息。
     * </ol>
     *
     *
     * @throws Exception
     */
    public String updateData(OrgUser bean) throws Exception;

    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>物理删除。
     * </ol>
     *
     *
     */
    public String deleteData(OrgUser bean) throws Exception;

    /**
     * <p>信息 单条。
     * <ol>[功能概要]
     * <li>恢复逻辑删除的数据。
     * </ol>
     *
     *
     */
    public String recoveryDataById(OrgUser bean) throws Exception;

    /**
     * <p>信息 单条。
     * <ol>[功能概要]
     * <li>逻辑删除。
     * </ol>
     *
     *
     */
    public String deleteDataById(OrgUser bean) throws Exception;

    /**
     * <p>信息列表 分页。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>分页。
     * </ol>
     *
     *
     */
    public List<OrgUser> findDataIsPage(OrgUser bean);

    /**
     * <p>信息列表。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     * </ol>
     *
     *
     */
    public List<OrgUser> findDataIsList(OrgUser bean);

    /**
     * <p>信息详情。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>详情。
     * </ol>
     *
     *
     */
    public OrgUser findDataById(OrgUser bean);

    /**
     * <p>获取用户角色集合。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     * </ol>
     *
     *
     */
    public List<AuthRole> findRoleDataIsList(OrgUser bean);

    /**
     * <p>获取用户所在部门集合。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     * </ol>
     *
     *
     */
    public List<OrgDepartment> findDeptDataIsList(OrgUser bean);

    /**
     * <p>某一种角色所有用户。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     * </ol>
     *
     *
     */
    public List<OrgUser> findUserList(OrgUser userBean);

    /**
     * <p>某一种角色所有用户。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>分页。
     * </ol>
     *
     *
     */
    public List<OrgUser> findUserIsPage(OrgUser userBean);

    /**
     * <p>判断用户id是否存在
     */
    public String isUidYN(String uid);
}