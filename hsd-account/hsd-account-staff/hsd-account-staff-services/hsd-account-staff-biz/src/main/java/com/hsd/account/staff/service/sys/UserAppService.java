package com.hsd.account.staff.service.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.api.sys.IUserAppService;
import com.hsd.account.staff.dao.sys.IUserAppDao;
import com.hsd.account.staff.dto.sys.UserAppDto;
import com.hsd.account.staff.entity.sys.UserApp;
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
        public Response saveOrUpdateData(@RequestBody UserAppDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                UserApp entity = copyTo(dto, UserApp.class);
                //判断数据是否存在
                if (userAppDao.isDataYN(entity) != 0) {
                    //数据存在
                    userAppDao.update(entity);
                } else {
                    //新增
                     userAppDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody UserAppDto dto) throws Exception {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                UserApp entity = copyTo(dto, UserApp.class);
                if(userAppDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody UserAppDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               UserApp entity = copyTo(dto, UserApp.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = userAppDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), UserAppDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<UserAppDto> findDataIsList(@RequestBody UserAppDto dto) throws Exception {
            List<UserAppDto>  results = null;
            try {
                UserApp entity = copyTo(dto, UserApp.class);
                 results = copyTo(userAppDao.findDataIsList(entity), UserAppDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public UserAppDto findDataById(@RequestBody UserAppDto dto) throws Exception {
            UserAppDto result = null;
            try {
                UserApp entity = copyTo(dto, UserApp.class);
                result = copyTo(userAppDao.selectByPrimaryKey(entity),UserAppDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

    @Override
    public UserAppDto findDate(@RequestBody UserAppDto dto) throws Exception {
        UserAppDto result = null;
        try {
            UserApp entity = copyTo(dto, UserApp.class);
            result = copyTo(userAppDao.findDate(entity),UserAppDto.class);
            if(result == null){
                userAppDao.insert(entity);
                result =copyTo(userAppDao.findDate(entity),UserAppDto.class);
            }
        } catch (Exception e) {
            log.error("信息查询异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }


}