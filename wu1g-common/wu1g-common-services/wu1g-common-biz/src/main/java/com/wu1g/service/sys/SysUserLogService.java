package com.wu1g.service.sys;

import com.github.pagehelper.PageHelper;
import com.wu1g.framework.service.BaseService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.api.sys.ISysUserLogService;
import com.wu1g.dao.sys.ISysUserLogDao;
import com.wu1g.vo.sys.SysUserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysUserLogService extends BaseService implements ISysUserLogService {

    @Autowired
    private ISysUserLogDao sysUserLogDao;
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysUserLog bean) throws Exception {
        String msg = "seccuss";
        if (bean != null) {
            try {
                // 判断数据是否存在
                if (sysUserLogDao.isDataYN(bean) != 0) {
                    // 数据存在
                    sysUserLogDao.update(bean);
                } else {
                    // 新增
                    sysUserLogDao.insert(bean);
                }
            } catch (Exception e) {
                msg = "信息保存失败,数据库处理错误!";
                log.error(msg, e);
                throw new Exception(msg);
            }
        }
        return msg;
    }
    public List<SysUserLog> findDataIsPage(SysUserLog bean) {
        List<SysUserLog> beans = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            beans = (List<SysUserLog>) sysUserLogDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败,数据库错误!", e);
        }
        return beans;
    }
    public void info(String type, String memo,String detailInfo, Long userId, String userName, String ip) {
        try {
            SysUserLog dto = new SysUserLog();
            dto.setType(type);// 操作类型(a增d删u改q查)
            dto.setMemo(memo);// 描述
            dto.setDetailInfo(detailInfo);// 具体
            dto.setCreateId(userId);// 操作人id
            dto.setCreateName(userName);// 操作人姓名
            dto.setCreateIp(ip);// 建立者IP
            // 新增
            sysUserLogDao.insert(dto);
        } catch (Exception e) {
            log.error("操作日志信息保存失败,数据库处理错误!", e);
        }
    }
}