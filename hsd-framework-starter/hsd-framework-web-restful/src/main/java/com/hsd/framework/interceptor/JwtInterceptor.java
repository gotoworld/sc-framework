package com.hsd.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            log.info("无需登陆可直接访问!" + request.getServletPath());
            return true;
        }
        final String authorizationToken = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
        log.info("authHeader=" + authorizationToken);
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
                log.debug("----当前用户类型----"+userType!=null?userType.getVal():"未知");
            }
            return true;
        } catch (final SignatureException e) {
            responseOutWithJson(response,Response.error(403, "签名验证失败!" + e.getMessage()));
        } catch (ExpiredJwtException e) {
            responseOutWithJson(response,Response.error(403, "授权过期!" + e.getMessage()));
        } catch (Throwable e) {
            log.error("授权检查异常", e);
            responseOutWithJson(response,Response.error(403, "授权检查异常!" + e.getMessage()));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
    /**
     * 以JSON格式输出
     * @param response
     */
    protected void responseOutWithJson(HttpServletResponse response,Object responseObject) {
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(responseObject));
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
