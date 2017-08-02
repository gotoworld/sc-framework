package com.hsd.service.sys;

import com.github.pagehelper.PageHelper;
import com.hsd.api.sys.ISysUserLogService;
import com.hsd.dao.sys.ISysUserLogDao;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.service.BaseService;
import com.hsd.dto.sys.SysUserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignService
@Slf4j
public class SysUserLogService extends BaseService implements ISysUserLogService {

    @Autowired
    private ISysUserLogDao sysUserLogDao;
    public List<SysUserLog> findDataIsPage(@RequestBody SysUserLog dto) {
        List<SysUserLog> results = null;
        try {
            PageHelper.startPage(PN(dto.getPageNum()), PS( dto.getPageSize()));
            results = (List<SysUserLog>) sysUserLogDao.findDataIsPage(dto);
        } catch (Exception e) {
            log.error("信息查询失败!", e);
        }
        return results;
    }
    public void info(@RequestParam("type") String type,
                     @RequestParam("memo")String memo,
                     @RequestParam("detailInfo")String detailInfo,
                     @RequestParam("userId")Long userId,
                     @RequestParam("userName")String userName,
                     @RequestParam("ip")String ip) {
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