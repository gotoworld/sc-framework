package com.hsd.util.service.msg;

import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.util.api.msg.IPushService;
import com.hsd.util.dto.msg.PushDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeUnit;

@FeignService
@Slf4j
public class PushService extends BaseService implements IPushService {
    public static final String prefix = "push:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Response captchaSms(@RequestBody PushDto dto) throws Exception {
        Response result = new Response(0, "seccuss");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getCellphone())) throw new RuntimeException("参数异常!");
            redisTemplate.opsForValue().set(prefix + dto.getPrefix() + dto.getCellphone(), "1234", 5, TimeUnit.MINUTES);//验证码有效期5分钟
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    public Response captchaEmail(@RequestBody PushDto dto) throws Exception {
        Response result = new Response(0, "seccuss");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getEmail())) throw new RuntimeException("参数异常!");
            redisTemplate.opsForValue().set(prefix + dto.getPrefix() + dto.getEmail(), "1234", 5, TimeUnit.MINUTES);//验证码有效期5分钟
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    public Response verifyCaptchaSms(@RequestBody PushDto dto) throws Exception {
        Response result = new Response(0, "seccuss");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getCellphone())) throw new RuntimeException("参数异常!");
            if (!(""+dto.getCaptcha()).equals(redisTemplate.opsForValue().get(prefix + dto.getPrefix() + dto.getCellphone()))) {
                throw new RuntimeException("校验失败,验证码错误!");
            }
            redisTemplate.opsForValue().getOperations().delete(prefix + dto.getPrefix() + dto.getCellphone());
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    public Response verifyCaptchaEmail(@RequestBody PushDto dto) throws Exception {
        Response result = new Response(0, "seccuss");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getEmail())) throw new RuntimeException("参数异常!");
            if (!(""+dto.getCaptcha()).equals(redisTemplate.opsForValue().get(prefix + dto.getPrefix() + dto.getEmail()))) {
                throw new RuntimeException("校验失败,验证码错误!");
            }
            redisTemplate.opsForValue().getOperations().delete(prefix + dto.getPrefix() + dto.getEmail());
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
}