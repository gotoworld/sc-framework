package com.hsd.account.staff.service.auth;

import com.hsd.account.staff.api.auth.IRoleSourceService;
import com.hsd.account.staff.dao.auth.IAuthPermDao;
import com.hsd.account.staff.dao.auth.IAuthRoleDao;
import com.hsd.account.staff.dao.org.IOrgStaffDao;
import com.hsd.account.staff.entity.org.OrgStaff;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
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
    private IOrgStaffDao orgStaffDao;

    @Override
    public Integer isSuperAdmin(@RequestBody OrgStaffDto dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("staffId", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return authRoleDao.isSuperAdmin(map);
        } catch (Exception e) {
            log.error("根据员工id,判断员工是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }

    @Override
    public List<AuthRoleDto> getRoleListByStaffId(@RequestBody OrgStaffDto dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("staffId", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return copyTo(authRoleDao.getRoleListByStaffId(map),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("角色信息列表>根据员工id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public List<AuthPermDto> getPermListByStaffId(@RequestBody OrgStaffDto dto) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("staffId", dto.getId());
            map.put("iissuperman", dto.getIissuperman());
            return copyTo(authPermDao.getPermListByStaffId(map),AuthPermDto.class);
        } catch (Exception e) {
            log.error("角色权限信息列表>根据员工id,数据库处理异常!", e);
        }
        return null;
    }

    @Override
    public OrgStaffDto findStaffByAccount(@RequestParam("account") String account, @RequestParam("staffType")  Integer staffType) {
        try {
            Map dto = new HashMap();
            dto.put("account", account);
            dto.put("staffType", staffType);
            return copyTo(orgStaffDao.findStaffByAccount(dto),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("员工信息>根据员工登录名,数据库处理异常!", e);
        }
        return null;
    }
    @Override
    public Integer lastLogin(@RequestBody OrgStaffDto dto) {
        try {
            return orgStaffDao.lastLogin(copyTo(dto, OrgStaff.class));
        } catch (Exception e) {
            log.error("更新最后登录时间!处理异常!", e);
        }
        return 0;
    }
}