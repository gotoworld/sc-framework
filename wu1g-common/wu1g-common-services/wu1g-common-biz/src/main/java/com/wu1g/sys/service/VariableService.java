package com.wu1g.sys.service;

import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IdUtil;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.sys.api.IVariableService;
import com.wu1g.sys.dao.ISysVariableDao;
import com.wu1g.sys.vo.SysVariable;
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
@Service
@Slf4j
public class VariableService implements IVariableService {
    /**
     * 数据字典 Dao接口类
     */
    @Autowired
    private ISysVariableDao SysVariableDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysVariable bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (SysVariableDao.isDataYN(bean) != 0) {
                    // 数据存在
                    SysVariableDao.updateByPrimaryKeySelective(bean);
                } else {
                    // 新增
                    if (ValidatorUtil.isEmpty(bean.getId())) {
                        bean.setId(IdUtil.createUUID(32));
                    }
                    SysVariableDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
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
                SysVariableDao.deleteByPrimaryKey(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
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
                SysVariableDao.deleteById(bean);
            } catch (Exception e) {
                msg = "信息删除失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }

    public List<SysVariable> findDataIsPage(SysVariable bean) {
        List<SysVariable> beans = null;
        try {
            beans = (List<SysVariable>) SysVariableDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public List<SysVariable> findDataIsList(SysVariable bean) {
        List<SysVariable> beans = null;
        try {
            beans = (List<SysVariable>) SysVariableDao.findDataIsList(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }

    public SysVariable findDataById(SysVariable bean) {
        SysVariable bean1 = null;
        try {
            bean1 = (SysVariable) SysVariableDao.selectByPrimaryKey(bean);
            // if(bean1!=null && ValidatorUtil.notEmpty(bean1.getDetailInfo())){
            // bean1.setDetailInfo(IOHelper.readHtml(bean1.getDetailInfo()));
            // }
        } catch (Exception e) {
            log.error("信息详情查询失败,数据库错误!", e);
        }
        return bean1;
    }

    public String recoveryDataById(SysVariable bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                SysVariableDao.recoveryDataById(bean);
            } catch (Exception e) {
                msg = "信息恢复失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
}