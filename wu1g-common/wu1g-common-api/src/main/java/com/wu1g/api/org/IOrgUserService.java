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
import com.wu1g.vo.org.OrgDept;
import com.wu1g.vo.org.OrgUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>组织架构_用户   业务处理接口类。
 */
@FeignClient(name = "wu1g-service-server")//, fallback = TestServiceHystrix.class)
public interface IOrgUserService {

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(OrgUser bean) throws Exception;

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/updateData")
    public String updateData(OrgUser bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = "/deleteData")
    public String deleteData(OrgUser bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(OrgUser bean) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(OrgUser bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public List<OrgUser> findDataIsPage(OrgUser bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<OrgUser> findDataIsList(OrgUser bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = "/findDataById")
    public OrgUser findDataById(OrgUser bean);

    /**
     * <p>获取用户角色集合。
     */
    @RequestMapping(value = "/findRoleDataIsList")
    public List<AuthRole> findRoleDataIsList(OrgUser bean);

    /**
     * <p>获取用户所在部门集合。
     */
    @RequestMapping(value = "/findDeptDataIsList")
    public List<OrgDept> findDeptDataIsList(OrgUser bean);

    /**
     * <p>某一种角色所有用户。
     */
    @RequestMapping(value = "/findUserList")
    public List<OrgUser> findUserList(OrgUser orgUserBean);

    /**
     * <p>某一种角色所有用户。
     */
    @RequestMapping(value = "/findUserIsPage")
    public List<OrgUser> findUserIsPage(OrgUser orgUserBean);

    /**
     * <p>判断用户id是否存在
     */
    @RequestMapping(value = "/isUidYN")
    public String isUidYN(String uid);
    /**
     * <p>密码修改
     */
    @RequestMapping(value = "/updatePwd")
    public String updatePwd(OrgUser dto) throws Exception;
}