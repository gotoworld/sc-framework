package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.api.sys.ISysMessageService;
import com.wu1g.dao.sys.ISysMessageDao;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.sys.SysMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysMessageService extends BaseService implements ISysMessageService {
    @Autowired
    private ISysMessageDao sysMessageDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysMessage bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysMessageDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysMessageDao.update(bean);
                } else {
                    sysMessageDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public String deleteData(SysMessage bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysMessageDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
            }
        }
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String deleteDataById(SysMessage bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysMessageDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysMessage> findDataIsPage(SysMessage bean) {
        List<SysMessage> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<SysMessage>) sysMessageDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public List<SysMessage> findDataIsList(SysMessage bean) {
        List<SysMessage> beans = null;
        try {
            beans = (List<SysMessage>) sysMessageDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return beans;
    }

    public SysMessage findDataById(SysMessage bean) {
        SysMessage bean1 = null;
        try {
            bean1 = (SysMessage) sysMessageDao.selectByPrimaryKey(bean);
        } catch (Exception e) {
            log.error("信息详情查询失败!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysMessage bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                sysMessageDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}

