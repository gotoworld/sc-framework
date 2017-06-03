package com.wu1g.service.auth;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.auth.IAuthRoleService;
import com.wu1g.dao.auth.IAuthRoleDao;
import com.wu1g.dao.auth.IAuthRoleVsPermDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.auth.AuthRole;
import com.wu1g.vo.auth.AuthRoleVsPerm;
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
        String msg = "seccuss";
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
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(AuthRole bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                authRoleDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    public String deleteDataById(AuthRole bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                authRoleDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<AuthRole> findDataIsPage(AuthRole bean) {
        List<AuthRole> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<AuthRole>) authRoleDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public List<AuthRole> findDataIsList(AuthRole bean) {
        List<AuthRole> beans = null;
        try {
            beans = (List<AuthRole>) authRoleDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public AuthRole findDataById(AuthRole bean) {
        AuthRole bean1 = null;
        try {
            bean1 = (AuthRole) authRoleDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return bean1;
    }

    public String recoveryDataById(AuthRole bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                authRoleDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}