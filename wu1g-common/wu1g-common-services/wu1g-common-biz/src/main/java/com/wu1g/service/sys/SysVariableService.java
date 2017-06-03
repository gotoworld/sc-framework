package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysVariableService;
import com.wu1g.dao.sys.ISysVariableDao;
import com.wu1g.framework.annotation.FeignService;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.NodeTree;
import com.wu1g.vo.sys.SysVariable;
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
        String msg = "seccuss";
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
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(SysVariable bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysVariableDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysVariable bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysVariableDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysVariable> findDataIsPage(SysVariable bean) {
        List<SysVariable> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS(bean.getPageSize()));
            beans = (List<SysVariable>) sysVariableDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public List<SysVariable> findDataIsList(SysVariable bean) {
        List<SysVariable> beans = null;
        try {
            beans = (List<SysVariable>) sysVariableDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public SysVariable findDataById(SysVariable bean) {
        SysVariable bean1 = null;
        try {
            bean1 = (SysVariable) sysVariableDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysVariable bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysVariableDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysVariable> findDataTree(SysVariable bean) {
        List<SysVariable> beans = findDataIsList(bean);
        if (beans == null) {
            return null;
        }
        NodeTree<SysVariable> tree = new NodeTree(beans, "id", "parentId", "nodes");
        return tree.buildTree();
    }
}
