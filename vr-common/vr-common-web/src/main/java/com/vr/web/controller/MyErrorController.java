package com.vr.web.controller;

import com.vr.framework.Response;
import com.vr.framework.util.ReflectUtil;
import com.vr.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class MyErrorController implements ErrorController {
    private static final String prefix = "/error";

    @RequestMapping(value = prefix+"/")
    public Response handleError(HttpServletRequest request, HttpServletResponse response) {
        log.info("其它错误~");
        return Response.error("未知错误!");
    }

    @RequestMapping(value = prefix + "/403")
    @ResponseBody
    public Response error403(HttpServletRequest request, HttpServletResponse response) {
        log.info("403错误~");
        return Response.error(403, "403错误!");
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

    @RequestMapping(value = prefix + "/500")
    public Response error500(HttpServletRequest request, HttpServletResponse response) {
        log.info("500错误~");
        return Response.error("系统运行错误!");
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
}
