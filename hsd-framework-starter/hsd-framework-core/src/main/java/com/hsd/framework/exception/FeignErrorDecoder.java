package com.hsd.framework.exception;

import com.hsd.framework.FeignErrorDTO;
import com.hsd.framework.util.CommonConstant;
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
                    int idx = data.indexOf(errDto.getException()+":");
                    if (idx >= 0) {
                        try {
                            code = Integer.parseInt(data.substring(idx+1,data.lastIndexOf(CommonConstant.FEIGN_ERROR_SYMBOL_STRING)).trim());
                        } catch (NumberFormatException e) {
                            code=501;
                        }
                        if (data.length() > idx + CommonConstant.FEIGN_ERROR_SYMBOL_STRING.length())
                            message = data.substring(data.lastIndexOf(CommonConstant.FEIGN_ERROR_SYMBOL_STRING) + CommonConstant.FEIGN_ERROR_SYMBOL_STRING.length());
                    }
                }

                data = body;
            }
        } catch (IOException ignored) { // NOPMD
//            log.error("FeignErrorDecoder:",ignored);
        }
        return new ServiceFeignException(code, message , data);
    }
}
