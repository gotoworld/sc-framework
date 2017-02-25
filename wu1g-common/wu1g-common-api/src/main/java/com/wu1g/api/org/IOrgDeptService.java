/*	
 * 组织架构_部门   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.api.org;


import com.wu1g.vo.org.OrgDept;

import java.util.List;

/**
 * <p>组织架构_部门   业务处理接口类。
 */
public interface IOrgDeptService {

    /**
     * <p>信息编辑。
     */
    public String saveOrUpdateData(OrgDept bean) throws Exception;

    /**
     * <p>物理删除。
     */
    public String deleteData(OrgDept bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    public String recoveryDataById(OrgDept bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    public String deleteDataById(OrgDept bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    public List<OrgDept> findDataIsPage(OrgDept bean);

    /**
     * <p>信息列表。
     */
    public List<OrgDept> findDataIsList(OrgDept bean);

    /**
     * <p>信息详情。
     */
    public OrgDept findDataById(OrgDept bean);

    /**
     * <p>信息树。
     */
    public List<OrgDept> findDataTree(OrgDept bean);
}