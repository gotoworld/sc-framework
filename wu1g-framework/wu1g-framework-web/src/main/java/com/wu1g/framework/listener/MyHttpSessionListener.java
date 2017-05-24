/*
 *  监听Session的创建与销毁
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016-3-25  wu1g    程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p> 监听Session的创建与销毁
 */
@Slf4j
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("createSession....." + se.getSession().getId());
        ServletContext context = se.getSession().getServletContext();
        Integer onlineNumber = (Integer) context.getAttribute("onlineNumber");
        if (onlineNumber == null) {
            onlineNumber = new Integer(1);
        } else {
            int co = onlineNumber.intValue();
            onlineNumber = new Integer(co + 1);
        }
        System.out.println("当前用户人数：" + onlineNumber);
        context.setAttribute("onlineNumber", onlineNumber);// 保存人数
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("deleteSession....." + se.getSession().getId());
        ServletContext context = se.getSession().getServletContext();
        Integer onlineNumber = (Integer) context.getAttribute("onlineNumber");
        int co = onlineNumber.intValue();
        onlineNumber = new Integer(co - 1);
        context.setAttribute("onlineNumber", onlineNumber);
        System.out.println("当前用户人数：" + onlineNumber);
    }
}
