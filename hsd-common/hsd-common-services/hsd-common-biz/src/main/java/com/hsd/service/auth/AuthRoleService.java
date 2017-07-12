package com.hsd.service.auth;

import com.github.pagehelper.PageHelper;
import com.hsd.api.auth.IAuthRoleService;
import com.hsd.dao.auth.IAuthRoleDao;
import com.hsd.dao.auth.IAuthRoleVsPermDao;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.vo.auth.AuthRole;
import com.hsd.vo.auth.AuthRoleVsPerm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public String saveOrUpdateData(AuthRole bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                if (getAuth().isPermitted("authRole:super")) {
                    bean.setIsSuper(bean.getIsSuper() == null ? 0 : 1);// 超级管理员0否1是
                } else {
                    bean.setIsSuper(0);// 超级管理员0否1是
                }
                // 判断数据是否存在
                if (authRoleDao.isDataYN(bean) != 0) {
                    // 数据存在
                    authRoleDao.update(bean);
                    if (getAuth().isPermitted("authRole:parm")) {
                        // 1.清空当前角色权限关联信息
                        Map xdto = new HashMap();
                        xdto.put("roleId", bean.getId());
                        authRoleVsPermDao.deleteDataByRid(xdto);
                    }
                } else {
                    // 新增
                    authRoleDao.insert(bean);
                }
                if (getAuth().isPermitted("authRole:parm")) {
                    // 2.新增角色权限关联信息
                    if (bean.getPermIdArray() != null) {
                        List<AuthRoleVsPerm> xdtos = new ArrayList<AuthRoleVsPerm>();
                        for (String permId : bean.getPermIdArray()) {
                            AuthRoleVsPerm authRoleVsPerm = new AuthRoleVsPerm();
                            authRoleVsPerm.setRoleId(bean.getId());
                            authRoleVsPerm.setPermId(permId);
                            xdtos.add(authRoleVsPerm);
                        }
                        authRoleVsPermDao.insertBatch(xdtos);
                    }
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public String deleteData(AuthRole bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                authRoleDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    public String deleteDataById(AuthRole bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                authRoleDao.deleteById(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthRole> findDataIsPage(AuthRole bean) {
        List<AuthRole> results = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            results = (List<AuthRole>) authRoleDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<AuthRole> findDataIsList(AuthRole bean) {
        List<AuthRole> results = null;
        try {
            results = (List<AuthRole>) authRoleDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public AuthRole findDataById(AuthRole bean) {
        AuthRole result = null;
        try {
            result = (AuthRole) authRoleDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(AuthRole bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                authRoleDao.recoveryDataById(bean);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }
}