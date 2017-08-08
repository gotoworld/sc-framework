package com.hsd.account.service.org;

import com.github.pagehelper.PageHelper;
import com.hsd.account.api.org.IOrgUserService;
import com.hsd.account.dao.auth.IAuthRoleDao;
import com.hsd.account.dao.auth.IAuthUserVsRoleDao;
import com.hsd.account.dao.org.IOrgInfoDao;
import com.hsd.account.dao.org.IOrgOrgVsUserDao;
import com.hsd.account.dao.org.IOrgUserDao;
import com.hsd.account.dto.org.OrgInfoDto;
import com.hsd.account.entity.auth.AuthUserVsRole;
import com.hsd.account.entity.org.OrgOrgVsUser;
import com.hsd.account.entity.org.OrgUser;
import com.hsd.account.dto.auth.AuthRoleDto;
import com.hsd.account.dto.org.OrgUserDto;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.ValidatorUtil;
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
    private IOrgInfoDao orgInfoDao;
    @Autowired
    private IOrgOrgVsUserDao orgOrgVsUserDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgUserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgUser entity=copyTo(dto,OrgUser.class);
            // 判断数据是否存在
            if (orgUserDao.isDataYN(entity) != 0) {
                // 数据存在
                orgUserDao.update(entity);
                if (getAuth().isPermitted("orgUser:role.edit") && 1!=(entity.getId())) {
                    AuthUserVsRole userVsRole = new AuthUserVsRole();
                    userVsRole.setUserId(entity.getId());
                    // 1.根据用户id清空用户角色关联表
                    authUserVsRoleDao.deleteBulkDataByUserId(userVsRole);
                }
                if (getAuth().isPermitted("orgUser:dept.edit")) {
                    OrgOrgVsUser orgVsUser = new OrgOrgVsUser();
                    orgVsUser.setUserId(entity.getId());
                    // 1.根据用户id清空用户部门关联表
                    orgOrgVsUserDao.deleteBulkDataByUserId(orgVsUser);
                }
            } else {
                // 新增
                orgUserDao.insert(entity);
                result.data=entity.getId();
            }
            if (getAuth().isPermitted("orgUser:role.edit")) {
                // 2.新增用户角色关联信息
                if (dto.getRoleIdArray() != null) {
                    List<AuthUserVsRole> xEntityList = new ArrayList<>();
                    for (Long roleId : dto.getRoleIdArray()) {
                        AuthUserVsRole authUserVsRole = new AuthUserVsRole();
                        authUserVsRole.setUserId(entity.getId());
                        authUserVsRole.setRoleId(roleId);
                        xEntityList.add(authUserVsRole);
                    }
                    authUserVsRoleDao.insertBatch(xEntityList);
                }
            }
            if (getAuth().isPermitted("orgUser:dept.edit")) {
                // 2.新增用户部门关联信息
                if (dto.getOrgIdArray() != null) {
                    List<OrgOrgVsUser> xEntityList = new ArrayList<>();
                    for (Long orgId : dto.getOrgIdArray()) {
                        OrgOrgVsUser OrgOrgVsUser = new OrgOrgVsUser();
                        OrgOrgVsUser.setUserId(entity.getId());
                        OrgOrgVsUser.setOrgId(orgId);
                        xEntityList.add(OrgOrgVsUser);
                    }
                    orgOrgVsUserDao.insertBatch(xEntityList);
                }
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String updateData(@RequestBody OrgUserDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgUser entity=copyTo(dto,OrgUser.class);
            // 判断数据是否存在
            if (orgUserDao.isDataYN(entity) != 0) {
                // 数据存在
                orgUserDao.update(entity);
            }
        } catch (Exception e) {
            log.error("信息更新失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public String deleteData(@RequestBody OrgUserDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgUserDao.deleteByPrimaryKey(copyTo(dto,OrgUser.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(@RequestBody OrgUserDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgUserDao.deleteById(copyTo(dto,OrgUser.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public List<OrgUserDto> findDataIsPage(@RequestBody OrgUserDto dto) {
        List<OrgUserDto> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = copyTo(orgUserDao.findDataIsPage(copyTo(dto,OrgUser.class)),OrgUserDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public List<OrgUserDto> findDataIsList(@RequestBody OrgUserDto dto) {
        List<OrgUserDto> results = null;
        try {
            results = copyTo(orgUserDao.findDataIsList(copyTo(dto,OrgUser.class)),OrgUserDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public OrgUserDto findDataById(@RequestBody OrgUserDto dto) {
        OrgUserDto result = null;
        try {
            result = copyTo(orgUserDao.selectByPrimaryKey(copyTo(dto,OrgUser.class)),OrgUserDto.class);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String recoveryDataById(@RequestBody OrgUserDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgUserDao.recoveryDataById(copyTo(dto,OrgUser.class));
        } catch (Exception e) {
            result = "信息恢复失败!";
            log.error(result, e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public List<AuthRoleDto> findRoleDataIsList(@RequestBody OrgUserDto dto) {
        List<AuthRoleDto> results = null;
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
                Map entity = new HashMap();
                entity.put("uid", dto.getId());
                results = copyTo(authRoleDao.getRoleListByUId(entity),AuthRoleDto.class);

        } catch (Exception e) {
            log.error("获取用户角色集合,数据库处理异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public List<OrgInfoDto> findDeptDataIsList(@RequestBody OrgUserDto dto) {
        List<OrgInfoDto> results = null;
        try {
            if (dto != null) {
                Map entity = new HashMap();
                entity.put("uid", dto.getId());
                results = copyTo(orgInfoDao.getDeptListByUId(entity),OrgInfoDto.class);
            }
        } catch (Exception e) {
            log.error("获取用户角色集合,数据库处理异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public List<OrgUserDto> findUserList(@RequestBody OrgUserDto dto) {
        List<OrgUserDto> results = null;
        try {
            if (dto != null) {
                results = copyTo(orgUserDao.getUserList(copyTo(dto,OrgUser.class)),OrgUserDto.class);
            }
        } catch (Exception e) {
            log.error("获取某一种角色所有用户,数据库处理异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public List<OrgUserDto> findUserIsPage(@RequestBody OrgUserDto dto) {
        List<OrgUserDto> results = null;
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            results = copyTo(orgUserDao.getUserIsPage(copyTo(dto,OrgUser.class)),OrgUserDto.class);
        } catch (Exception e) {
            log.error("获取某一种角色所有用户,数据库处理异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
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
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String updatePwd(@RequestBody OrgUserDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgUserDto orgUserDto=findDataById(dto);
            if (orgUserDto==null) throw new RuntimeException("用户不存在!");
            if(!orgUserDto.getPwd().equals(orgUserDto.getOldpwd())) throw new RuntimeException("原密码错误!");
            if(orgUserDao.updatePwd(copyTo(orgUserDto,OrgUser.class))==0) throw new RuntimeException("密码修改失败,请重试!");
        } catch (Exception e) {
            log.error("用户修改密码异常!",e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
}