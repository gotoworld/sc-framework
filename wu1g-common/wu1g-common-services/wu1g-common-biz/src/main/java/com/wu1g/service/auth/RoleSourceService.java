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
package com.wu1g.service.auth;

import com.wu1g.api.auth.IRoleSourceService;
import com.wu1g.dao.auth.IAuthPermDao;
import com.wu1g.dao.auth.IAuthRoleDao;
import com.wu1g.vo.auth.AuthPerm;
import com.wu1g.vo.auth.AuthRole;
import com.wu1g.framework.service.BaseService;
import com.wu1g.dao.org.IOrgUserDao;
import com.wu1g.vo.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleSourceService")
@Slf4j
public class RoleSourceService extends BaseService implements IRoleSourceService {

    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthPermDao authPermDao;
    @Autowired
    private IOrgUserDao orgUserDao;

    @Override
    public int isSuperAdmin(Map dto) {
        try {
            return authRoleDao.isSuperAdmin(dto);
        } catch (Exception e) {
            log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }

    @Override
    public List<AuthRole> getRoleListByUId(Map dto) {
        try {
            return authRoleDao.getRoleListByUId(dto);
        } catch (Exception e) {
            log.error("角色信息列表>根据用户id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public List<AuthPerm> getPermListByUId(Map dto) {
        try {
            return authPermDao.getPermListByUId(dto);
        } catch (Exception e) {
            log.error("角色权限信息列表>根据用户id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public OrgUser findUserByLoginName(String accid,Integer userType) {
        try {
            Map dto = new HashMap();
            dto.put("accid", accid);
            dto.put("userType", userType);
            return orgUserDao.findUserByLoginName(dto);
        } catch (Exception e) {
            log.error("用户信息>根据用户登录名,数据库处理异常!", e);
        }
        return null;
    }
    @Override
    public int lastLogin(OrgUser orgUser) {
        try {
            return orgUserDao.lastLogin(orgUser);
        } catch (Exception e) {
            log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }
}