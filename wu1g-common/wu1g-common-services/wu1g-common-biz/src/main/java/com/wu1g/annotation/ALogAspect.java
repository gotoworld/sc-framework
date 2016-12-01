package com.wu1g.annotation;

import com.alibaba.fastjson.JSON;
import com.wu1g.framework.annotation.ALogController;
import com.wu1g.framework.annotation.ALogService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.IpUtils;
import com.wu1g.org.vo.OrgUser;
import com.wu1g.sys.service.SysUserLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 切点类
 */
@Aspect
@Component
@Slf4j
public class ALogAspect {
    //注入Service用于把日志保存数据库
    @Resource
    private SysUserLogService sysUserLogService;

    //Service层切点
    @Pointcut("@annotation(com.wu1g.framework.annotation.ALogService)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.wu1g.framework.annotation.ALogController)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
        //请求的IP
        String ip = IpUtils.getIpAddr(request);
        String desc=getControllerMethodDescription(joinPoint);
        try {
            //*========控制台输出=========*//
            log.debug("=====前置通知开始=====");
            log.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.debug("方法描述:" + getControllerMethodDescription(joinPoint));
            log.debug("请求人:" + user.getName());
            log.debug("请求IP:" + ip);
            //*========数据库日志=========*//
            sysUserLogService.info("xx", "用户[" + user.getName() + "]"+desc, user.getName(), ip);
            log.debug("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            //读取session中的用户
            OrgUser user = (OrgUser) request.getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
            //获取请求ip
            String ip = IpUtils.getIpAddr(request);
            String desc=getServiceMthodDescription(joinPoint);
              /*========控制台输出=========*/
            log.debug("=====异常通知开始=====");
            log.debug("异常代码:" + e.getClass().getName());
            log.debug("异常信息:" + e.getMessage());
            log.debug("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.debug("方法描述:" + desc);
            log.debug("请求人:" + user.getName());
            log.debug("请求IP:" + ip);
            log.debug("请求参数:" + params);
            sysUserLogService.info("xx", "用户[" + user.getName() + "]"+desc+e.getMessage(), user.getName(), ip);
            log.debug("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            log.error("==异常通知异常==");
            log.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ALogService.class).desc();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ALogController.class).desc();
                    break;
                }
            }
        }
        return description;
    }
}
