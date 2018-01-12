package com.hsd.account.finance.aspect;

import com.alibaba.fastjson.JSONObject;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.framework.IDto;
import com.hsd.framework.util.IpUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ReflectUtil;
import com.hsd.framework.util.ValidatorUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
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
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的IP
        String ip = IpUtil.getIpAddr(request);
        try {
            Object[] objArr = joinPoint.getArgs();
            if (objArr != null) {
                for (Object obj : objArr) {
                    if (obj instanceof IDto) {
                        //读取session中的员工
                        OrgStaffDto dto = JwtUtil.getSubject(obj, OrgStaffDto.class);
                        if (log.isDebugEnabled()) {
                            //*========控制台输出=========*//
                            log.debug("=====前置通知开始=====");
                            log.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
                            log.debug("请求人:" + dto.getName());
                            log.debug("请求IP:" + ip);
                        }
//                      System.out.printf(JSON.toJSONString(obj));
                        ReflectUtil.setValueByFieldName(obj, "createId", dto.getId());//创建者id
                        if(ValidatorUtil.isEmpty(ReflectUtil.getValueByFieldName(obj,"account")))
                            ReflectUtil.setValueByFieldName(obj, "account", dto.getAccount());
                        if(ValidatorUtil.isEmpty(ReflectUtil.getValueByFieldName(obj,"staffId")))
                            ReflectUtil.setValueByFieldName(obj, "staffId", dto.getId());
                        if(ValidatorUtil.isEmpty(ReflectUtil.getValueByFieldName(obj,"staffName")))
                         ReflectUtil.setValueByFieldName(obj, "staffName",dto.getName());
                        if(ValidatorUtil.isEmpty(ReflectUtil.getValueByFieldName(obj,"appStaffId")))
                            ReflectUtil.setValueByFieldName(obj, "appStaffId", dto.getAppStaffId());
                        if(ValidatorUtil.isEmpty(ReflectUtil.getValueByFieldName(obj,"appId")))
                            ReflectUtil.setValueByFieldName(obj, "appId",dto.getAppId());
                        if(ValidatorUtil.isEmpty(ReflectUtil.getValueByFieldName(obj,"appName")))
                            ReflectUtil.setValueByFieldName(obj, "appName", dto.getAppName());
                        ReflectUtil.setValueByFieldName(obj, "ip", ip);
                        break;
                    }
                }
            }
            log.debug("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            log.error("异常信息:{}", e.getMessage());
        }
    }
}
