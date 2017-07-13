package com.hsd.account.service.org;

import com.github.pagehelper.PageHelper;
import com.hsd.account.api.org.IOrgDeptService;
import com.hsd.account.vo.org.OrgDept;
import com.hsd.dao.account.org.IOrgDeptDao;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.annotation.RfAccount2Bean;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.vo.NodeTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FeignService
@Slf4j
public class OrgDeptService extends BaseService implements IOrgDeptService {
    @Autowired
    private IOrgDeptDao orgDepartmentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(OrgDept bean) throws Exception {
        Response result = new Response(0,"seccuss");
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (orgDepartmentDao.isDataYN(bean) != 0) {
                    // 数据存在
                    orgDepartmentDao.update(bean);
                } else {
                    // 新增
                    orgDepartmentDao.insert(bean);
                    result.data=bean.getId();
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @RfAccount2Bean
    public String deleteData(OrgDept bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(OrgDept bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.deleteById(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<OrgDept> findDataIsPage(OrgDept bean) {
        List<OrgDept> results = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            results = (List<OrgDept>) orgDepartmentDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<OrgDept> findDataIsList(OrgDept bean) {
        List<OrgDept> results = null;
        try {
            results = (List<OrgDept>) orgDepartmentDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public OrgDept findDataById(OrgDept bean) {
        OrgDept result = null;
        try {
            result = (OrgDept) orgDepartmentDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(OrgDept bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                orgDepartmentDao.recoveryDataById(bean);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<OrgDept> findDataTree(OrgDept bean) {
        List<OrgDept> results = findDataIsList(bean);
        if (results == null) {
            return null;
        }
        NodeTree<OrgDept> tree = new NodeTree(results, "id", "parentId", "nodes");
        return tree.buildTree();
    }
}