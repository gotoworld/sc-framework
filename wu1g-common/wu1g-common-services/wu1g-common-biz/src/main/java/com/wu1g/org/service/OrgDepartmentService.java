/*	
 * 组织架构_部门   业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.service;

import com.github.pagehelper.PageHelper;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.org.api.IOrgDepartmentService;
import com.wu1g.org.dao.IOrgDepartmentDao;
import com.wu1g.org.vo.OrgDepartment;
import com.wu1g.sys.api.ISysUserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrgDepartmentService extends BaseService implements IOrgDepartmentService {
    @Autowired
    private IOrgDepartmentDao orgDepartmentDao;

    /**
     * <p>
     * 信息编辑。
     * <p>
     * <ol>
     * [功能概要] <li>新增信息。 <li>修改信息。
     * </ol>
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String saveOrUpdateData(OrgDepartment bean) throws Exception {
        String msg = "1";
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

    /**
     * <p>
     * 信息编辑。
     * <p>
     * <ol>
     * [功能概要] <li>物理删除。
     * </ol>
     */
    @RfAccount2Bean
    public String deleteData(OrgDepartment bean) throws Exception {
        String msg = "1";
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

    /**
     * <p>
     * 信息 单条。
     * <p>
     * <ol>
     * [功能概要] <li>逻辑删除。
     * </ol>
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(OrgDepartment bean) throws Exception {
        String msg = "1";
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

    /**
     * <p>
     * 信息列表 分页。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索。 <li>分页。
     * </ol>
     */
    public List<OrgDepartment> findDataIsPage(OrgDepartment bean) {
        List<OrgDepartment> beans = null;
        try {
            PageHelper.startPage((Integer) bean.getPageNum(), (Integer) bean.getPageSize());
            beans = (List<OrgDepartment>) orgDepartmentDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    /**
     * <p>
     * 信息列表。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索。 <li>列表。
     * </ol>
     */
    public List<OrgDepartment> findDataIsList(OrgDepartment bean) {
        List<OrgDepartment> beans = null;
        try {
            beans = (List<OrgDepartment>) orgDepartmentDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    /**
     * <p>
     * 信息详情。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索。 <li>详情。
     * </ol>
     */
    public OrgDepartment findDataById(OrgDepartment bean) {
        OrgDepartment bean1 = null;
        try {
            bean1 = (OrgDepartment) orgDepartmentDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    /**
     * <p>
     * 信息 单条。
     * <p>
     * <ol>
     * [功能概要] <li>恢复逻辑删除的数据。
     * </ol>
     */
    public String recoveryDataById(OrgDepartment bean) throws Exception {
        String msg = "1";
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

    /**
     * <p>
     * 信息列表。
     * <p>
     * <ol>
     * [功能概要] <li>信息检索 根据xx查询所有圈圈。
     * </ol>
     */
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
            if (ValidatorUtil.isNullEmpty(node.getParentid())) {
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
            if (id.equals(child.getParentid())) {
                children.add(child);
            }
        }
        return children;
    }
}