/*	
 * 组织架构_部门   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.api;


import com.wu1g.org.vo.OrgDepartment;

import java.util.List;

/**
 * <p>组织架构_部门   业务处理接口类。
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
public interface IOrgDepartmentService {

    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>新增信息。
     * <li>修改信息。
     * </ol>
     *
     *
     */
    public String saveOrUpdateData(OrgDepartment bean) throws Exception;

    /**
     * <p>信息编辑。
     * <ol>[功能概要]
     * <li>物理删除。
     * </ol>
     *
     *
     */
    public String deleteData(OrgDepartment bean) throws Exception;

    /**
     * <p>信息 单条。
     * <ol>[功能概要]
     * <li>恢复逻辑删除的数据。
     * </ol>
     *
     *
     */
    public String recoveryDataById(OrgDepartment bean) throws Exception;

    /**
     * <p>信息 单条。
     * <ol>[功能概要]
     * <li>逻辑删除。
     * </ol>
     *
     *
     */
    public String deleteDataById(OrgDepartment bean) throws Exception;

    /**
     * <p>信息列表 分页。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>分页。
     * </ol>
     *
     *
     */
    public List<OrgDepartment> findDataIsPage(OrgDepartment bean);

    /**
     * <p>信息列表。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>列表。
     * </ol>
     *
     *
     */
    public List<OrgDepartment> findDataIsList(OrgDepartment bean);

    /**
     * <p>信息详情。
     * <ol>[功能概要]
     * <li>信息检索。
     * <li>详情。
     * </ol>
     *
     *
     */
    public OrgDepartment findDataById(OrgDepartment bean);

    /**
     * <p>信息列表。
     * <ol>[功能概要]
     * <li>信息检索 根据xx查询所有圈圈。
     * </ol>
     *
     *
     */
    public List<OrgDepartment> findDataTree(OrgDepartment bean);
}