package com.hsd.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "crossFilter", urlPatterns = {"/boss/*","/api/*","/file/*"})
@Slf4j
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("===============设置“跨域”访问头=============");
    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletRequest alteredRequest = ((HttpServletRequest) request);
            HttpServletResponse alteredResponse = ((HttpServletResponse) response);
            addHeadersFor200Response(alteredRequest,alteredResponse);
            chain.doFilter(request, response);
        }
    }

    private void addHeadersFor200Response(HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Authorization,Cache-Control,Content-Language,Content-Type,Expires,Last-Modified,Pragma");
    }

}