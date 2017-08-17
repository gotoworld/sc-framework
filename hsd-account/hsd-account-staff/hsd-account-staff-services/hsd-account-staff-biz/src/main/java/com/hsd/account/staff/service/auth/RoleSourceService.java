package com.hsd.account.staff.service.auth;

import com.hsd.account.staff.api.auth.IRoleSourceService;
import com.hsd.account.staff.dao.auth.IAuthPermDao;
import com.hsd.account.staff.dao.auth.IAuthRoleDao;
import com.hsd.account.staff.dao.org.IOrgUserDao;
import com.hsd.account.staff.entity.org.OrgUser;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
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
    public Integer isSuperAdmin(@RequestBody OrgUserDto dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return authRoleDao.isSuperAdmin(map);
        } catch (Exception e) {
            log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }

    @Override
    public List<AuthRoleDto> getRoleListByUserId(@RequestBody OrgUserDto dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return copyTo(authRoleDao.getRoleListByUserId(map),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("角色信息列表>根据用户id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public List<AuthPermDto> getPermListByUserId(@RequestBody OrgUserDto dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return copyTo(authPermDao.getPermListByUserId(map),AuthPermDto.class);
        } catch (Exception e) {
            log.error("角色权限信息列表>根据用户id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public OrgUserDto findUserByAccount(@RequestParam("account") String account, @RequestParam("userType")  Integer userType) {
        try {
            Map dto = new HashMap();
            dto.put("account", account);
            dto.put("userType", userType);
            return copyTo(orgUserDao.findUserByAccount(dto),OrgUserDto.class);
        } catch (Exception e) {
            log.error("用户信息>根据用户登录名,数据库处理异常!", e);
        }
        return null;
    }
    @Override
    public Integer lastLogin(@RequestBody OrgUserDto dto) {
        try {
            return orgUserDao.lastLogin(copyTo(dto, OrgUser.class));
        } catch (Exception e) {
            log.error("根据用户id,判断用户是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }
}