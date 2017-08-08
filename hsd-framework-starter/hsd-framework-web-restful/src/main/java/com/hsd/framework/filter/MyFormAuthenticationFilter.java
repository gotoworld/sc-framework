package com.hsd.framework.filter;

import com.hsd.framework.Response;
import com.hsd.framework.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.自定义角色鉴权过滤器(满足其中一个角色则认证通过) 2.扩展异步请求认证提示功能;
 */
@Slf4j
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 表示当访问拒绝时
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                return super.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            Subject subject = getSubject(request, response);
            if (subject.getPrincipal() == null || WebUtil.isAjaxRequest(httpRequest)) {
                WebUtil.sendJson(httpResponse, Response.error(403, "您尚未登录或登录时间过长,请重新登录!"));
            } else {
                super.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }
//    extends AuthorizationFilter
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        Subject subject = getSubject(request, response);
//
//        if (subject.getPrincipal() == null) {
//            if (WebUtil.isAjaxRequest(httpRequest)) {
//                WebUtil.sendJson(httpResponse, Response.error(403, "您尚未登录或登录时间过长,请重新登录!"));
//            } else {
//                saveRequestAndRedirectToLogin(request, response);
//            }
//        } else {
//            if (WebUtil.isAjaxRequest(httpRequest)) {
//                WebUtil.sendJson(httpResponse, Response.error(403, "您没有足够的权限执行该操作!"));
//            } else {
//                String unauthorizedUrl = getUnauthorizedUrl();
//                if (StringUtils.hasText(unauthorizedUrl)) {
//                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
//                } else {
//                    WebUtils.toHttp(response).sendError(401);
//                }
//            }
//        }
//        return false;
//    }
}