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
package com.hsd.api.org;


import com.hsd.vo.auth.AuthRole;
import com.hsd.vo.org.OrgDept;
import com.hsd.vo.org.OrgUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>组织架构_用户   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account}")//, fallback = TestServiceHystrix.class)
public interface IOrgUserService {
    String actPrefix = "/api/IOrgUserService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(OrgUser bean) throws Exception;

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/updateData")
    public String updateData(OrgUser bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(OrgUser bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(OrgUser bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(OrgUser bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<OrgUser> findDataIsPage(OrgUser bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<OrgUser> findDataIsList(OrgUser bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public OrgUser findDataById(OrgUser bean);

    /**
     * <p>获取用户角色集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findRoleDataIsList")
    public List<AuthRole> findRoleDataIsList(OrgUser bean);

    /**
     * <p>获取用户所在部门集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDeptDataIsList")
    public List<OrgDept> findDeptDataIsList(OrgUser bean);

    /**
     * <p>某一种角色所有用户。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findUserList")
    public List<OrgUser> findUserList(OrgUser orgUserBean);

    /**
     * <p>某一种角色所有用户。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findUserIsPage")
    public List<OrgUser> findUserIsPage(OrgUser orgUserBean);

    /**
     * <p>判断用户id是否存在
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/isUidYN")
    public String isUidYN(String uid);
    /**
     * <p>密码修改
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/updatePwd")
    public String updatePwd(OrgUser dto) throws Exception;
}