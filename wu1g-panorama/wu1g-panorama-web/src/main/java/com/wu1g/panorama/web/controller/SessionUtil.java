package com.wu1g.panorama.web.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.baseos.common.CommonConstant;


public class SessionUtil {
	/**
	 * 后台用户退出登录或者清空Session
	 * @param req
	 */
	public static void clearAdminSession(HttpServletRequest req) {
    	req.getSession().removeAttribute(CommonConstant.SESSION_KEY_USER);
    	req.getSession().invalidate();
	}
}
