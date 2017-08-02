package com.hsd.aspect;

import com.alibaba.fastjson.JSON;
import com.hsd.api.sys.ISysUserLogService;
import com.hsd.framework.IDto;
import com.hsd.framework.annotation.ALogOperation;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IpUtil;
import com.hsd.framework.util.ReflectUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.dto.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 切点类
 */
@Aspect
@Component
@Slf4j
public class ALogAspect {
    //注入Service用于把日志保存数据库
    @Autowired
    private ISysUserLogService sysUserLogService;

    //操作日志切点
    @Pointcut("@annotation(com.hsd.framework.annotation.ALogOperation)")
    public void alogOperationAspect() {
    }

    /**
     * 用于记录用户的操作
     */
    @After("alogOperationAspect()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        //读取session中的用户
        OrgUser user = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
        //请求的IP
        String ip = IpUtil.getIpAddr(request);
        String[] logArr = getMethodDesc(joinPoint);
        try {
            //*========控制台输出=========*//
//            log.debug("=====前置通知开始=====");
//            log.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.debug("方法描述:" + logArr[1]);
//            log.debug("请求人:" + user.getName());
//            log.debug("请求IP:" + ip);
            Object object=null;
            Object[] objArr=joinPoint.getArgs();
            if(objArr!=null){
                for(Object obj:objArr){
                    if(obj instanceof IDto){
//                        log.debug(JSON.toJSONString(obj));
                        object=obj;
                        break;
                    }
                }
            }
            String id="";
            String newFlag="";
            try {
                id=(String) ReflectUtil.getValueByFieldName(object,"id");
                newFlag=(String) ReflectUtil.getValueByFieldName(object,"newFlag");
                if("1".equals(newFlag)){
                    logArr[0]="新增";
                }
                if(ValidatorUtil.notEmpty(id)){
                    id="["+id+"]";
                }
            } catch (Exception e) {
                id="["+objArr[0]+"]";
            }
//            //*========数据库日志=========*//
            sysUserLogService.info(logArr[0],logArr[1],id, user.getId(), user.getName(), ip);
//            log.debug("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            log.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 记录操作异常日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "alogOperationAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            //读取session中的用户
            OrgUser user = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_ADMIN);
            //获取请求ip
            String ip = IpUtil.getIpAddr(request);
            String[] logArr = getMethodDesc(joinPoint);
//              /*========控制台输出=========*/
//            log.debug("=====异常通知开始=====");
//            log.debug("异常代码:" + e.getClass().getName());
//            log.debug("异常信息:" + e.getMessage());
//            log.debug("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.debug("方法描述:" + logArr[1]);
//            log.debug("请求人:" + user.getName());
//            log.debug("请求IP:" + ip);
//            log.debug("请求参数:" + params);
            sysUserLogService.info(logArr[0], logArr[1], e.getMessage(), user.getId(),user.getName(), ip);
//            log.debug("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            log.error("==异常通知异常==");
            log.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
    }
    /**
     * 获取注解中对方法的描述信息
     */
    public static String[] getMethodDesc(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String[] LogArr = new String[2];
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    LogArr[0] = method.getAnnotation(ALogOperation.class).type();
                    LogArr[1] = method.getAnnotation(ALogOperation.class).desc();
                    break;
                }
            }
        }
        return LogArr;
    }
}
