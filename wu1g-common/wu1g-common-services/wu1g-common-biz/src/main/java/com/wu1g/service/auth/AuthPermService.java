package com.wu1g.service.auth;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.auth.IAuthPermService;
import com.wu1g.dao.auth.IAuthPermDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.vo.NodeTree;
import com.wu1g.vo.auth.AuthPerm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@FeignService
@Slf4j
public class AuthPermService extends BaseService implements IAuthPermService {
    @Autowired
    private IAuthPermDao authPermDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String saveOrUpdateData(AuthPerm dto) throws Exception {
        String result = "seccuss";
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
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public String deleteData(AuthPerm bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
                dto.setId(bean.getId());//权限id
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
    public String deleteDataById(AuthPerm bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
                dto.setId(bean.getId());//权限id
                authPermDao.deleteById(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthPerm> findDataIsPage(AuthPerm bean) {
        List<AuthPerm> results = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            results = (List<AuthPerm>) authPermDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<AuthPerm> findDataIsList(AuthPerm bean) {
        List<AuthPerm> results = null;
        try {
            results = (List<AuthPerm>) authPermDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public AuthPerm findDataById(AuthPerm bean) {
        AuthPerm result = null;
        try {
            result = (AuthPerm) authPermDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(AuthPerm bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
                dto.setId(bean.getId());//权限id
                authPermDao.recoveryDataById(dto);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<AuthPerm> findDataTree(AuthPerm bean) {
        List<AuthPerm> results = findDataIsList(bean);
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