package com.hsd.framework.web.controller;

import com.hsd.framework.Response;
import com.hsd.framework.util.ReflectUtil;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
public class MyErrorController implements ErrorController {
    private static final String prefix = "/error";
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = prefix+"/")
    public Response handleError(HttpServletRequest request, HttpServletResponse response) {
        log.info("其它错误~");
        return Response.error("未知错误!");
    }

    @RequestMapping(value = prefix + "/403")
    @ResponseBody
    public Response error403(HttpServletRequest request, HttpServletResponse response) {
        log.info("403错误~");
        return Response.error(403, "无效的登录认证!");
    }

    @RequestMapping(value = prefix + "/404")
    public Response error404(HttpServletRequest request, HttpServletResponse response) {
        String gurl404 =""+(request.getAttribute("javax.servlet.error.request_uri"));
        if(ValidatorUtil.isEmpty(gurl404)){
            Object[] objArr = (Object[]) ReflectUtil.getValueByFieldName(request, "specialAttributes");
            if (objArr != null && objArr.length > 6) {
                gurl404 = (String) objArr[6];
            }
        }
        log.info("404错误 url=" + gurl404);
        return Response.error(404, "所访问的地址不存在 url=" + gurl404);
    }
    private boolean debug = true;
    @RequestMapping(value = prefix + "/500")
    public Response error500(HttpServletRequest request, HttpServletResponse response) {
        log.info("500错误~"+request.getMethod()+response.getStatus()+getErrorAttributes(request, debug));
        return Response.error("系统运行错误!"+getErrorAttributes(request, debug));
    }


    @RequestMapping(value = prefix + "/locked")
    @ResponseBody
    public Response errorlocked(HttpServletRequest request, HttpServletResponse response) {
        log.info("locked~");
        return Response.error("locked!");
    }

    @RequestMapping(value = prefix + "/noauth")
    public Response errornoauth(HttpServletRequest request, HttpServletResponse response) {
        log.info("noauth~");
        return Response.error(503, "权限不足!");
    }

    @Override
    public String getErrorPath() {
        return prefix;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
