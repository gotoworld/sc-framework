package com.wu1g.framework.web.controller;

import com.wu1g.framework.util.CommonConstant;

import javax.servlet.http.HttpServletRequest;



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
