package com.hsd.actor.aspect;

import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.IDto;
import com.hsd.framework.util.IpUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ReflectUtil;
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
    public void doBefore(JoinPoint joinPoint) throws Exception {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //读取session中的员工
            UserDto actor = JwtUtil.getSubject(UserDto.class);
            //请求的IP
            String ip = IpUtil.getIpAddr(request);
            if(log.isDebugEnabled()) {
                log.debug("=====前置通知开始=====");
                log.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
                log.debug("请求人:" + actor.getName());
                log.debug("请求IP:" + ip);
            }
            Object[] objArr=joinPoint.getArgs();
            if(objArr!=null){
                for(Object obj:objArr){
                    if(obj instanceof IDto){
//                      System.out.printf(JSON.toJSONString(obj));
                        ReflectUtil.setValueByFieldName2(obj,"createId", actor.getId());//创建者id
//                        ReflectUtil.setValueByFieldName2(obj,"account", orgActor.getAccount());//id
//                        ReflectUtil.setValueByFieldName2(obj,"createIp",ip);//创建者ip
//                        ReflectUtil.setValueByFieldName2(obj,"updateId",orgActor.getId());//修改者id
//                        ReflectUtil.setValueByFieldName2(obj,"updateIp",ip);//修改者ip
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
