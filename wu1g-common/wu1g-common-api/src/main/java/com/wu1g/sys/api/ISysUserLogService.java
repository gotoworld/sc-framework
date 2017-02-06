/*	
 * 系统_管理员操作日志   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.sys.api;

import com.wu1g.sys.vo.SysUserLog;

import java.util.List;

/**
 * <p>系统_管理员操作日志   业务处理接口类。
 */
public interface ISysUserLogService {

    /**
     * 管理员操作日志记录。
     *
     * @param type   操作类型
     * @param memo   具体描述
     * @param userId 用户id
     * @param ip     用户ip
     * @return
     */
    public void info(String type, String memo, String userId, String ip);

    /**
     * <p>信息列表 分页。
     */
    public List<SysUserLog> findDataIsPage(SysUserLog bean);
}