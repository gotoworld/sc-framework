package com.hsd.service.sys;

import com.github.pagehelper.PageHelper;
import com.hsd.api.sys.ISysVariableService;
import com.hsd.dao.sys.ISysVariableDao;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.vo.NodeTree;
import com.hsd.vo.sys.SysVariable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>数据字典 业务处理实现类。
 */
@FeignService
@Slf4j
public class SysVariableService extends BaseService implements ISysVariableService {
    /**
     * 数据字典 Dao接口类
     */
    @Autowired
    private ISysVariableDao sysVariableDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysVariable bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysVariableDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysVariableDao.update(bean);
                } else {
                    sysVariableDao.insert(bean);
                }
            } catch (Exception e) {
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public String deleteData(SysVariable bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                sysVariableDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysVariable bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                sysVariableDao.deleteById(bean);
            } catch (Exception e) {
                result = "信息删除失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<SysVariable> findDataIsPage(SysVariable bean) {
        List<SysVariable> results = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS(bean.getPageSize()));
            results = (List<SysVariable>) sysVariableDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public List<SysVariable> findDataIsList(SysVariable bean) {
        List<SysVariable> results = null;
        try {
            results = (List<SysVariable>) sysVariableDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }

    public SysVariable findDataById(SysVariable bean) {
        SysVariable result = null;
        try {
            result = (SysVariable) sysVariableDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return result;
    }

    public String recoveryDataById(SysVariable bean) throws Exception {
        String result = "seccuss";
        if (bean != null) {
            try {
                sysVariableDao.recoveryDataById(bean);
            } catch (Exception e) {
                result = "信息恢复失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }

    public List<SysVariable> findDataTree(SysVariable bean) {
        List<SysVariable> results = findDataIsList(bean);
        if (results == null) {
            return null;
        }
        NodeTree<SysVariable> tree = new NodeTree(results, "id", "parentId", "nodes");
        return tree.buildTree();
    }
}
