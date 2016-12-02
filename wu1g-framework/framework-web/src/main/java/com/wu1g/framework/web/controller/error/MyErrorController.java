package com.wu1g.framework.web.controller.error;

import com.wu1g.framework.Response;
import com.wu1g.framework.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("/error")
@Slf4j
public class MyErrorController {
    private static final String ERROR_PATH = "/";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Response handleError(ServletRequest request, ServletResponse rsp) {
        log.info("其它错误~");
//		return "error/error";
        return Response.error("未知错误!");
    }

    @RequestMapping(value = "/403")
    @ResponseBody
    public Response error403(ServletRequest request, ServletResponse rsp) {
        log.info("403错误~");
//		return "error/403";
        return Response.error(403, "403错误!");
    }

    @RequestMapping(value = "/404")
    @ResponseBody
    public Response error404(ServletRequest request, ServletResponse rsp) {
        HttpServletRequest httprequest = ((HttpServletRequest) request);
        Object[] objArr = (Object[]) ReflectUtil.getValueByFieldName(httprequest, "specialAttributes");
        String gurl404 = "";
        if (objArr != null && objArr.length > 6) {
            gurl404 = (String) objArr[6];
        }
        log.info("404错误 url=" + gurl404);
//		return "error/404";
        return Response.error(404, "所访问的地址不存在 url=" + gurl404);
    }

    @RequestMapping(value = "/500")
    @ResponseBody
    public Response error500(ServletRequest request, ServletResponse rsp) {
        log.info("500错误~");
        return Response.error("系统运行错误!");
    }

    @RequestMapping(value = "/locked")
    @ResponseBody
    public Response errorlocked(ServletRequest request, ServletResponse rsp) {
        log.info("locked~");
//		return "error/locked";
        return Response.error("locked!");
    }

    @RequestMapping(value = "/noauth")
    @ResponseBody
    public Response errornoauth(ServletRequest request, ServletResponse rsp) {
        log.info("noauth~");
        return Response.error(503, "权限不足!");
    }
}
