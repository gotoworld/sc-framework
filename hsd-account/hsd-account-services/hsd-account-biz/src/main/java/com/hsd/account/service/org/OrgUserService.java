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
package com.hsd.account.service.org;

import com.github.pagehelper.PageHelper;
import com.hsd.account.api.org.IOrgUserService;
import com.hsd.account.vo.auth.AuthRole;
import com.hsd.account.vo.auth.AuthUserVsRole;
import com.hsd.account.vo.org.OrgDept;
import com.hsd.account.vo.org.OrgDeptVsUser;
import com.hsd.dao.account.auth.IAuthRoleDao;
import com.hsd.dao.account.auth.IAuthUserVsRoleDao;
import com.hsd.dao.account.org.IOrgDeptDao;
import com.hsd.dao.account.org.IOrgDeptVsUserDao;
import com.hsd.dao.account.org.IOrgUserDao;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.vo.org.OrgUser;
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
public class OrgUserService extends BaseService implements IOrgUserService {
    @Autowired
    private IOrgUserDao orgUserDao;
    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthUserVsRoleDao authUserVsRoleDao;
    @Autowired
    private IOrgDeptDao orgDepartmentDao;
    @Autowired
    private IOrgDeptVsUserDao orgUserVsDepartmentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgUser dto) throws Exception {
        Response result = new Response(0,"seccuss");
        if (dto != null) {
            try {
                // 判断数据是否存在
                if (orgUserDao.isDataYN(dto) != 0) {
                    // 数据存在
                    orgUserDao.update(dto);
                    Map xdto = new HashMap();
                    xdto.put("userId", dto.getId());
                    if (getAuth().isPermitted("orgUser:role.edit") && 1!=(dto.getId())) {
                        // 1.根据用户id清空用户角色关联表
                        authUserVsRoleDao.deleteDataByUid(xdto);
                    }
                    if (getAuth().isPermitted("orgUser:dept.edit")) {
                        // 1.根据用户id清空用户部门关联表
                        orgUserVsDepartmentDao.deleteDataByUid(xdto);
                    }
                } else {
                    // 新增
                    orgUserDao.insert(dto);
                    result.data=dto.getId();
                }
                if (getAuth().isPermitted("orgUser:role.edit")) {
                    // 2.新增用户角色关联信息
                    if (dto.getRoleIdArray() != null) {
                        List<AuthUserVsRole> xdtos = new ArrayList<AuthUserVsRole>();
                        for (Long roleId : dto.getRoleIdArray()) {
                            AuthUserVsRole authUserVsRoleDto = new AuthUserVsRole();
                            authUserVsRoleDto.setUserId(dto.getId());
                            authUserVsRoleDto.setRoleId(roleId);
                            xdtos.add(authUserVsRoleDto);
                        }
                        authUserVsRoleDao.insertBatch(xdtos);
                    }
                }
                if (getAuth().isPermitted("orgUser:dept.edit")) {
                    // 2.新增用户部门关联信息
                    if (dto.getDeptIdArray() != null) {
                        List<OrgDeptVsUser> xdtos = new ArrayList<OrgDeptVsUser>();
                        for (Long deptId : dto.getDeptIdArray()) {
                            OrgDeptVsUser orgDeptVsUserDto = new OrgDeptVsUser();
                            orgDeptVsUserDto.setUserId(dto.getId());
                            orgDeptVsUserDto.setDeptId(deptId);
                            xdtos.add(orgDeptVsUserDto);
                        }
                        orgUserVsDepartmentDao.insertBatch(xdtos);
                    }
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String updateData(@RequestBody OrgUser dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                // 判断数据是否存在
                if (orgUserDao.isDataYN(dto) != 0) {
                    // 数据存在
                    orgUserDao.update(dto);
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }
    public String deleteData(@RequestBody OrgUser dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgUserDao.deleteByPrimaryKey(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(@RequestBody OrgUser dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgUserDao.deleteById(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<OrgUser> findDataIsPage(@RequestBody OrgUser dto) {
        List<OrgUser> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = (List<OrgUser>) orgUserDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<OrgUser> findDataIsList(@RequestBody OrgUser dto) {
        List<OrgUser> results = null;
        try {
            results = (List<OrgUser>) orgUserDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public OrgUser findDataById(@RequestBody OrgUser dto) {
        OrgUser result = null;
        try {
            result = (OrgUser) orgUserDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(@RequestBody OrgUser dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgUserDao.recoveryDataById(dto);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthRole> findRoleDataIsList(@RequestBody OrgUser dto) {
        List<AuthRole> results = null;
        try {
            if (dto != null) {
                Map entity = new HashMap();
                entity.put("uid", dto.getId());
                results = authRoleDao.getRoleListByUId(entity);
            }
        } catch (Exception e) {
            log.error("获取用户角色集合,数据库处理异常!", e);
        }
        return results;
    }

    public List<OrgDept> findDeptDataIsList(@RequestBody OrgUser dto) {
        List<OrgDept> results = null;
        try {
            if (dto != null) {
                Map entity = new HashMap();
                entity.put("uid", dto.getId());
                results = orgDepartmentDao.getDeptListByUId(entity);
            }
        } catch (Exception e) {
            log.error("获取用户角色集合,数据库处理异常!", e);
        }
        return results;
    }

    public List<OrgUser> findUserList(@RequestBody OrgUser dto) {
        List<OrgUser> results = null;
        try {
            if (dto != null) {
                results = (List<OrgUser>) orgUserDao.getUserList(dto);
            }
        } catch (Exception e) {
            log.error("获取某一种角色所有用户,数据库处理异常!", e);
        }
        return results;
    }

    public List<OrgUser> findUserIsPage(@RequestBody OrgUser dto) {
        List<OrgUser> results = null;
        try {
            if (dto != null) {
                PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
                results = (List<OrgUser>) orgUserDao.getUserIsPage(dto);
            }
        } catch (Exception e) {
            log.error("获取某一种角色所有用户,数据库处理异常!", e);
        }
        return results;
    }

    public String isUidYN(String uid) {
        String result = "seccuss";
        try {
            if (ValidatorUtil.notEmpty(uid)) {
                if (orgUserDao.isUidYN(uid) == 0) {
                    result = "0";
                }
            }
        } catch (Exception e) {
            log.error("判断用户id是否存在,数据库处理异常!", e);
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String updatePwd(@RequestBody OrgUser dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                OrgUser entity=findDataById(dto);
                if (entity==null) throw new RuntimeException("用户不存在!");
                if(!entity.getPwd().equals(entity.getOldpwd())) throw new RuntimeException("原密码错误!");
                if(orgUserDao.updatePwd(entity)==0) throw new RuntimeException("密码修改失败,请重试!");
            } catch (Exception e) {
                log.error("用户修改密码异常!",e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}