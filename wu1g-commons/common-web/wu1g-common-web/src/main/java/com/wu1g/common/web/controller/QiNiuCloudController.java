package com.wu1g.common.web.controller;

import com.wu1g.common.web.config.QiNiuYunUtils;
import com.wu1g.framework.Response;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lixuejian on 2016/9/18.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/common/qiNiuCloud")
public class QiNiuCloudController{

    @RequestMapping(value = "/v1/getUpToken", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "获取七牛云上传Token", notes = "获取七牛云上传Token")
    public Response getUpToken() {
        Response result = new Response();
        result.data = QiNiuYunUtils.getUpToken();
        return result;
    }
}
