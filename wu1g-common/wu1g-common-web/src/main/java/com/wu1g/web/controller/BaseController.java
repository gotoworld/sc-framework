package com.wu1g.web.controller;


import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IpUtils;
import com.wu1g.framework.util.ReflectUtil;
import com.wu1g.vo.org.OrgUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
        this.request.setAttribute("basePath", getBasePath());
        this.request.setAttribute("ctxPath", request.getContextPath());
    }

    /***
     * 返回上次访问链接
     *
     * @return
     */
    public String getBackPath() {
        return request.getHeader("referer");
    }

    /***
     * 输出
     *
     * @return
     * @throws IOException
     */
    public PrintWriter getWriter() throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        return response.getWriter();
    }

    /**
     * 获取用户IP
     *
     * @return
     */
    public String getIpAddr() {
        return IpUtils.getIpAddr(request);
    }

    public String getBasePath() {
        String path = request.getContextPath();
        String basePath = null;
        // log.info("访问端口号："+ request.getServerPort());
        if (request.getServerPort() != 80) {
            basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        } else {
            basePath = request.getScheme() + "://" + request.getServerName() + path;
        }
        return basePath+"/";
    }
    /**
     * 权限验证框架取得
     *
     * @return 权限验证框架
     */
    public Subject getAuth() {
        return SecurityUtils.getSubject();
    }


    public OrgUser getUser(){
        return (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
    }

    public Integer getPageSize(Object obj){
        String pageNum = (String) ReflectUtil.getValueByFieldName(obj,"pageNum");
        return pageNum!=null?Integer.parseInt(pageNum):1;
    }
}