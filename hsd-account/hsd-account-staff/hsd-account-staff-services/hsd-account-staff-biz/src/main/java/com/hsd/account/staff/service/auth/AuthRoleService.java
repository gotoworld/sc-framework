package com.hsd.account.staff.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.auth.IAuthRoleService;
import com.hsd.account.staff.dao.auth.IAuthRoleDao;
import com.hsd.account.staff.dao.auth.IAuthRoleVsAppDao;
import com.hsd.account.staff.dao.auth.IAuthRoleVsMenuDao;
import com.hsd.account.staff.dao.auth.IAuthRoleVsPermDao;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.sys.SysAppDto;
import com.hsd.account.staff.dto.sys.SysMenuDto;
import com.hsd.account.staff.entity.auth.AuthRole;
import com.hsd.account.staff.entity.auth.AuthRoleVsApp;
import com.hsd.account.staff.entity.auth.AuthRoleVsMenu;
import com.hsd.account.staff.entity.auth.AuthRoleVsPerm;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
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
    @Autowired
    private IAuthRoleVsMenuDao authRoleVsMenuDao;
    @Autowired
    private IAuthRoleVsAppDao authRoleVsAppDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody AuthRoleDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            AuthRole entity=copyTo(dto,AuthRole.class);
            if (JwtUtil.isPermitted("authRole:super")) {
                entity.setIsSuper(dto.getIsSuper() == null ? 0 : 1);// 超级管理员0否1是
            } else {
                entity.setIsSuper(0);// 超级管理员0否1是
            }
            // 判断数据是否存在
            if (authRoleDao.isDataYN(entity) != 0) {
                // 数据存在
                authRoleDao.update(entity);
                if (JwtUtil.isPermitted("authRole:edit:perm")) {
                    // 1.清空当前角色权限关联信息
                    AuthRoleVsPerm roleVsPerm = new AuthRoleVsPerm();
                    roleVsPerm.setRoleId(entity.getId());
                    authRoleVsPermDao.deleteBulkDataByRoleId(roleVsPerm);
                }
                if (JwtUtil.isPermitted("authRole:edit:menu")) {
                    // 1.清空当前角色菜单关联信息
                    AuthRoleVsMenu roleVsMenu = new AuthRoleVsMenu();
                    roleVsMenu.setRoleId(entity.getId());
                    authRoleVsMenuDao.deleteBulkDataByRoleId(roleVsMenu);
                }
                if (JwtUtil.isPermitted("authRole:edit:app")) {
                    // 1.清空当前角色应用关联信息
                    AuthRoleVsApp roleVsApp = new AuthRoleVsApp();
                    roleVsApp.setRoleId(entity.getId());
                    authRoleVsAppDao.deleteBulkDataByRoleId(roleVsApp);
                }
            } else {
                // 新增
                authRoleDao.insert(entity);
                result.data=entity.getId();
            }
            if (JwtUtil.isPermitted("authRole:edit:perm")) {
                // 2.新增角色权限关联信息
                if (dto.getPermIds() != null) {
                    List<AuthRoleVsPerm> xdtos = new ArrayList<>();
                    for (String permId : dto.getPermIds()) {
                        AuthRoleVsPerm authRoleVsPerm = new AuthRoleVsPerm();
                        authRoleVsPerm.setRoleId(entity.getId());
                        authRoleVsPerm.setPermId(permId);
                        authRoleVsPerm.setCreateId(dto.getCreateId());
                        xdtos.add(authRoleVsPerm);
                    }
                    authRoleVsPermDao.insertBatch(xdtos);
                }
            }
            if (JwtUtil.isPermitted("authRole:edit:menu")) {
                // 2.新增角色菜单关联信息
                if (dto.getMenuIds() != null) {
                    List<AuthRoleVsMenu> xdtos = new ArrayList<>();
                    for (Long menuId : dto.getMenuIds()) {
                        AuthRoleVsMenu authRoleVsMenu = new AuthRoleVsMenu();
                        authRoleVsMenu.setRoleId(entity.getId());
                        authRoleVsMenu.setMenuId(menuId);
                        authRoleVsMenu.setCreateId(dto.getCreateId());
                        xdtos.add(authRoleVsMenu);
                    }
                    authRoleVsMenuDao.insertBatch(xdtos);
                }
            }
            if (JwtUtil.isPermitted("authRole:edit:app")) {
                // 2.新增角色应用关联信息
                if (dto.getAppIds() != null) {
                    List<AuthRoleVsApp> xdtos = new ArrayList<>();
                    for (String appId : dto.getAppIds()) {
                        AuthRoleVsApp authRoleVsApp = new AuthRoleVsApp();
                        authRoleVsApp.setRoleId(entity.getId());
                        authRoleVsApp.setAppId(appId);
                        authRoleVsApp.setCreateId(dto.getCreateId());
                        xdtos.add(authRoleVsApp);
                    }
                    authRoleVsAppDao.insertBatch(xdtos);
                }
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String deleteData(@RequestBody AuthRoleDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authRoleDao.deleteByPrimaryKey(copyTo(dto,AuthRole.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String deleteDataById(@RequestBody AuthRoleDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authRoleDao.deleteById(copyTo(dto,AuthRole.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public PageInfo findDataIsPage(@RequestBody AuthRoleDto dto) {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            AuthRole entity = copyTo(dto, AuthRole.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = authRoleDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), AuthRoleDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
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

    public List<AuthPermDto> findPermIsList(@RequestBody AuthRoleDto dto) {
        List<AuthPermDto> results = null;
        try {
            AuthRoleVsPerm authRoleVsPerm=new AuthRoleVsPerm();
            authRoleVsPerm.setRoleId(dto.getId());
            results = copyTo(authRoleVsPermDao.findPermIsList(authRoleVsPerm),AuthPermDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public List<SysMenuDto> findMenuIsList(@RequestBody AuthRoleDto dto) {
        List<SysMenuDto> results = null;
        try {
            AuthRoleVsMenu authRoleVsMenu=new AuthRoleVsMenu();
            authRoleVsMenu.setRoleId(dto.getId());
            results = copyTo(authRoleVsMenuDao.findMenuIsList(authRoleVsMenu),SysMenuDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public List<SysAppDto> findAppIsList(@RequestBody AuthRoleDto dto) {
        List<SysAppDto> results = null;
        try {
            AuthRoleVsApp authRoleVsApp=new AuthRoleVsApp();
            authRoleVsApp.setRoleId(dto.getId());
            results = copyTo(authRoleVsAppDao.findAppIsList(authRoleVsApp),SysAppDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
}