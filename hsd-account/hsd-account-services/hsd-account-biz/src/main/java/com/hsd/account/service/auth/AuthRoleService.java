package com.hsd.account.service.auth;

import com.github.pagehelper.PageHelper;
import com.hsd.account.api.auth.IAuthRoleService;
import com.hsd.account.dao.auth.IAuthRoleDao;
import com.hsd.account.dao.auth.IAuthRoleVsPermDao;
import com.hsd.account.entity.auth.AuthRole;
import com.hsd.account.entity.auth.AuthRoleVsPerm;
import com.hsd.dto.auth.AuthRoleDto;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@FeignService
@Slf4j
public class AuthRoleService extends BaseService implements IAuthRoleService {
    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthRoleVsPermDao authRoleVsPermDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody AuthRoleDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            AuthRole entity=copyTo(dto,AuthRole.class);
            if (getAuth().isPermitted("authRole:super")) {
                entity.setIsSuper(dto.getIsSuper() == null ? 0 : 1);// 超级管理员0否1是
            } else {
                entity.setIsSuper(0);// 超级管理员0否1是
            }
            // 判断数据是否存在
            if (authRoleDao.isDataYN(entity) != 0) {
                // 数据存在
                authRoleDao.update(entity);
                if (getAuth().isPermitted("authRole:parm")) {
                    // 1.清空当前角色权限关联信息
                    AuthRoleVsPerm roleVsPerm = new AuthRoleVsPerm();
                    roleVsPerm.setRoleId(entity.getId());
                    authRoleVsPermDao.deleteBulkDataByRoleId(roleVsPerm);
                }
            } else {
                // 新增
                authRoleDao.insert(entity);
                result.data=entity.getId();
            }
            if (getAuth().isPermitted("authRole:parm")) {
                // 2.新增角色权限关联信息
                if (dto.getPermIdArray() != null) {
                    List<AuthRoleVsPerm> xdtos = new ArrayList<>();
                    for (String permId : dto.getPermIdArray()) {
                        AuthRoleVsPerm authRoleVsPerm = new AuthRoleVsPerm();
                        authRoleVsPerm.setRoleId(entity.getId());
                        authRoleVsPerm.setPermId(permId);
                        xdtos.add(authRoleVsPerm);
                    }
                    authRoleVsPermDao.insertBatch(xdtos);
                }
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String deleteData(@RequestBody AuthRoleDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authRoleDao.deleteByPrimaryKey(copyTo(dto,AuthRole.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String deleteDataById(@RequestBody AuthRoleDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authRoleDao.deleteById(copyTo(dto,AuthRole.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public List<AuthRoleDto> findDataIsPage(@RequestBody AuthRoleDto dto) {
        List<AuthRoleDto> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = copyTo(authRoleDao.findDataIsPage(copyTo(dto,AuthRole.class)),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public List<AuthRoleDto> findDataIsList(@RequestBody AuthRoleDto dto) {
        List<AuthRoleDto> results = null;
        try {
            results = copyTo(authRoleDao.findDataIsList(copyTo(dto,AuthRole.class)),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public AuthRoleDto findDataById(@RequestBody AuthRoleDto dto) {
        AuthRoleDto result = null;
        try {
            result = copyTo(authRoleDao.selectByPrimaryKey(copyTo(dto,AuthRole.class)),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
}