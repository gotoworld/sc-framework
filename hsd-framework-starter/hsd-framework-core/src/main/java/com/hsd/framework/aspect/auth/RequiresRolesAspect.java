package com.hsd.framework.aspect.auth;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.IDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.auth.Logical;
import com.hsd.framework.annotation.auth.RequiresRoles;
import com.hsd.framework.util.*;
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

@Aspect
@Component
@Slf4j
public class RequiresRolesAspect {
    //切点
    @Pointcut("@annotation(com.hsd.framework.annotation.auth.RequiresRoles)")
    public void requiresPermissionsAcpect() {
    }

    /**
     * 角色权限 判断
     */
    @Around("requiresPermissionsAcpect() && @annotation(requiresRoles)")
    public Object doAround(ProceedingJoinPoint pjp, RequiresRoles requiresRoles) throws Throwable {
        Object result = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String authorizationToken = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
        if (ValidatorUtil.isEmpty(authorizationToken)) {
            //-------第二类情况----服务层直接相互调用--------
            Object[] objArr = pjp.getArgs();
            if (objArr != null) {
                for (Object obj : objArr) {
                    if (obj instanceof IDto) {
                        authorizationToken = "" + ReflectUtil.getValueByFieldName(obj, CommonConstant.JWT_HEADER_TOKEN_KEY);
                        break;
                    }
                }
            }
        }
        if (ValidatorUtil.isEmpty(authorizationToken)) {
            //经过JwtInterceptor过滤 理论情况不会进入这里
            WebUtil.sendJson(response, Response.error(403, "签名验证失败!" + "token头缺失"));
            return null;
        }
        final Claims claims = JwtUtil.parseJWT(authorizationToken);
        JSONObject jobj = JSON.parseObject(claims.getSubject());
        JSONArray authorizationInfoRoles = jobj.getJSONArray("authorizationInfoRoles");
        boolean flag = false;
        String[] permStrArr = requiresRoles.value();
        if (authorizationInfoRoles != null) {
            if (Logical.OR.equals(requiresRoles.logical())) {
                flag = false;
                for (int i = 0; i < permStrArr.length; i++) {
                    if (authorizationInfoRoles.contains(permStrArr[i])) {
                        flag = true;
                        break;
                    }
                }
            } else {
                flag = true;
                for (int i = 0; i < permStrArr.length; i++) {
                    if (!(authorizationInfoRoles.contains(permStrArr[i]))) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag) {
            result=pjp.proceed();
        } else {
            WebUtil.sendJson(response, Response.error(503, "权限不足!"));
        }
        return result;
    }
}