package com.vr.service.sys;

import com.github.pagehelper.PageHelper;
import com.vr.framework.annotation.FeignService;
import com.vr.framework.service.BaseService;
import com.vr.framework.util.CommonConstant;
import com.vr.api.sys.ISysUserLogService;
import com.vr.dao.sys.ISysUserLogDao;
import com.vr.vo.sys.SysUserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FeignService
@Slf4j
public class SysUserLogService extends BaseService implements ISysUserLogService {

    @Autowired
    private ISysUserLogDao sysUserLogDao;
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public String saveOrUpdateData(SysUserLog bean) throws Exception {
        String result = "seccuss";
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
                result = "信息保存失败!";
                log.error(result, e);
                throw new RuntimeException(result);
            }
        }
        return result;
    }
    public List<SysUserLog> findDataIsPage(SysUserLog bean) {
        List<SysUserLog> results = null;
        try {
            PageHelper.startPage(PN(bean.getPageNum()), PS( bean.getPageSize()));
            results = (List<SysUserLog>) sysUserLogDao.findDataIsPage(bean);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
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
            log.error("操作日志信息保存失败!", e);
        }
    }
}