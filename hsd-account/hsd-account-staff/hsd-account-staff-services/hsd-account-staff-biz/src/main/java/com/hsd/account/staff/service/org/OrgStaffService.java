package com.hsd.account.staff.service.org;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.org.IOrgStaffService;
import com.hsd.account.staff.dao.auth.IAuthRoleDao;
import com.hsd.account.staff.dao.auth.IAuthStaffVsRoleDao;
import com.hsd.account.staff.dao.org.IOrgInfoDao;
import com.hsd.account.staff.dao.org.IOrgOrgVsStaffDao;
import com.hsd.account.staff.dao.org.IOrgStaffDao;
import com.hsd.account.staff.dao.sys.ISysVariableDao;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.AuthStaffVsRoleDto;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsStaffDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.account.staff.entity.auth.AuthRole;
import com.hsd.account.staff.entity.auth.AuthStaffVsRole;
import com.hsd.account.staff.entity.org.OrgInfo;
import com.hsd.account.staff.entity.org.OrgOrgVsStaff;
import com.hsd.account.staff.entity.org.OrgStaff;
import com.hsd.common.util.excel.ExcelUtil;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.security.MD5;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IdUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class OrgStaffService extends BaseService implements IOrgStaffService {
    @Autowired
    private IOrgStaffDao orgStaffDao;
    @Autowired
    private IAuthRoleDao authRoleDao;
    @Autowired
    private IAuthStaffVsRoleDao authStaffVsRoleDao;
    @Autowired
    private IOrgInfoDao orgInfoDao;
    @Autowired
    private IOrgOrgVsStaffDao orgOrgVsStaffDao;
    @Autowired
    private ISysVariableDao sysVariableDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgStaffDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgStaff entity=copyTo(dto,OrgStaff.class);
            // 判断数据是否存在
            if (orgStaffDao.isDataYN(entity) != 0) {
                // 数据存在
                orgStaffDao.update(entity);
            } else {
                if(!JwtUtil.isPermitted("orgStaff:add")){
                    throw new RuntimeException("没用新增员工的权限!");
                }
                if(orgStaffDao.isAccountYN(dto.getAccount())>0){
                    throw new RuntimeException("账号已存在!");
                }
                entity.setPwd(MD5.pwdMd5Hex(entity.getPwd()));
                // 新增
                orgStaffDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response insert(@RequestBody OrgStaffDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgStaff entity=copyTo(dto,OrgStaff.class);
            // 判断数据是否存在
            if (orgStaffDao.isDataYN(entity) != 0) {
                // 数据存在
            } else {
                if(!JwtUtil.isPermitted(dto,"orgStaff:add")){
                    throw new RuntimeException("没用新增员工的权限!");
                }
                if(orgStaffDao.isAccountYN(dto.getAccount())>0){
                    throw new RuntimeException("账号已存在!");
                }
                entity.setPwd(MD5.pwdMd5Hex(entity.getPwd()));
                // 新增
                orgStaffDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String updateData(@RequestBody OrgStaffDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgStaff entity=copyTo(dto,OrgStaff.class);
            // 判断数据是否存在
            if (orgStaffDao.isDataYN(entity) != 0) {
                // 数据存在
                orgStaffDao.update(entity);
            }
        } catch (Exception e) {
            log.error("信息更新失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public String deleteData(@RequestBody OrgStaffDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgStaffDao.deleteByPrimaryKey(copyTo(dto,OrgStaff.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(@RequestBody OrgStaffDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgStaffDao.deleteById(copyTo(dto,OrgStaff.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Override
    public PageInfo findDataIsPage(@RequestBody OrgStaffDto dto) {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            OrgStaff entity = copyTo(dto, OrgStaff.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = orgStaffDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), OrgStaffDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }
    public List<OrgStaffDto> findDataIsList(@RequestBody OrgStaffDto dto) {
        List<OrgStaffDto> results = null;
        try {
            results = copyTo(orgStaffDao.findDataIsList(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    public List<OrgStaffDto> findStaffAndOrgDataIsList(@RequestBody OrgStaffDto dto) {
        List<OrgStaffDto> results = null;
        try {
            results = copyTo(orgStaffDao.findStaffAndOrgDataIsList(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("获取用户及用户所在组织 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public OrgStaffDto findDataById(@RequestBody OrgStaffDto dto) {
        OrgStaffDto result = null;
        try {
            result = copyTo(orgStaffDao.selectByPrimaryKey(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public OrgStaffDto findDataByAccount(@RequestBody OrgStaffDto dto) {
        OrgStaffDto result = null;
        try {
            result = copyTo(orgStaffDao.findDataByAccount(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public String recoveryDataById(@RequestBody OrgStaffDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgStaffDao.recoveryDataById(copyTo(dto,OrgStaff.class));
        } catch (Exception e) {
            result = "信息恢复失败!";
            log.error(result, e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public List<OrgInfoDto> findOrgIsList(@RequestBody OrgStaffDto dto) {
        List<OrgInfoDto> results = null;
        try {
            if (dto != null) {
                Map entity = new HashMap();
                entity.put("staffId", dto.getId());
                results = copyTo(orgInfoDao.getOrgListByStaffId(entity),OrgInfoDto.class);
            }
        } catch (Exception e) {
            log.error("获取员工组织集合,数据库处理异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public String isAccountYN(@RequestParam(name ="account") String account) {
        String result = "success";
        try {
            if (ValidatorUtil.notEmpty(account)) {
                if (orgStaffDao.isAccountYN(account) > 0) {
                    throw new RuntimeException("账号["+account+"]已存在!");
                }
            }
        } catch (Exception e) {
            log.error("判断员工id是否存在,数据库处理异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String updatePwd(@RequestBody OrgStaffDto dto) {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            OrgStaffDto orgStaffDto=copyTo(orgStaffDao.selectUserPwdByPrimaryKey(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
            if (orgStaffDto==null) throw new RuntimeException("员工不存在!");

            orgStaffDto.setOldpwd(MD5.pwdMd5Hex(dto.getOldpwd()));
//            orgStaffDto.setNewpwd(MD5.pwdMd5Hex(dto.getNewpwd()));
            orgStaffDto.setConfirmpwd(MD5.pwdMd5Hex(dto.getConfirmpwd()));

            if(!orgStaffDto.getPwd().equals(orgStaffDto.getOldpwd())) throw new RuntimeException("原密码错误!");
            if(orgStaffDao.updatePwd(copyTo(orgStaffDto,OrgStaff.class))==0) throw new RuntimeException("密码修改失败,请重试!");
        } catch (Exception e) {
            log.error("员工修改密码异常!",e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String resetPwd(@RequestBody OrgStaffDto dto) {
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            String newPwd=IdUtil.createUUID(8);
            dto.setPwd(MD5.pwdMd5Hex(MD5.md5Hex(newPwd)));
            if(orgStaffDao.resetPwd(copyTo(dto,OrgStaff.class))==0) throw new RuntimeException("密码重置失败,请重试!");
            //TODO 给账号发送新密码短信

            return newPwd;
        } catch (Exception e) {
            log.error("密码重置失败!",e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
    }
    @Override
    public PageInfo findBriefDataIsPage(@RequestBody OrgStaffDto dto) {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            OrgStaff entity = copyTo(dto, OrgStaff.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = orgStaffDao.findBriefDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), OrgStaffDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }

    @RfAccount2Bean
    @RequiresPermissions("orgStaff:edit:org")
    public Response addOrg(@RequestBody OrgOrgVsStaffDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgOrgVsStaffDao.insert(copyTo(dto,OrgOrgVsStaff.class));
        } catch (Exception e) {
            log.error("信息添加失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @RequiresPermissions("orgStaff:edit:org")
    public Response delOrg(@RequestBody OrgOrgVsStaffDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");

            OrgOrgVsStaff orgOrgVsStaff=copyTo(dto,OrgOrgVsStaff.class);
            if(ValidatorUtil.notEmpty(dto.getStaffAccount()) && ValidatorUtil.notEmpty(dto.getOrgCode())){
                //根据account获取staffId
                orgOrgVsStaff.setStaffId(orgStaffDao.getIdbyAcccount(dto.getStaffAccount()));
                //根据orgCode获取orgId
                orgOrgVsStaff.setOrgId(orgInfoDao.getIdByPCode(new OrgInfo(){{setCode(dto.getOrgCode());}}));
            }
            orgOrgVsStaffDao.deleteByPrimaryKey(orgOrgVsStaff);
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    //    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RequiresPermissions("orgStaff:add:batch")
    public Response addBatch(@RequestParam(name = "fileUrl") String fileUrl) {
        Response result = new Response(0,"success");
        try {
            if (fileUrl == null) throw new RuntimeException("文件路径不存在!");
            Map<String,List> map= ExcelUtil.readExcelIsList(fileUrl,true);
            if(map==null)  throw new RuntimeException("excel读取失败!");
            List titles=map.get("titles");
            List datas=map.get("datas");
            log.info("excel->title->"+titles);
            Long createId=JwtUtil.getSubject().getLong("id");
            final StringBuffer finalMessage = new StringBuffer("");
            if(datas!=null) {
                List<OrgStaff> orgStaffs=new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    List data= (List) datas.get(i);
                    OrgStaff orgStaff=new OrgStaff();
                    if(ValidatorUtil.isEmpty(data.get(0))){
                        finalMessage.append("<br/>空行:"+(i+1));
                        continue;
                    }
                    orgStaff.setJobNo((String) data.get(0));//工号
                    orgStaff.setAccount((String) data.get(1));//账号
                    orgStaff.setPwd(MD5.pwdMd5Hex(MD5.md5Hex((String) data.get(2))) );//密码
                    orgStaff.setName((String) data.get(3));//姓名
                    orgStaff.setGender(getGender((String) data.get(4)));//性别
                    orgStaff.setCellphone((String) data.get(5));//手机号
                    orgStaff.setEmail((String) data.get(6));//邮箱
                    orgStaff.setMemo("批量导入");
//                    orgStaff.setOrderNo(i);
                    orgStaff.setType(0);
                    orgStaff.setState(0);
                    orgStaff.setCreateId(createId);
                    orgStaffs.add(orgStaff);

//                    if((i+1)%100==0||(i+1)==datas.size()){
//                        try {
//                            orgStaffDao.insertBatch(orgStaffs);
//                        } catch (Exception e) {
                            orgStaffs.forEach(ou -> {
                                try {
                                    try {
                                        ou.setLevel(sysVariableDao.getCodeByVariableName(new AuthRole(){{setName((String) data.get(9));}}));//职级 9
                                    } catch (Exception e) {
                                        log.error(ou.getAccount()+"员工批量导入-异常:职级,"+e.getMessage());
                                    }
                                    try {
                                        ou.setLeadership(orgStaffDao.getIdbyAcccount((String) data.get(10)));//上级领导 10
                                    } catch (Exception e) {
                                        log.error(ou.getAccount()+"员工批量导入-异常:上级领导,"+e.getMessage());
                                    }
                                    long staffId = orgStaffDao.insert(ou);
                                    try {
                                        //部门 7
                                        long orgId=orgInfoDao.getIdByName(new OrgInfo(){{setName((String) data.get(7));}});//根据部门名称获取部门id
                                        orgOrgVsStaffDao.insert(new OrgOrgVsStaff(){{setStaffId(staffId);setOrgId(orgId);}});
                                    } catch (Exception e) {
                                        log.error(ou.getAccount()+"员工批量导入-异常:部门,"+e.getMessage());
                                    }
                                    try {
                                        //岗位 8
                                        long roleId=authRoleDao.getIdByName(new AuthRole(){{setName((String) data.get(8));}});
                                        authStaffVsRoleDao.insert(new AuthStaffVsRole(){{setStaffId(staffId);setRoleId(roleId);}});
                                    } catch (Exception e) {
                                        log.error(ou.getAccount()+"员工批量导入-异常:岗位,"+e.getMessage());
                                    }
                                } catch (Exception e1) {
                                    String msg=""+e1.getMessage();
                                    int indexOf=e1.getMessage().indexOf("for key");
                                    finalMessage.append("<br/>异常:"+(indexOf!=-1?msg.substring(0,indexOf):msg));
                                    finalMessage.append("<br/>==>"+ou.getAccount()+","+ou.getName()+","+ou.getCellphone());
                                }
                            });
//                        }
                        orgStaffs=new ArrayList<>();
//                    }
                }
            }
            result.code=500;
            result.message=finalMessage.toString();
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    private int getGender(String gender){
        int val=3;
        switch (gender){
            case "男":val=0;break;
            case "女":val=1;break;
            case "保密":val=2;break;
        }
        return val;
    }

    @Override
    public List<AuthRoleDto> findStaffRoleIsList(@RequestBody OrgStaffDto dto) {
        List<AuthRoleDto> results = null;
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            AuthStaffVsRole staffVsRole=new AuthStaffVsRole();
            staffVsRole.setStaffId(dto.getId());
            results = copyTo(authStaffVsRoleDao.findStaffRoleIsList(staffVsRole),AuthRoleDto.class);

        } catch (Exception e) {
            log.error("获取员工->个人角色。异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    @Override
    public List<AuthRoleDto> findOrgRoleIsList(@RequestBody OrgStaffDto dto) {
        List<AuthRoleDto> results = null;
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            AuthStaffVsRole staffVsRole=new AuthStaffVsRole();
            staffVsRole.setStaffId(dto.getId());
            results = copyTo(authStaffVsRoleDao.findOrgRoleIsList(staffVsRole),AuthRoleDto.class);

        } catch (Exception e) {
            log.error("获取员工->组织角色。异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    @RfAccount2Bean
    @RequiresPermissions("orgStaff:edit:role")
    public Response addRole(@RequestBody AuthStaffVsRoleDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authStaffVsRoleDao.insert(copyTo(dto,AuthStaffVsRole.class));
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @RequiresPermissions("orgStaff:edit:role")
    public Response delRole(@RequestBody AuthStaffVsRoleDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authStaffVsRoleDao.deleteByPrimaryKey(copyTo(dto,AuthStaffVsRole.class));
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    public List<OrgStaffDto> findStaffByRoleIsList(@RequestBody AuthStaffVsRoleDto dto) {
        List<OrgStaffDto> results = null;
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            AuthStaffVsRole staffVsRole=new AuthStaffVsRole();
            staffVsRole.setRoleId(dto.getRoleId());
            results = copyTo(authStaffVsRoleDao.findStaffByRoleIsList(staffVsRole),OrgStaffDto.class);

        } catch (Exception e) {
            log.error("根据角色id->获取用户列表。异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    @RequiresPermissions("orgStaff:edit:leadership")
    public Response setLeadership(@RequestBody OrgStaffDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgStaffDao.setLeadership(copyTo(dto,OrgStaff.class));
        } catch (Exception e) {
            log.error("上级领导设置失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public Response getLeadership(@RequestBody OrgStaffDto dto) {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            orgStaffDao.getLeadership(copyTo(dto,OrgStaff.class));
        } catch (Exception e) {
            log.error("获取领导设置失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public OrgStaffDto getStaffByStaffIdAndleadershipLevel(@RequestBody OrgStaffDto dto) {
        OrgStaffDto result = null;
        try {
            result = copyTo(orgStaffDao.getStaffByStaffIdAndleadershipLevel(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("获取员工-根据员工和上级级别 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    public List<OrgStaffDto> getStaffLeadershipAll(@RequestBody OrgStaffDto dto) {
        List<OrgStaffDto> results = null;
        try {
            results = copyTo(orgStaffDao.getStaffLeadershipAll(copyTo(dto,OrgStaff.class)),OrgStaffDto.class);
        } catch (Exception e) {
            log.error("获取员工-所有上级 异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
    //获取最大员工号的员工信息
    public List<String> getMaxJobNo() {
        List<String> results = null;
        try{
            results = orgStaffDao.findMaxJobNo();
        }catch (Exception e) {
            log.error("获取最大员工号 异常！", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
}