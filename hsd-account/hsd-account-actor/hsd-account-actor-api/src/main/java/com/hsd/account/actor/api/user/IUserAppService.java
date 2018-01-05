package com.hsd.account.actor.api.user;

import com.hsd.account.actor.dto.user.UserAppDto;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * <p>APP用户表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IUserAppService {
    String acPrefix = "/feign/account/actor/IUserAppService";
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getSaveDataByAppIdAndUserId")
    UserAppDto getSaveDataByAppIdAndUserId(UserAppDto dto)throws Exception;
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByAppIdAndUserId")
    UserAppDto findDataByAppIdAndUserId(UserAppDto dto)throws Exception;
}