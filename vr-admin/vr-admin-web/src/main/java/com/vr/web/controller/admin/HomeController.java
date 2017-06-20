/*
 * 后台页面首页
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.16  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.vr.web.controller.admin;

import com.vr.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>后台页面首页 action
 */
@Controller("AdminHomeController")
@RequestMapping(value = "/h")
@Slf4j
public class HomeController extends BaseController {

    private static final long serialVersionUID = -6103432072290645133L;

    /**
     * 默认的构造函数
     */
    public HomeController() {
        log.info("HomeController constructed");
    }

    /**
     * <p>初始化
     */
    @RequestMapping(method={RequestMethod.GET},value = "/index")
    public String init() throws Exception {
        log.info("HomeController init");
        return "admin/index";
    }

    /**
     * <p>首页
     */
    @RequestMapping(method={RequestMethod.GET},value = "/home")
    public String home() throws Exception {
        log.info("HomeController home");
        return "admin/home";
    }
}
