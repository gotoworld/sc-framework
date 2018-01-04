package com.hsd.account.actor.service.user;

import com.hsd.account.actor.api.user.IUserAppService;
import com.hsd.account.actor.dao.user.IUserAppDao;
import com.hsd.account.actor.dto.user.UserAppDto;
import com.hsd.account.actor.entity.user.UserApp;
import com.hsd.framework.IdGenerator;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class UserAppService extends BaseService implements IUserAppService {
    @Autowired
    private IUserAppDao userAppDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response saveOrUpdateData(@RequestBody UserAppDto dto) {
        Response result = new Response(0, "success");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            UserApp entity = copyTo(dto, UserApp.class);
            //判断数据是否存在
            if (userAppDao.isDataYN(entity) != 0) {
                //数据存在

            } else {
                //新增
                userAppDao.insert(entity);
                result.data = entity.getId();
            }
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError, e.getMessage());
        }
        return result;
    }

    @Override
    public UserAppDto findDataByAppIdAndUserId(@RequestBody UserAppDto dto) {
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

}