package com.hsd.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

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
    @Value("${zuul.cross.origin}")
    private String accessControlAllowOrigin;
    @Value("${zuul.cross.methods}")
    private String accessControlAllowMethods;
    @Value("${zuul.cross.headers}")
    private String accessControlAllowHeaders;
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletRequest alteredRequest = ((HttpServletRequest) request);
            HttpServletResponse alteredResponse = ((HttpServletResponse) response);
            addHeadersFor200Response(alteredRequest,alteredResponse);
            addHeadersCookieForAuthorization(alteredRequest,alteredResponse);
            chain.doFilter(request, response);
        }
    }

    private void addHeadersFor200Response(HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin",accessControlAllowOrigin);
        response.addHeader("Access-Control-Allow-Methods", accessControlAllowMethods);
        response.setHeader("Access-Control-Allow-Headers", accessControlAllowHeaders);
    }
    private void addHeadersCookieForAuthorization(HttpServletRequest request,HttpServletResponse response) {
        String authorization=request.getHeader("Authorization");
        if(authorization!=null && !"".equals(authorization)){
//            response.setHeader("X-Cache",DigestUtils.md5Hex(authorization ));
        }
    }
}