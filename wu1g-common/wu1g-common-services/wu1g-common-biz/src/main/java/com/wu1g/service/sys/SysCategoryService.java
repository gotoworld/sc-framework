package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysCategoryService;
import com.wu1g.dao.sys.ISysCategoryDao;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class SysCategoryService extends BaseService implements ISysCategoryService {
    @Autowired
    private ISysCategoryDao sysCategoryDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysCategory bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysCategoryDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysCategoryDao.update(bean);
                } else {
                    sysCategoryDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(SysCategory bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysCategoryDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysCategory bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysCategoryDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysCategory> findDataIsPage(SysCategory bean) {
        List<SysCategory> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<SysCategory>) sysCategoryDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<SysCategory> findDataIsList(SysCategory bean) {
        List<SysCategory> beans = null;
        try {
            beans = (List<SysCategory>) sysCategoryDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public SysCategory findDataById(SysCategory bean) {
        SysCategory bean1 = null;
        try {
            bean1 = (SysCategory) sysCategoryDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysCategory bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysCategoryDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}

