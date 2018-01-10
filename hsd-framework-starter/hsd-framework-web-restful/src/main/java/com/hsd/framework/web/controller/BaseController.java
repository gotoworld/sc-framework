package com.hsd.framework.web.controller;


import com.github.pagehelper.PageInfo;
import com.hsd.framework.ObjectCopy;
import com.hsd.framework.PageUtil;
import com.hsd.framework.PageDto;
import com.hsd.framework.util.IpUtil;
import com.hsd.framework.util.ReflectUtil;
import com.hsd.framework.util.ValidatorUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
     */
    public String getIpAddr() {
        return IpUtil.getIpAddr(request);
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
        return basePath + "/";
    }

    public Integer getPageNum(Object obj) {
        Object pageNum = ReflectUtil.getValueByFieldName(obj, "pageNum");
        return ValidatorUtil.notEmpty("" + pageNum) ? Integer.parseInt("" + pageNum) : 1;
    }

    public PageDto getPageDto(PageInfo pageInfo) {
        return PageUtil.copy(pageInfo);
    }

    public <T> T copyTo(Object obj,Class<T> toObj) throws Exception {
        if(obj==null){
            return null;
        }
        return  ObjectCopy.copyTo(obj, toObj);
    }
    public <T> List<T> copyTo(List from, Class<T> to){
        if(from==null){
            return null;
        }
        return  ObjectCopy.copyTo(from, to);
    }
}