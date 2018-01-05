package com.hsd.account.finance.web.controller;

import com.hsd.account.actor.api.user.IUserAppService;
import com.hsd.account.actor.dto.user.UserAppDto;
import com.hsd.framework.cache.util.RedisHelper;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

public class FinanceBaseController extends BaseController {
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private IUserAppService userAppService;

    /**
     * 获取app用户id
     */
    protected Long getAppUserId(String appId, Long userId) throws Exception {
        String appUserObj=""+redisHelper.get("appUser:"+appId+":"+userId);
        Long appUserId = ValidatorUtil.notEmpty(appUserObj)?Long.parseLong(appUserObj):null;
        if(appUserId==null){
            UserAppDto userAppDto=userAppService.findDataByAppIdAndUserId(new UserAppDto(){{setAppId(appId);setUserId(userId);}});
            if(userAppDto!=null){
                appUserId=userAppDto.getId();
                redisHelper.set("appUser:"+appId+":"+userId,appUserId);
            }else{
                throw new RuntimeException("无应用使用记录");
            }
        }
        return appUserId;
    }
}
