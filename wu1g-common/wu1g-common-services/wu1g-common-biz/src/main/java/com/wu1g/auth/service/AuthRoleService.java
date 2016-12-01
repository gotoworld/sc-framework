/*	
 * 权限_角色信息   业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.service;

import com.github.pagehelper.PageHelper;
import com.wu1g.auth.api.IAuthRoleService;
import com.wu1g.auth.dao.IAuthRoleDao;
import com.wu1g.auth.dao.IAuthRoleVsPermDao;
import com.wu1g.auth.vo.AuthRole;
import com.wu1g.auth.vo.AuthRoleVsPerm;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
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

@Service
@Slf4j
public class AuthRoleService extends BaseService implements IAuthRoleService {
    /**
     * 权限_角色信息 Dao接口类
     */
    @Autowired
    private IAuthRoleDao authRoleDao;
    /**
     * 权限_角色vs权限 数据库处理接口类
     */
    @Autowired
    private IAuthRoleVsPermDao authRoleVsPermDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(AuthRole bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                if (getAuth().isPermitted("authRole:super")) {
                    bean.setIsSuper(bean.getIsSuper() == null ? "0" : "1");// 超级管理员0否1是
                } else {
                    bean.setIsSuper("0");// 超级管理员0否1是
                }
                // 判断数据是否存在
                if (authRoleDao.isDataYN(bean) != 0) {
                    // 数据存在
                    authRoleDao.updateByPrimaryKeySelective(bean);
                    if (getAuth().isPermitted("authRole:parm")) {
                        // 1.清空当前角色权限关联信息
                        Map xdto = new HashMap();
                        xdto.put("roleId", bean.getId());
                        authRoleVsPermDao.deleteDataByRid(xdto);
                    }
                } else {
                    // 新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(22));
                    }
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
                            authRoleVsPerm.setCreateId(bean.getCreateId());
                            authRoleVsPerm.setCreateIp(bean.getCreateIp());
                            xdtos.add(authRoleVsPerm);
                        }
                        authRoleVsPermDao.insertBatch(xdtos);
                    }
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    /**
     * <p>
     * 信息编辑。
     * <p>
     * <ol>
     * [功能概要] <li>物理删除。
     * </ol>
     */
    public String deleteData(AuthRole bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                authRoleDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    /**
     * <p>
     * 信息 单条。
     * <p>
     * <ol>
     * [功能概要] <li>逻辑删除。
     * </ol>
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(AuthRole bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                authRoleDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    /**
     * <p>
     * 信息列表 分页。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索。 <li>分页。
     * </ol>
     */
    public List<AuthRole> findDataIsPage(AuthRole bean) {
        List<AuthRole> beans = null;
        try {
            PageHelper.startPage((Integer) bean.getPageNum(), (Integer) bean.getPageSize());
            beans = (List<AuthRole>) authRoleDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    /**
     * <p>
     * 信息列表。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索。 <li>列表。
     * </ol>
     */
    public List<AuthRole> findDataIsList(AuthRole bean) {
        List<AuthRole> beans = null;
        try {
            beans = (List<AuthRole>) authRoleDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    /**
     * <p>
     * 信息详情。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索。 <li>详情。
     * </ol>
     */
    public AuthRole findDataById(AuthRole bean) {
        AuthRole bean1 = null;
        try {
            bean1 = (AuthRole) authRoleDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    /**
     * <p>
     * 信息 单条。
     * <p>
     * <ol>
     * [功能概要] <li>恢复逻辑删除的数据。
     * </ol>
     */
    public String recoveryDataById(AuthRole bean) throws Exception {
        String msg = "1";
        if (bean != null) {
            try {
                authRoleDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}