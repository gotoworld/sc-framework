package com.wu1g.framework.web.interceptor;

import com.cddang.framework.SysErrorCode;
import com.cddang.framework.annotation.Authorize;
import com.cddang.framework.annotation.NoAuthorize;
import com.cddang.framework.annotation.NoOneLogin;
import com.cddang.framework.cache.config.CacheConfig;
import com.cddang.framework.cache.redis.RedisCache;
import com.cddang.framework.config.AppConfig;
import com.cddang.framework.dto.AuthUser;
import com.cddang.framework.exception.SysException;
import com.cddang.framework.net.HttpRestClient;
import com.cddang.framework.util.Constracts;
import com.cddang.framework.util.IpUtil;
import com.cddang.framework.web.HttpContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.valves.RemoteAddrValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by leo on 16/6/13.
 * Web 用户认证/登录状态拦截器
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    IpUtil ipUtil;

    private static HttpRestClient httpRestClient = null;
    private static String accUrl = null;

    static {
        httpRestClient = HttpRestClient.getHttpClient("auth-interceptor-http");
        httpRestClient.addHeader("Accept", "application/json");
        httpRestClient.addHeader("Content-Type", "application/x-www-form-urlencoded");

    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String from_ip = HttpContext.getRemoteAddr(request);

        // get user token from request header
        String token = request.getHeader(Constracts.HEADER_TOKEN);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String uri = request.getRequestURI();
        AuthUser user = null;
        boolean isAuthrize = "true".equalsIgnoreCase(AppConfig.getProperty("common.requieAuthrize") ) ;
        log.info("---req from[{}] uri:{}", from_ip, uri);

        if (token != null) {
            int idx = token.lastIndexOf(":");
            if (idx > 0)
                token = token.substring(token.lastIndexOf(":") + 1);
            log.debug("--get user from token={}", token);
            /**获取user*/
            try {
                user = new RedisCache<AuthUser>("cddang-redis").get(Constracts.ACC_KEY_PREFIX + token);
            }catch(Exception e){
                log.debug(",,,,", e);
            }
            if (user == null) {
                /**接口检查token是否合法*/
                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                boolean isCheck = true;
                try {
                    user = httpRestClient.doPost(AppConfig.getProperty("common.checkByToken"), params, AuthUser.class);
                }catch(Exception e) {
                    isCheck = false;
                }

                if (isCheck && user == null) {
                    /**默认为单点登录，检查是否有无需单点登录请求*/
                    Annotation noOneLogin = handlerMethod.getBean().getClass().getAnnotation(NoOneLogin.class);
                    Annotation noOneLogin1 = handlerMethod.getMethod().getAnnotation(NoOneLogin.class);
                    if (!(noOneLogin != null || noOneLogin1 != null)) {
                        throw new SysException(SysErrorCode.ONE_LOGIN);
                    }
                }

            }

        } else {
            if (uri.startsWith("/api/") || uri.startsWith("/serv/")) {
                /**@TODO: 16-11-7  是否服务端访问，进行服务端安全验证*/

                /***/
                if ( (isAuthrize && from_ip != null && ipUtil.isAllowed(from_ip) /*&& (from_ip.startsWith("10.") || from_ip.startsWith("192.168.") || from_ip.startsWith("0.0.") || from_ip.startsWith("101.204.230.251") )*/)
                        || !isAuthrize  ) {
                    log.info("token is null 来自服务器 ip[{}] url {} , isAuthrize={}", from_ip, uri ,isAuthrize);
                    return true;
                }
                log.trace("token is null 访问 ip[{}] url {} ", from_ip, uri );
            } else if (uri.startsWith("/boss/")) {
                try {
                    user = new RedisCache<AuthUser>("cddang-redis").get(Constracts.ACC_KEY_BOSS_PREFIX + request.getSession().getId());
                }catch(Exception e){
                }
            } else {
                //其他
                return true;
            }
        }

        if (user != null) {
            if (user.getAccid() < 0) {
                throw new SysException(SysErrorCode.AUTH_FAIL );
            }
            request.setAttribute(Constracts.REQ_USER, user);
        }
        //Controller级授权
        Annotation classAuthrize = handlerMethod.getBean().getClass().getAnnotation(Authorize.class);
        Annotation classNoLogin = handlerMethod.getBean().getClass().getAnnotation(NoAuthorize.class);

        isAuthrize = (isAuthrize && classNoLogin == null) || classAuthrize != null;

        /**是否需要授权*/
        if (isAuthrize ) {
            /**需授权，则检查是否有无需授权登录的注解*/
            Annotation noLogin1 = handlerMethod.getMethod().getAnnotation(NoAuthorize.class);

            if (noLogin1 != null  ) {
                return true;
            }

            if (user == null) {
                throw new SysException(SysErrorCode.AUTH_FAIL );
            }
        } else {
            /**无需授权，检查Method是否有需求授权*/
            Annotation authorize1 = handlerMethod.getMethod().getAnnotation(Authorize.class);

            if (user == null && authorize1 != null ) {
                throw new SysException(SysErrorCode.AUTH_FAIL );
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
