package com.hsd.account.staff.service.org;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.dao.org.IOrgInfoDao;
import com.hsd.account.staff.dao.org.IOrgOrgVsRoleDao;
import com.hsd.account.staff.dao.org.IOrgOrgVsUserDao;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsUserDto;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.account.staff.entity.org.OrgInfo;
import com.hsd.account.staff.entity.org.OrgOrgVsRole;
import com.hsd.account.staff.entity.org.OrgOrgVsUser;
import com.hsd.framework.NodeTree;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
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
public class OrgInfoService extends BaseService implements IOrgInfoService {
    @Autowired
    private IOrgInfoDao orgInfoDao;
    @Autowired
    private IOrgOrgVsUserDao orgOrgVsUserDao;
    @Autowired
    private IOrgOrgVsRoleDao orgOrgVsRoleDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgInfoDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            dto.setState(dto.getState()==null?0:dto.getState());
            OrgInfo entity=copyTo(dto,OrgInfo.class);
            // 判断数据是否存在
            if (orgInfoDao.isDataYN(entity) != 0) {
                // 数据存在
                orgInfoDao.update(entity);
            } else {
                // 新增
                orgInfoDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @RfAccount2Bean
    public String deleteData(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgInfoDao.deleteByPrimaryKey(copyTo(dto,OrgInfo.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "seccuss";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgInfoDao.deleteById(copyTo(dto,OrgInfo.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public PageInfo findDataIsPage(@RequestBody OrgInfoDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            OrgInfo entity = copyTo(dto, OrgInfo.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = orgInfoDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), OrgInfoDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }
    @Override
    public PageInfo findBriefDataIsPage(@RequestBody OrgInfoDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            OrgInfo entity = copyTo(dto, OrgInfo.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = orgInfoDao.findBriefDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), OrgInfoDto.class));
        } catch (Exception e) {
            log.error("精简信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }

    public List<OrgInfoDto> findDataIsList(@RequestBody OrgInfoDto dto) {
        List<OrgInfoDto> results = null;
        try {
            results = copyTo(orgInfoDao.findDataIsList(copyTo(dto,OrgInfo.class)),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("信息列表获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public OrgInfoDto findDataById(@RequestBody OrgInfoDto dto) {
        OrgInfoDto result = null;
        try {
            result = copyTo(orgInfoDao.selectByPrimaryKey(copyTo(dto,OrgInfo.class)),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("信息详情获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String recoveryDataById(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgInfoDao.recoveryDataById(copyTo(dto,OrgInfo.class));
            } catch (Exception e) {
                log.error("信息恢复失败!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
        }
        return result;
    }

    public List<OrgInfoDto> findDataIsTree(@RequestBody(required = false) OrgInfoDto dto) {
        try {
            List<OrgInfoDto> results = findDataIsList(dto);
            if (results == null) {
                return null;
            }
            NodeTree<OrgInfoDto> tree = new NodeTree(results, "id", "parentId", "nodes");
            return tree.buildTree();
        } catch (Exception e) {
            log.error("信息树获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
    }
    public List<OrgUserDto> findUserIsList(@RequestBody OrgInfoDto dto) {
        List<OrgUserDto> results = null;
        try {
            OrgOrgVsUser orgVsUser=new OrgOrgVsUser();
            orgVsUser.setOrgId(dto.getId());
            results = copyTo(orgOrgVsUserDao.findUserIsList(orgVsUser),OrgUserDto.class);
        } catch (Exception e) {
            log.error("获取组织->人员 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public List<AuthRoleDto> findRoleIsList(@RequestBody OrgInfoDto dto) {
        List<AuthRoleDto> results = null;
        try {
            OrgOrgVsRole orgVsUser=new OrgOrgVsRole();
            orgVsUser.setOrgId(dto.getId());
            results = copyTo(orgOrgVsRoleDao.findRoleIsList(orgVsUser),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("获取组织->角色 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    @RfAccount2Bean
    public Response addUser(@RequestBody OrgOrgVsUserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            if(JwtUtil.isPermitted("orgInfo:edit:user")){
                orgOrgVsUserDao.insert(copyTo(dto,OrgOrgVsUser.class));
            }else{
                throw new RuntimeException("权限不足!");
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public Response delUser(@RequestBody OrgOrgVsUserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            if(JwtUtil.isPermitted("orgInfo:edit:user")){
                orgOrgVsUserDao.deleteByPrimaryKey(copyTo(dto,OrgOrgVsUser.class));
            }else{
                throw new RuntimeException("权限不足!");
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response setRole(@RequestBody OrgInfoDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            if(JwtUtil.isPermitted("orgInfo:edit:role")){
                OrgOrgVsRole orgOrgVsRole = new OrgOrgVsRole();
                orgOrgVsRole.setOrgId(dto.getId());
                // 1.根据组织id清除角色关联表
                orgOrgVsRoleDao.deleteBulkDataByOrgId(orgOrgVsRole);
                // 2.新增组织用户关联信息
                if (dto.getRoleIds() != null) {
                    List<OrgOrgVsRole> xEntityList = new ArrayList<>();
                    for (Long roleId : dto.getRoleIds()) {
                        OrgOrgVsRole orgVsRole = new OrgOrgVsRole();
                        orgVsRole.setOrgId(dto.getId());
                        orgVsRole.setRoleId(roleId);
                        xEntityList.add(orgVsRole);
                    }
                    orgOrgVsRoleDao.insertBatch(xEntityList);
                }
            }else{
                throw new RuntimeException("权限不足!");
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
}