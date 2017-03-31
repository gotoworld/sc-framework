package com.wu1g.service.org;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.org.IOrgDeptService;
import com.wu1g.dao.org.IOrgDeptDao;
import com.wu1g.framework.annotation.RfAccount2Bean;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.NodeTree;
import com.wu1g.vo.org.OrgDept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrgDeptService extends BaseService implements IOrgDeptService {
    @Autowired
    private IOrgDeptDao orgDepartmentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String saveOrUpdateData(OrgDept bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (orgDepartmentDao.isDataYN(bean) != 0) {
                    // 数据存在
                    orgDepartmentDao.update(bean);
                } else {
                    // 新增
                    orgDepartmentDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    @RfAccount2Bean
    public String deleteData(OrgDept bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(OrgDept bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<OrgDept> findDataIsPage(OrgDept bean) {
        List<OrgDept> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<OrgDept>) orgDepartmentDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public List<OrgDept> findDataIsList(OrgDept bean) {
        List<OrgDept> beans = null;
        try {
            beans = (List<OrgDept>) orgDepartmentDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public OrgDept findDataById(OrgDept bean) {
        OrgDept bean1 = null;
        try {
            bean1 = (OrgDept) orgDepartmentDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return bean1;
    }

    public String recoveryDataById(OrgDept bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<OrgDept> findDataTree(OrgDept bean) {
        List<OrgDept> beans = findDataIsList(bean);
        if (beans == null) {
            return null;
        }
        NodeTree<OrgDept> tree = new NodeTree(beans, "id", "parentId", "beans");
        return tree.buildTree();
    }
}