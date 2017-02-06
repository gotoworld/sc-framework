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
 */
public interface IOrgDepartmentService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(OrgDepartment bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(OrgDepartment bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(OrgDepartment bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(OrgDepartment bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<OrgDepartment> findDataIsPage(OrgDepartment bean);

    /**
     * <p>信息列表。
     */
    public List<OrgDepartment> findDataIsList(OrgDepartment bean);

    /**
     * <p>信息详情。
     */
    public OrgDepartment findDataById(OrgDepartment bean);

    /**
     * <p>信息树。
     */
    public List<OrgDepartment> findDataTree(OrgDepartment bean);
}