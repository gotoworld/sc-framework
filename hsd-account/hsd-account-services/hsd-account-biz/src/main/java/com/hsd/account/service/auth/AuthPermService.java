package com.hsd.account.service.auth;

import com.github.pagehelper.PageHelper;
import com.hsd.account.api.auth.IAuthPermService;
import com.hsd.account.vo.auth.AuthPerm;
import com.hsd.dao.account.auth.IAuthPermDao;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IdUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.NodeTree;
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
    public Response saveOrUpdateData(@RequestBody AuthPerm dto) throws Exception {
        Response result = new Response(0,"seccuss");
        if (dto != null) {
            try {
                //判断数据是否存在
                if (authPermDao.isDataYN(dto) != 0) {
                    //数据存在
                    authPermDao.update(dto);
                } else {
                    //新增
                    if (ValidatorUtil.isEmpty(dto.getId())) {
                        dto.setId(IdUtil.createUUID(22));
                    }
                    authPermDao.insert(dto);
                    result.data=dto.getId();
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    public String deleteData(@RequestBody AuthPerm dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                authPermDao.deleteByPrimaryKey(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String deleteDataById(@RequestBody AuthPerm dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                authPermDao.deleteById(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthPerm> findDataIsPage(@RequestBody AuthPerm dto) {
        List<AuthPerm> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = (List<AuthPerm>) authPermDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<AuthPerm> findDataIsList(@RequestBody AuthPerm dto) {
        List<AuthPerm> results = null;
        try {
            results = (List<AuthPerm>) authPermDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public AuthPerm findDataById(@RequestBody AuthPerm dto) {
        AuthPerm result = null;
        try {
            result = (AuthPerm) authPermDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(@RequestBody AuthPerm dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                authPermDao.recoveryDataById(dto);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthPerm> findDataTree(@RequestBody AuthPerm dto) {
        List<AuthPerm> results = findDataIsList(dto);
        if (results == null) {
            return null;
        }
        NodeTree<AuthPerm> tree = new NodeTree(results,"id","parentId","nodes");
        return tree.buildTree();
    }

    public List<AuthPerm> findPermDataIsListByRoleId(Map dto) {
        List<AuthPerm> results = null;
        try {
            results = (List<AuthPerm>) authPermDao.findPermDataIsListByRoleId(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }
}