package com.hsd.framework.exception;

import com.hsd.framework.FeignErrorDTO;
import com.hsd.framework.util.Converter;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static java.lang.String.format;

/** 服务发现错误、例外处理
 */
@Configuration
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        int code = response.status() ;
        String message = format("status %s reading %s", response.status(), methodKey);
        String data = "";

        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                FeignErrorDTO errDto = Converter.parseObject(body, FeignErrorDTO.class);

                message = errDto.getMessage();
                if (message == null)
                    message = errDto.getError();
                if (message != null) {
                    data = message;
                    int idx = data.indexOf(":");
                    if (idx >= 0) {
                        code = Integer.parseInt(data.substring(idx+1,data.lastIndexOf(":")).trim());
                        if (data.length() > idx + 1)
                            message = data.substring(data.lastIndexOf(":") + 1);
                    }
                }

                data = body;
            }
        } catch (IOException ignored) { // NOPMD
//            log.error("FeignErrorDecoder:",ignored);
        }

        ServiceFeignException exception = new ServiceFeignException(code, message , data);

        return exception;
    }
}