package com.hsd.account.staff.service.org;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.org.IOrgInfoService;
import com.hsd.account.staff.dao.org.IOrgInfoDao;
import com.hsd.account.staff.dao.org.IOrgOrgVsRoleDao;
import com.hsd.account.staff.dao.org.IOrgOrgVsStaffDao;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsRoleDto;
import com.hsd.account.staff.dto.org.OrgOrgVsStaffDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.account.staff.entity.org.OrgInfo;
import com.hsd.account.staff.entity.org.OrgOrgVsRole;
import com.hsd.account.staff.entity.org.OrgOrgVsStaff;
import com.hsd.framework.NodeTree;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.RequiresPermissions;
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
import java.util.List;

@FeignService
@Slf4j
public class OrgInfoService extends BaseService implements IOrgInfoService {
    @Autowired
    private IOrgInfoDao orgInfoDao;
    @Autowired
    private IOrgOrgVsStaffDao orgOrgVsStaffDao;
    @Autowired
    private IOrgOrgVsRoleDao orgOrgVsRoleDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgInfoDto dto) throws Exception {
        Response result = new Response(0,"success");
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
        String result = "success";
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
        String result = "success";
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
    public OrgInfoDto findDataByCode(@RequestBody OrgInfoDto dto) {
        OrgInfoDto result = null;
        try {
            result = copyTo(orgInfoDao.selectByCode(copyTo(dto,OrgInfo.class)),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("信息详情获取失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String recoveryDataById(@RequestBody OrgInfoDto dto) throws Exception {
        String result = "success";
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
    public List<OrgStaffDto> findOrgStaffIsList(@RequestBody OrgOrgVsStaffDto dto) {
        List<OrgStaffDto> results = null;
        try {
            results = copyTo(orgOrgVsStaffDao.findOrgStaffIsList(copyTo(dto,OrgOrgVsStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("获取组织->人员 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    @RfAccount2Bean
    @RequiresPermissions("orgInfo:edit:staff")
    public Response addStaff(@RequestBody OrgOrgVsStaffDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgOrgVsStaffDao.insert(copyTo(dto,OrgOrgVsStaff.class));
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgInfo:edit:staff")
    public Response delStaff(@RequestBody OrgOrgVsStaffDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgOrgVsStaffDao.deleteByPrimaryKey(copyTo(dto,OrgOrgVsStaff.class));
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public List<AuthRoleDto> findOrgRoleIsList(@RequestBody OrgInfoDto dto) {
        List<AuthRoleDto> results = null;
        try {
            OrgOrgVsRole orgVsStaff=new OrgOrgVsRole();
            orgVsStaff.setOrgId(dto.getId());
            results = copyTo(orgOrgVsRoleDao.findOrgRoleIsList(orgVsStaff),AuthRoleDto.class);
        } catch (Exception e) {
            log.error("获取组织->角色 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    @RfAccount2Bean
    @RequiresPermissions("orgInfo:edit:role")
    public Response addRole(@RequestBody OrgOrgVsRoleDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgOrgVsRoleDao.insert(copyTo(dto,OrgOrgVsRole.class));
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("orgInfo:edit:role")
    public Response delRole(@RequestBody OrgOrgVsRoleDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgOrgVsRoleDao.deleteByPrimaryKey(copyTo(dto,OrgOrgVsRole.class));
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("orgInfo:edit:setManager")
    public Response setManager(@RequestBody OrgInfoDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getManager())) throw new RuntimeException("参数对象不能为null");
            orgInfoDao.setManager(copyTo(dto,OrgInfo.class));
        } catch (Exception e) {
            log.error("部门负责人设置失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public List<OrgStaffDto> findOrgStaffByCodeIsList(@RequestBody OrgOrgVsStaffDto dto) {
        List<OrgStaffDto> results = null;
        try {
            results = copyTo(orgOrgVsStaffDao.findOrgStaffByCodeIsList(copyTo(dto,OrgOrgVsStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("获取组织->人员 根据组织编码 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public String findOrgLevel(@RequestBody OrgOrgVsStaffDto dto) {
        String result = null;
        try {
            result = orgOrgVsStaffDao.findOrgLevel(copyTo(dto,OrgOrgVsStaff.class));
        } catch (Exception e) {
            log.error("获取组织内人员职级 根据组织编码 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public List<OrgInfoDto> findChildDataIsList(@RequestBody OrgInfoDto dto) {
        List<OrgInfoDto> results = null;
        try {
            OrgInfo orgInfo= (OrgInfo) orgInfoDao.getDataByPCode(new OrgInfo(){{setCode(dto.getCode());}});
            if(orgInfo!=null){
                results = new ArrayList<>();
            }
            int count=0;
            while (orgInfo!=null){
                results.add(copyTo(orgInfo,OrgInfoDto.class));

                OrgInfo entity=new OrgInfo();
                entity.setCode(orgInfo.getCode());
                orgInfo= (OrgInfo) orgInfoDao.getDataByPCode(entity);

                count++;
                if(count>100){
                    throw new RuntimeException("疑似递归死循环!");
                }
            }
            if(orgInfo!=null) results.add(copyTo(orgInfo,OrgInfoDto.class));

        } catch (Exception e) {
            log.error("获取下级组织->传入公司或部门的code 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public List<OrgInfoDto> findOrgChildStaffIsList(@RequestBody OrgInfoDto dto) {
        List<OrgInfoDto> results = null;
        try {
            List<Long> childIdArr=new ArrayList<>();
            Long orgId=orgInfoDao.getIdByPCode(copyTo(dto,OrgInfo.class));
            if(orgId!=null) childIdArr.add(orgId);
            List<OrgInfoDto> dtos=findChildDataIsList(dto);
            if(dtos!=null){
                dtos.forEach(orgDto->{
                    childIdArr.add(orgDto.getId());
                });
            }
            results = copyTo(orgOrgVsStaffDao.findOrgChildStaffIsList(childIdArr),OrgInfoDto.class);
        } catch (Exception e) {
            log.error("获取组织级下及组织内人员->传入公司或部门的code 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
}