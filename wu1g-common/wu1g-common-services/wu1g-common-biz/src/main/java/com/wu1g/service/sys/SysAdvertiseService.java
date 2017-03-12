package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysAdvertiseService;
import com.wu1g.dao.sys.ISysAdvertiseDao;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysAdvertise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class SysAdvertiseService extends BaseService implements ISysAdvertiseService {
    @Autowired
    private ISysAdvertiseDao sysAdvertiseDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysAdvertise bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysAdvertiseDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysAdvertiseDao.update(bean);
                } else {
                    sysAdvertiseDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(SysAdvertise bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysAdvertiseDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysAdvertise bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysAdvertiseDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysAdvertise> findDataIsPage(SysAdvertise bean) {
        List<SysAdvertise> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<SysAdvertise>) sysAdvertiseDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<SysAdvertise> findDataIsList(SysAdvertise bean) {
        List<SysAdvertise> beans = null;
        try {
            beans = (List<SysAdvertise>) sysAdvertiseDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public SysAdvertise findDataById(SysAdvertise bean) {
        SysAdvertise bean1 = null;
        try {
            bean1 = (SysAdvertise) sysAdvertiseDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysAdvertise bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysAdvertiseDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}

