/*
 * 基础Service类接口
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Observable;

/**
 * <p>
 * service 基类 放置service通用资源或公共方法
 * </p>
 * <ol>
 * [提供机能]
 * <li>taskExecutor 线程池
 * <li>jedisHelper 缓存服务
 * <li>alog 系统_管理员操作日志
 * <li>Subject 权限验证
 * </ol>
 */
@Service
public abstract class BaseService extends Observable {
//	/** 线程池 */
//	@Autowired
//	protected TaskExecutor			taskExecutor;
    /**
     * 用户登录id
     */
    protected String uid;
    /**
     * 权限验证框架
     */
    private Subject auth;

    /**
     * 用户登录id取得
     *
     * @return 用户登录id
     */
    public String getUid() {
        if (uid == null) {
            uid = (String) SecurityUtils.getSubject().getPrincipal();
        }
        return uid;
    }

    /**
     * 权限验证框架取得
     *
     * @return 权限验证框架
     */
    public Subject getAuth() {
        if (auth == null) {
            auth = SecurityUtils.getSubject();
        }
        return auth;
    }

    protected Integer PN(Integer pageNum) {
        if (pageNum == null || pageNum == 0) {
            return 1;
        } else {
            return pageNum;
        }
    }

    protected Integer PS(Integer pageSize) {
        if (pageSize == null || pageSize == 0 || pageSize > 50) {//防止被攻击 限制每页数据最大值
            return 15;
        } else {
            return pageSize;
        }
    }
}
