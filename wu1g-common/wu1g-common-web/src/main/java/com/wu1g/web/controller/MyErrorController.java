package com.wu1g.web.controller;

import com.wu1g.framework.Response;
import com.wu1g.framework.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyErrorController extends BaseController {
    private static final String prefix = "/error";

    @RequestMapping(value = prefix+"/")
    public Response handleError() {
        log.info("其它错误~");
        return Response.error("未知错误!");
    }

    @RequestMapping(value = prefix + "/403")
    @ResponseBody
    public Response error403() {
        log.info("403错误~");
        return Response.error(403, "403错误!");
    }

    @RequestMapping(value = prefix + "/404")
    public Response error404() {
        Object[] objArr = (Object[]) ReflectUtil.getValueByFieldName(request, "specialAttributes");
        String gurl404 = "";
        if (objArr != null && objArr.length > 6) {
            gurl404 = (String) objArr[6];
        }
        log.info("404错误 url=" + gurl404);
        return Response.error(404, "所访问的地址不存在 url=" + gurl404);
    }

    @RequestMapping(value = prefix + "/500")
    public Response error500() {
        log.info("500错误~");
        return Response.error("系统运行错误!");
    }

    @RequestMapping(value = prefix + "/locked")
    @ResponseBody
    public Response errorlocked() {
        log.info("locked~");
        return Response.error("locked!");
    }

    @RequestMapping(value = prefix + "/noauth")
    public Response errornoauth() {
        log.info("noauth~");
        return Response.error(503, "权限不足!");
    }
}
