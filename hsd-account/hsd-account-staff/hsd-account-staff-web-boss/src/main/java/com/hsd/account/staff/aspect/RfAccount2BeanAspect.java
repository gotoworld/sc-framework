package com.hsd.account.staff.aspect;

import com.alibaba.fastjson.JSON;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.framework.IDto;
import com.hsd.framework.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切点类
 */
@Aspect
@Component
@Slf4j
public class RfAccount2BeanAspect {
    //切点
    @Pointcut("@annotation(com.hsd.framework.annotation.RfAccount2Bean)")
    public void account2BeanAspect() {
    }

    /**
     * 用于 将当前操作人员的信息写入实体对象
     */
    @Before("account2BeanAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //读取session中的用户
        OrgUserDto user = (OrgUserDto) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
        if(user==null){
            final String authorizationToken = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
            log.info("authHeader=" + authorizationToken);
            if (ValidatorUtil.isEmpty(authorizationToken)) {
                throw new SignatureException("token头缺失");
            }
            final Claims claims = JwtUtil.parseJWT(authorizationToken);
            user=JSON.parseObject(claims.getSubject(), OrgUserDto.class);
        }
        //请求的IP
        String ip = IpUtil.getIpAddr(request);
        try {
            if(log.isDebugEnabled()) {
                //*========控制台输出=========*//
                log.debug("=====前置通知开始=====");
                log.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
                log.debug("请求人:" + user.getName());
                log.debug("请求IP:" + ip);
            }
            Object[] objArr=joinPoint.getArgs();
            if(objArr!=null){
                for(Object obj:objArr){
                    if(obj instanceof IDto){
//                      System.out.printf(JSON.toJSONString(obj));
                        ReflectUtil.setValueByFieldName2(obj,"createId", user.getId());//创建者id
//                        ReflectUtil.setValueByFieldName2(obj,"account", orgUser.getAccount());//用户id
//                        ReflectUtil.setValueByFieldName2(obj,"createIp",ip);//创建者ip
//                        ReflectUtil.setValueByFieldName2(obj,"updateId",orgUser.getId());//修改者id
//                        ReflectUtil.setValueByFieldName2(obj,"updateIp",ip);//修改者ip
                        break;
                    }
                }
            }
            log.debug("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", e.getMessage());
        }
    }
}
