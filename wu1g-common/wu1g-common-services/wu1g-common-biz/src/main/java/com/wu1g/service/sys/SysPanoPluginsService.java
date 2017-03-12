package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysPanoPluginsService;
import com.wu1g.dao.sys.ISysPanoPluginsDao;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysPanoPlugins;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysPanoPluginsService extends BaseService implements ISysPanoPluginsService {
    @Autowired
    private ISysPanoPluginsDao sysPanoPluginsDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysPanoPlugins bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysPanoPluginsDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysPanoPluginsDao.update(bean);
                } else {
                    sysPanoPluginsDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(SysPanoPlugins bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysPanoPluginsDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysPanoPlugins bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysPanoPluginsDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysPanoPlugins> findDataIsPage(SysPanoPlugins bean) {
        List<SysPanoPlugins> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<SysPanoPlugins>) sysPanoPluginsDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<SysPanoPlugins> findDataIsList(SysPanoPlugins bean) {
        List<SysPanoPlugins> beans = null;
        try {
            beans = (List<SysPanoPlugins>) sysPanoPluginsDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public SysPanoPlugins findDataById(SysPanoPlugins bean) {
        SysPanoPlugins bean1 = null;
        try {
            bean1 = (SysPanoPlugins) sysPanoPluginsDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysPanoPlugins bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysPanoPluginsDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}

