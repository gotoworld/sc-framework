package com.wu1g.web.controller.member;

import com.wu1g.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>会员页面首页 action
 */
@Controller("MemberHomeController")
@RequestMapping(value = "/m")
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
        return "member/index";
    }

    /**
     * <p>首页
     */
    @RequestMapping(method={RequestMethod.GET},value = "/home")
    public String home() throws Exception {
        log.info("HomeController home");
        return "member/home";
    }
}
