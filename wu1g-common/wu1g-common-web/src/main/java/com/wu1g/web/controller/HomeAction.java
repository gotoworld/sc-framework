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
package com.wu1g.web.controller;

import com.wu1g.framework.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>后台页面首页 action</p>
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class HomeAction extends BaseController {

    private static final long serialVersionUID = -6103432072290645133L;

    /**
     * 默认的构造函数
     */
    public HomeAction() {
        log.info("HomeAction constructed");
    }

    /**
     * <p>初始化
     */
    @RequestMapping(value = "/index")
    public String init() throws Exception {
        log.info("HomeAction init");
        return "admin/index";
    }

    /**
     * <p>首页
     */
    @RequestMapping(value = "/home")
    public String home() throws Exception {
        log.info("HomeAction home");
        return "admin/home";
    }
}
