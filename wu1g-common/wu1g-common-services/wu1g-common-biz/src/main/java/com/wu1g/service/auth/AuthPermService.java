package com.wu1g.service.auth;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.auth.IAuthPermService;
import com.wu1g.dao.auth.IAuthPermDao;
import com.wu1g.vo.auth.AuthPerm;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthPermService extends BaseService implements IAuthPermService {
    @Autowired
    private IAuthPermDao authPermDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String saveOrUpdateData(AuthPerm bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
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
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(AuthPerm bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
                dto.setId(bean.getId());//权限id
                authPermDao.deleteByPrimaryKey(dto);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {
            Exception.class, RuntimeException.class})
    public String deleteDataById(AuthPerm bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
                dto.setId(bean.getId());//权限id
                authPermDao.deleteById(dto);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<AuthPerm> findDataIsPage(AuthPerm bean) {
        List<AuthPerm> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<AuthPerm>) authPermDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<AuthPerm> findDataIsList(AuthPerm bean) {
        List<AuthPerm> beans = null;
        try {
            beans = (List<AuthPerm>) authPermDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public AuthPerm findDataById(AuthPerm bean) {
        AuthPerm bean1 = null;
        try {
            bean1 = (AuthPerm) authPermDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(AuthPerm bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                AuthPerm dto = new AuthPerm();
                dto.setId(bean.getId());//权限id
                authPermDao.recoveryDataById(dto);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<AuthPerm> findDataTree(AuthPerm bean) {
        List<AuthPerm> beans = findDataIsList(bean);
        if (beans == null) {
            return null;
        }
        AuthPermTree tree = new AuthPermTree(beans);
        return tree.buildTree();
    }

    public List<AuthPerm> findPermDataIsListByRoleId(Map dto) {
        List<AuthPerm> beans = null;
        try {
            beans = (List<AuthPerm>) authPermDao.findPermDataIsListByRoleId(dto);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }
}

class AuthPermTree {
    private List<AuthPerm> new_nodes = new ArrayList<AuthPerm>();
    private List<AuthPerm> nodes;

    public AuthPermTree(List<AuthPerm> nodes) {
        this.nodes = nodes;
    }

    public List<AuthPerm> buildTree() {
        for (AuthPerm node : nodes) {
            if (ValidatorUtil.isNullEmpty(node.getParentId())) {
                new_nodes.add(node);
                build(node);
            }
        }
        return new_nodes;
    }

    private void build(AuthPerm node) {
        List<AuthPerm> children = getChildren(node);
        if (!children.isEmpty()) {
            if (node.getBeans() == null) {
                node.setBeans(new ArrayList());
            }
            for (AuthPerm child : children) {
                node.getBeans().add(child);
                build(child);
            }
        }
    }

    private List<AuthPerm> getChildren(AuthPerm node) {
        List<AuthPerm> children = new ArrayList<AuthPerm>();
        String id = node.getId();
        for (AuthPerm child : nodes) {
            if (id.equals(child.getParentId())) {
                children.add(child);
            }
        }
        return children;
    }
}