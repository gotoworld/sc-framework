/*	
 * 组织架构_用户   业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.service.org;

import com.github.pagehelper.PageHelper;
import com.wu1g.dao.auth.IAuthRoleDao;
import com.wu1g.dao.auth.IAuthUserVsRoleDao;
import com.wu1g.vo.auth.AuthRole;
import com.wu1g.vo.auth.AuthUserVsRole;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.api.org.IOrgUserService;
import com.wu1g.dao.org.IOrgDepartmentDao;
import com.wu1g.dao.org.IOrgUserDao;
import com.wu1g.dao.org.IOrgUserVsDepartmentDao;
import com.wu1g.vo.org.OrgDepartment;
import com.wu1g.vo.org.OrgUser;
import com.wu1g.vo.org.OrgUserVsDepartment;
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
public class OrgUserService extends BaseService implements IOrgUserService {
    @Autowired
    private IOrgUserDao orgUserDao;
    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthUserVsRoleDao authUserVsRoleDao;
    @Autowired
    private IOrgDepartmentDao orgDepartmentDao;
    @Autowired
    private IOrgUserVsDepartmentDao orgUserVsDepartmentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(OrgUser bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (orgUserDao.isDataYN(bean) != 0) {
                    // 数据存在
                    orgUserDao.updateByPrimaryKeySelective(bean);
                    Map xdto = new HashMap();
                    xdto.put("userId", bean.getId());
                    if (getAuth().isPermitted("orgUser:role.edit") && !"4d868ddfd70342b093e3013886e00ea9".equals(bean.getId())) {
                        // 1.根据用户id清空用户角色关联表
                        authUserVsRoleDao.deleteDataByUid(xdto);
                    }
                    if (getAuth().isPermitted("orgUser:dept.edit")) {
                        // 1.根据用户id清空用户部门关联表
                        orgUserVsDepartmentDao.deleteDataByUid(xdto);
                    }
                } else {
                    // 新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(32));
                    }
                    orgUserDao.insert(bean);
                }
                if (getAuth().isPermitted("orgUser:role.edit")) {
                    // 2.新增用户角色关联信息
                    if (bean.getRoleIdArray() != null) {
                        List<AuthUserVsRole> xdtos = new ArrayList<AuthUserVsRole>();
                        for (String roleId : bean.getRoleIdArray()) {
                            AuthUserVsRole authUserVsRoleDto = new AuthUserVsRole();
                            authUserVsRoleDto.setUserId(bean.getId());
                            authUserVsRoleDto.setRoleId(roleId);
                            authUserVsRoleDto.setCreateId(bean.getCreateId());
                            authUserVsRoleDto.setCreateIp(bean.getCreateIp());
                            xdtos.add(authUserVsRoleDto);
                        }
                        authUserVsRoleDao.insertBatch(xdtos);
                    }
                }
                if (getAuth().isPermitted("orgUser:dept.edit")) {
                    // 2.新增用户部门关联信息
                    if (bean.getDeptIdArray() != null) {
                        List<OrgUserVsDepartment> xdtos = new ArrayList<OrgUserVsDepartment>();
                        for (String deptId : bean.getDeptIdArray()) {
                            OrgUserVsDepartment orgUserVsDepartmentDto = new OrgUserVsDepartment();
                            orgUserVsDepartmentDto.setUserid(bean.getId());
                            orgUserVsDepartmentDto.setDepartmentid(deptId);
                            orgUserVsDepartmentDto.setCreateId(bean.getCreateId());
                            orgUserVsDepartmentDto.setCreateIp(bean.getCreateIp());
                            xdtos.add(orgUserVsDepartmentDto);
                        }
                        orgUserVsDepartmentDao.insertBatch(xdtos);
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String updateData(OrgUser bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (orgUserDao.isDataYN(bean) != 0) {
                    // 数据存在
                    orgUserDao.updateByPrimaryKeySelective(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(OrgUser bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgUserDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(OrgUser bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgUserDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<OrgUser> findDataIsPage(OrgUser bean) {
        List<OrgUser> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<OrgUser>) orgUserDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<OrgUser> findDataIsList(OrgUser bean) {
        List<OrgUser> beans = null;
        try {
            beans = (List<OrgUser>) orgUserDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public OrgUser findDataById(OrgUser bean) {
        OrgUser bean1 = null;
        try {
            bean1 = (OrgUser) orgUserDao.selectByPrimaryKey(bean);
            // if(bean1!=null && ValidatorUtil.notEmpty(bean1.getDetailInfo())){
            // bean1.setDetailInfo(IOHelper.readHtml(bean1.getDetailInfo()));
            // }
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(OrgUser bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgUserDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<AuthRole> findRoleDataIsList(OrgUser bean) {
        List<AuthRole> beans = null;
        try {
            if (bean != null) {
                Map dto = new HashMap();
                dto.put("uid", bean.getId());
                beans = authRoleDao.getRoleListByUId(dto);
            }
        } catch (Exception e) {
            log.error("获取用户角色集合,数据库处理异常!", e);
        }
        return beans;
    }

    public List<OrgDepartment> findDeptDataIsList(OrgUser bean) {
        List<OrgDepartment> beans = null;
        try {
            if (bean != null) {
                Map dto = new HashMap();
                dto.put("uid", bean.getId());
                beans = orgDepartmentDao.getDeptListByUId(dto);
            }
        } catch (Exception e) {
            log.error("获取用户角色集合,数据库处理异常!", e);
        }
        return beans;
    }

    public List<OrgUser> findUserList(OrgUser bean) {
        List<OrgUser> beans = null;
        try {
            if (bean != null) {
                beans = orgUserDao.getUserList(bean);
            }
        } catch (Exception e) {
            log.error("获取某一种角色所有用户,数据库处理异常!", e);
        }
        return beans;
    }

    public List<OrgUser> findUserIsPage(OrgUser bean) {
        List<OrgUser> beans = null;
        try {
            if (bean != null) {
                PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
                beans = orgUserDao.getUserIsPage(bean);
            }
        } catch (Exception e) {
            log.error("获取某一种角色所有用户,数据库处理异常!", e);
        }
        return beans;
    }

    public String isUidYN(String uid) {
        String msg = "seccuss";
        try {
            if (ValidatorUtil.notEmpty(uid)) {
                if (orgUserDao.isUidYN(uid) == 0) {
                    msg = "0";
                }
            }
        } catch (Exception e) {
            log.error("判断用户id是否存在,数据库处理异常!", e);
        }
        return msg;
    }
}