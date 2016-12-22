/*
 * 基础Manager类接口
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2012.04.17  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 robots  System. - All Rights Reserved.
 *
 */
package com.wu1g.service;

import java.util.Observable;

/**
 * 基础Manager类接口
 * 
 * @author {wuxiaogang}
 *
 */
public abstract class BaseManager extends Observable {
	// /** 管理员操作日志 service类 */
	// protected IUserLogsManager userLogsManager;
	//
	// /**
	// * 管理员操作日志 service类取得
	// * @return 管理员操作日志 service类
	// */
	// public IUserLogsManager getUserLogsManager() {
	// return userLogsManager;
	// }
	//
	// /**
	// * 管理员操作日志 service类设定
	// * @param userLogsManager 管理员操作日志 service类
	// */
	// public void setUserLogsManager(IUserLogsManager userLogsManager) {
	// this.userLogsManager = userLogsManager;
	// }
}
