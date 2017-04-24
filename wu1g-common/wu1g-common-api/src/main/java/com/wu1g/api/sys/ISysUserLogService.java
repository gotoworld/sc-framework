/*	
 * 系统_管理员操作日志   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.wu1g.api.sys;

import com.wu1g.vo.sys.SysUserLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>系统_管理员操作日志   业务处理接口类。
 */
@FeignClient(name = "${spring.application.name}")//, fallback = TestServiceHystrix.class)
public interface ISysUserLogService {

    /**
     * 管理员操作日志记录。
     */
    @RequestMapping(value = "/info")
    public void info(String type, String memo,String detailInfo, Long userId, String userName, String ip);
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public List<SysUserLog> findDataIsPage(SysUserLog bean);
}