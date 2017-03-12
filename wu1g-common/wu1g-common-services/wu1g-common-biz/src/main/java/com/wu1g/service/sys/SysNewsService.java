package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysNewsService;
import com.wu1g.dao.sys.ISysNewsDao;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysNews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysNewsService extends BaseService implements ISysNewsService {
    @Autowired
    private ISysNewsDao sysNewsDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysNews bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysNewsDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysNewsDao.update(bean);
                } else {
                    sysNewsDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(SysNews bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysNewsDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysNews bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysNewsDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysNews> findDataIsPage(SysNews bean) {
        List<SysNews> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<SysNews>) sysNewsDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<SysNews> findDataIsList(SysNews bean) {
        List<SysNews> beans = null;
        try {
            beans = (List<SysNews>) sysNewsDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public SysNews findDataById(SysNews bean) {
        SysNews bean1 = null;
        try {
            bean1 = (SysNews) sysNewsDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysNews bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysNewsDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}

