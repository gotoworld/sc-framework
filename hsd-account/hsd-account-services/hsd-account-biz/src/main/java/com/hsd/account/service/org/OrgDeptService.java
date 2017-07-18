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
import com.hsd.framework.NodeTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class OrgDeptService extends BaseService implements IOrgDeptService {
    @Autowired
    private IOrgDeptDao orgDepartmentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public Response saveOrUpdateData(@RequestBody OrgDept dto) throws Exception {
        Response result = new Response(0,"seccuss");
        if (dto != null) {
            try {
                // 判断数据是否存在
                if (orgDepartmentDao.isDataYN(dto) != 0) {
                    // 数据存在
                    orgDepartmentDao.update(dto);
                } else {
                    // 新增
                    orgDepartmentDao.insert(dto);
                    result.data=dto.getId();
                }
            } catch (Exception e) {
                log.error("信息保存失败!", e);
                throw new RuntimeException("信息保存失败!");
            }
        }
        return result;
    }

    @RfAccount2Bean
    public String deleteData(@RequestBody OrgDept dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgDepartmentDao.deleteByPrimaryKey(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    @RfAccount2Bean
    public String deleteDataById(@RequestBody OrgDept dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgDepartmentDao.deleteById(dto);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<OrgDept> findDataIsPage(@RequestBody OrgDept dto) {
        List<OrgDept> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = (List<OrgDept>) orgDepartmentDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<OrgDept> findDataIsList(@RequestBody OrgDept dto) {
        List<OrgDept> results = null;
        try {
            results = (List<OrgDept>) orgDepartmentDao.findDataIsList(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public OrgDept findDataById(@RequestBody OrgDept dto) {
        OrgDept result = null;
        try {
            result = (OrgDept) orgDepartmentDao.selectByPrimaryKey(dto);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(@RequestBody OrgDept dto) throws Exception {
        String result = "seccuss";
        if (dto != null) {
            try {
                orgDepartmentDao.recoveryDataById(dto);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<OrgDept> findDataTree(@RequestBody(required = false) OrgDept dto) {
        List<OrgDept> results = findDataIsList(dto);
        if (results == null) {
            return null;
        }
        NodeTree<OrgDept> tree = new NodeTree(results, "id", "parentId", "nodes");
        return tree.buildTree();
    }
}