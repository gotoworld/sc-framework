package com.hsd.account.staff.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.auth.IAuthPermService;
import com.hsd.account.staff.dao.auth.IAuthPermDao;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.entity.auth.AuthPerm;
import com.hsd.framework.NodeTree;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IdUtil;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class AuthPermService extends BaseService implements IAuthPermService {
    @Autowired
    private IAuthPermDao authPermDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody AuthPermDto dto) throws Exception {
        Response result = new Response(0,"success");
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            AuthPerm entity=copyTo(dto,AuthPerm.class);
            if (authPermDao.isDataYN(entity) != 0) { //判断数据是否存在
                //数据存在
                authPermDao.update(entity);
            } else {
                //新增
                if (ValidatorUtil.isEmpty(entity.getId())) {
                    entity.setId(IdUtil.createUUID(22));
                }
                authPermDao.insert(entity);
                result.data=entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String deleteData(@RequestBody AuthPermDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authPermDao.deleteByPrimaryKey(copyTo(dto,AuthPerm.class));
        } catch (Exception e) {
            log.error("信息删除失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String deleteDataById(@RequestBody AuthPermDto dto) throws Exception {
        String result = "success";
        try {
            if (dto == null) throw new RuntimeException("参数对象不能为null");
            authPermDao.deleteById(copyTo(dto,AuthPerm.class));
        } catch (Exception e) {
            log.error("信息删除失败", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    public PageInfo findDataIsPage(@RequestBody AuthPermDto dto) throws Exception {
        PageInfo pageInfo=null;
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            AuthPerm entity = copyTo(dto, AuthPerm.class);
            PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
            List list = authPermDao.findDataIsPage(entity);
            pageInfo=new PageInfo(list);
            pageInfo.setList(copyTo(pageInfo.getList(), AuthPermDto.class));
        } catch (Exception e) {
            log.error("信息[分页]查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return pageInfo;
    }

    public List<AuthPermDto> findDataIsList(@RequestBody AuthPermDto dto) {
        List<AuthPermDto> results = null;
        try {
            results = copyTo(authPermDao.findDataIsList(copyTo(dto,AuthPerm.class)),AuthPermDto.class);
        } catch (Exception e) {
            log.error("信息[列表]查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }

    public AuthPermDto findDataById(@RequestBody AuthPermDto dto) {
        AuthPermDto result = null;
        try {
            result = copyTo(authPermDao.selectByPrimaryKey(copyTo(dto,AuthPerm.class)),AuthPermDto.class);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }

    public String recoveryDataById(@RequestBody AuthPermDto dto) throws Exception {
        String result = "success";
            try {
                if (dto == null) throw new RuntimeException("参数对象不能为null");
                authPermDao.recoveryDataById(copyTo(dto,AuthPerm.class));
            } catch (Exception e) {
                log.error("信息恢复失败!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
        return result;
    }

    public List<AuthPermDto> findDataIsTree(@RequestBody(required = false) AuthPermDto dto) {
        try {
            List<AuthPermDto> results = findDataIsList(dto);
            if (results == null)  return null;
            NodeTree<AuthPermDto> tree = new NodeTree(results,"id","parentId","nodes");
            return tree.buildTree();
        } catch (Exception e) {
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
    }

    public List<AuthPermDto> findPermDataIsListByRoleId(Map dto) {
        List<AuthPermDto> results = null;
        try {
            results = (List<AuthPermDto>) authPermDao.findPermDataIsListByRoleId(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return results;
    }
}