package com.hsd.account.actor.service.user;

import com.hsd.account.actor.api.user.IUserAppService;
import com.hsd.account.actor.dao.user.IUserAppDao;
import com.hsd.account.actor.dto.user.UserAppDto;
import com.hsd.account.actor.entity.user.UserApp;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@FeignService
@Slf4j
public class UserAppService extends BaseService implements IUserAppService {
    @Autowired
    private IUserAppDao userAppDao;

    @Override
    public UserAppDto getSaveDataByAppIdAndUserId(@RequestBody UserAppDto dto) {
        UserAppDto result = null;
        try {
            UserApp entity = copyTo(dto, UserApp.class);
            result = copyTo(userAppDao.findDataByAppIdAndUserId(entity), UserAppDto.class);
            if (result == null) {
                entity.setId(idGenerator.nextId());
                userAppDao.insert(entity);
                dto.setId(entity.getId());
                result = dto;
            }
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }
    @Override
    public UserAppDto findDataByAppIdAndUserId(@RequestBody UserAppDto dto) {
        UserAppDto result = null;
        try {
            result = copyTo(userAppDao.findDataByAppIdAndUserId(copyTo(dto, UserApp.class)), UserAppDto.class);
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

}