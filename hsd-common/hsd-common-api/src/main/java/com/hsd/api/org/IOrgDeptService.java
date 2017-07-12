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
package com.hsd.api.org;


import com.hsd.vo.org.OrgDept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>组织架构_部门   业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface IOrgDeptService {
    String actPrefix = "/api/IOrgDeptService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(OrgDept bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(OrgDept bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(OrgDept bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(OrgDept bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<OrgDept> findDataIsPage(OrgDept bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<OrgDept> findDataIsList(OrgDept bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public OrgDept findDataById(OrgDept bean);

    /**
     * <p>信息树。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataTree")
    public List<OrgDept> findDataTree(OrgDept bean);
}