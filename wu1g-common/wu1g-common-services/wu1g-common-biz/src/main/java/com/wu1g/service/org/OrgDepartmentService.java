package com.wu1g.service.org;

import com.github.pagehelper.PageHelper;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.api.org.IOrgDepartmentService;
import com.wu1g.dao.org.IOrgDepartmentDao;
import com.wu1g.vo.org.OrgDepartment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrgDepartmentService extends BaseService implements IOrgDepartmentService {
    @Autowired
    private IOrgDepartmentDao orgDepartmentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String saveOrUpdateData(OrgDepartment bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (orgDepartmentDao.isDataYN(bean) != 0) {
                    // 数据存在
                    orgDepartmentDao.updateByPrimaryKeySelective(bean);
                } else {
                    // 新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(32));
                    }
                    orgDepartmentDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    @RfAccount2Bean
    public String deleteData(OrgDepartment bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(OrgDepartment bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<OrgDepartment> findDataIsPage(OrgDepartment bean) {
        List<OrgDepartment> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<OrgDepartment>) orgDepartmentDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<OrgDepartment> findDataIsList(OrgDepartment bean) {
        List<OrgDepartment> beans = null;
        try {
            beans = (List<OrgDepartment>) orgDepartmentDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public OrgDepartment findDataById(OrgDepartment bean) {
        OrgDepartment bean1 = null;
        try {
            bean1 = (OrgDepartment) orgDepartmentDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(OrgDepartment bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<OrgDepartment> findDataTree(OrgDepartment bean) {
        List<OrgDepartment> beans = findDataIsList(bean);
        if (beans == null) {
            return null;
        }
        OrgDepartmentBeanTree tree = new OrgDepartmentBeanTree(beans);
        return tree.buildTree();
    }
}

class OrgDepartmentBeanTree {
    private List<OrgDepartment> new_nodes = new ArrayList<OrgDepartment>();
    private List<OrgDepartment> nodes;

    public OrgDepartmentBeanTree(List<OrgDepartment> nodes) {
        this.nodes = nodes;
    }

    public List<OrgDepartment> buildTree() {
        for (OrgDepartment node : nodes) {
            // String id = node.getCode();
            if (ValidatorUtil.isNullEmpty(node.getParentId())) {
                new_nodes.add(node);
                build(node);
            }
        }
        return new_nodes;
    }

    private void build(OrgDepartment node) {
        List<OrgDepartment> children = getChildren(node);
        if (!children.isEmpty()) {
            if (node.getBeans() == null) {
                node.setBeans(new ArrayList());
            }
            for (OrgDepartment child : children) {
                String id = child.getId();
                node.getBeans().add(child);
                build(child);
            }
        }
    }

    private List<OrgDepartment> getChildren(OrgDepartment node) {
        List<OrgDepartment> children = new ArrayList<OrgDepartment>();
        String id = node.getId();
        for (OrgDepartment child : nodes) {
            if (id.equals(child.getParentId())) {
                children.add(child);
            }
        }
        return children;
    }
}