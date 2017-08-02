/*	
 * 角色资源 业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.06.29      wuxiaogang         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 baseos System. - All Rights Reserved.		
 *	
 */
package com.hsd.account.service.auth;

import com.hsd.api.auth.IRoleSourceService;
import com.hsd.dto.auth.AuthPerm;
import com.hsd.dto.auth.AuthRole;
import com.hsd.dao.account.auth.IAuthPermDao;
import com.hsd.dao.account.auth.IAuthRoleDao;
import com.hsd.dao.account.org.IOrgUserDao;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.dto.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class RoleSourceService extends BaseService implements IRoleSourceService {

    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthPermDao authPermDao;
    @Autowired
    private IOrgUserDao orgUserDao;

    @Override
    public Integer isSuperAdmin(@RequestBody OrgUser dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return authRoleDao.isSuperAdmin(map);
        } catch (Exception e) {
            log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }

    @Override
    public List<AuthRole> getRoleListByUId(@RequestBody OrgUser dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return (List<AuthRole>) authRoleDao.getRoleListByUId(map);
        } catch (Exception e) {
            log.error("角色信息列表>根据用户id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public List<AuthPerm> getPermListByUId(@RequestBody OrgUser dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return authPermDao.getPermListByUId(map);
        } catch (Exception e) {
            log.error("角色权限信息列表>根据用户id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public OrgUser findUserByLoginName(@RequestParam("account") String account, @RequestParam("userType")  Integer userType) {
        try {
            Map dto = new HashMap();
            dto.put("account", account);
            dto.put("userType", userType);
            return (OrgUser) orgUserDao.findUserByLoginName(dto);
        } catch (Exception e) {
            log.error("用户信息>根据用户登录名,数据库处理异常!", e);
        }
        return null;
    }
    @Override
    public Integer lastLogin(@RequestBody OrgUser orgUser) {
        try {
            return orgUserDao.lastLogin(orgUser);
        } catch (Exception e) {
            log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }
}