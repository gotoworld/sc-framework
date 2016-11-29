/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016-3-25  wu1g119      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>类功能说明待补充
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    private static final Log log = LogFactory.getLog(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContex初始化");
        log.info("web容器信息:" + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContex销毁");
    }

}
