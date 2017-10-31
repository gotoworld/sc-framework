package com.hsd.framework.interceptor;

import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.util.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //无需授权注解标记 类级+方法级
        if (handlerMethod.getBean().getClass().getAnnotation(NoAuthorize.class) != null || handlerMethod.getMethod().getAnnotation(NoAuthorize.class) != null) {
            if (log.isDebugEnabled()) log.debug("无需登陆可直接访问!" + request.getServletPath());
            return true;
        }
        final String authorizationToken = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
        if (log.isDebugEnabled()) log.debug("authHeader=" + authorizationToken);
        try {
            if (ValidatorUtil.isEmpty(authorizationToken)) {
                throw new SignatureException("头缺失");
            }
            final Claims claims = JwtUtil.parseJWT(authorizationToken);
            if (log.isDebugEnabled()) {
                log.debug("ID: " + claims.getId());
                log.debug("Subject: " + claims.getSubject());
                log.debug("Issuer: " + claims.getIssuer());
                log.debug("Expiration: " + claims.getExpiration());
                JwtUtil.UserType userType =null;
                if(claims.get("userType")!=null) userType = Enum.valueOf(JwtUtil.UserType.class, ""+claims.get("userType"));
                log.debug("UserType: "+userType!=null?userType.getVal():"未知");
            }
            return true;
        } catch (final SignatureException e) {
            WebUtil.sendJson(response,Response.error(403, "签名验证失败!" + e.getMessage()));
        } catch (ExpiredJwtException e) {
            WebUtil.sendJson(response,Response.error(403, "授权过期!" + e.getMessage()));
        } catch (Throwable e) {
            log.error("授权检查异常", e);
            WebUtil.sendJson(response,Response.error(403, "授权检查异常!" + e.getMessage()));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
