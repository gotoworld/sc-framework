package com.hsd.framework.aspect.auth;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresPermissions;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.util.WebUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
public class RequiresPermissionsAspect {
    //切点
    @Pointcut("@annotation(com.hsd.framework.annotation.auth.RequiresPermissions)")
    public void requiresPermissionsAcpect() {
    }

    /**
     * 功能权限 判断
     */
    @Around("requiresPermissionsAcpect() && target(requiresPermissions)")
    public void doAround(ProceedingJoinPoint pjp, RequiresPermissions requiresPermissions) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        final String authorizationToken = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
        if (ValidatorUtil.isEmpty(authorizationToken)) {
            //经过JwtInterceptor过滤 理论情况不会进入这里
            WebUtil.sendJson(response, Response.error(403, "签名验证失败!" + "token头缺失"));
            return;
        }
        final Claims claims = JwtUtil.parseJWT(authorizationToken);
        JSONObject jobj = JSON.parseObject(claims.getSubject());
        String authorizationInfoPerms = jobj.getString("authorizationInfoPerms");
        boolean flag = false;
        String[] permStrArr = requiresPermissions.value();
        if (authorizationInfoPerms != null) {
            List<String> userPermsArr = Arrays.asList(authorizationInfoPerms.split(","));
            if (userPermsArr != null) {
                if (Logical.OR.equals(requiresPermissions.logical())) {
                    flag = false;
                    for (int i = 0; i < permStrArr.length; i++) {
                        if (userPermsArr.contains(permStrArr[i])) {
                            flag = true;
                            break;
                        }
                    }
                } else {
                    flag = true;
                    for (int i = 0; i < permStrArr.length; i++) {
                        if (!(userPermsArr.contains(permStrArr[i]))) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }
        if (flag) {
            pjp.proceed();
        } else {
            WebUtil.sendJson(response, Response.error(503, "权限不足!"));
            return;
        }
    }
}