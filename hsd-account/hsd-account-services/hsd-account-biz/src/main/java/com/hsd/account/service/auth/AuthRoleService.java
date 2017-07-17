package com.hsd.account.service.auth;

import com.github.pagehelper.PageHelper;
import com.hsd.account.api.auth.IAuthRoleService;
import com.hsd.vo.auth.AuthRole;
import com.hsd.account.vo.auth.AuthRoleVsPerm;
import com.hsd.dao.account.auth.IAuthRoleDao;
import com.hsd.dao.account.auth.IAuthRoleVsPermDao;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class AuthRoleService extends BaseService implements IAuthRoleService {
    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthRoleVsPermDao authRoleVsPermDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody AuthRole dto) throws Exception {
        Response result = new Response(0,"seccuss");
        if (dto != null) {
            try {
                if (getAuth().isPermitted("authRole:super")) {
                    dto.setIsSuper(dto.getIsSuper() == null ? 0 : 1);// 超级管理员0否1是
                } else {
                    dto.setIsSuper(0);// 超级管理员0否1是
                }
                // 判断数据是否存在
                if (authRoleDao.isDataYN(dto) != 0) {
                    // 数据存在
                    authRoleDao.update(dto);
                    if (getAuth().isPermitted("authRole:parm")) {
                        // 1.清空当前角色权限关联信息
                        Map xdto = new HashMap();
                        xdto.put("roleId", dto.getId());
                        authRoleVsPermDao.deleteDataByRid(xdto);
                    }
                } else {
                    // 新增
                    authRoleDao.insert(dto);
                    result.data=dto.getId();
                }
                if (getAuth().isPermitted("authRole:parm")) {
                    // 2.新增角色权限关联信息
                    if (dto.getPermIdArray() != null) {
                        List<AuthRoleVsPerm> xdtos = new ArrayList<AuthRoleVsPerm>();
                        for (String permId : dto.getPermIdArray()) {
                            AuthRoleVsPerm authRoleVsPerm = new AuthRoleVsPerm();
                            authRoleVsPerm.setRoleId(dto.getId());
                            authRoleVsPerm.setPermId(permId);
                            xdtos.add(authRoleVsPerm);
                        }
                        authRoleVsPermDao.insertBatch(xdtos);
                    }
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    public String deleteData(@RequestBody AuthRole dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                authRoleDao.deleteByPrimaryKey(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    public String deleteDataById(@RequestBody AuthRole dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                authRoleDao.deleteById(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthRole> findDataIsPage(@RequestBody AuthRole dto) {
        List<AuthRole> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = (List<AuthRole>) authRoleDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<AuthRole> findDataIsList(@RequestBody AuthRole dto) {
        List<AuthRole> results = null;
        try {
            results = (List<AuthRole>) authRoleDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public AuthRole findDataById(@RequestBody AuthRole dto) {
        AuthRole result = null;
        try {
            result = (AuthRole) authRoleDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(@RequestBody AuthRole dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                authRoleDao.recoveryDataById(dto);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }
}