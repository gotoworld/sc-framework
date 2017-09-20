package com.hsd.account.actor.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.user.IUserExtInfoService;
import com.hsd.account.actor.dao.user.IUserExtInfoDao;
import com.hsd.account.actor.dto.user.UserExtInfoDto;
import com.hsd.account.actor.entity.user.UserExtInfo;
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
public class UserExtInfoService extends BaseService implements IUserExtInfoService {
    @Autowired
    private IUserExtInfoDao userExtInfoDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody UserExtInfoDto dto) throws Exception {
            Response result = new Response(0,"success");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                UserExtInfo entity = copyTo(dto, UserExtInfo.class);
                //判断数据是否存在
                if (userExtInfoDao.isDataYN(entity) != 0) {
                    //数据存在
                    userExtInfoDao.update(entity);
                } else {
                    //新增
                     userExtInfoDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        public String deleteData(@RequestBody UserExtInfoDto dto) throws Exception {
            String result = "success";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                UserExtInfo entity = copyTo(dto, UserExtInfo.class);
                if(userExtInfoDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody UserExtInfoDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               UserExtInfo entity = copyTo(dto, UserExtInfo.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = userExtInfoDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), UserExtInfoDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<UserExtInfoDto> findDataIsList(@RequestBody UserExtInfoDto dto) throws Exception {
            List<UserExtInfoDto>  results = null;
            try {
                UserExtInfo entity = copyTo(dto, UserExtInfo.class);
                 results = copyTo(userExtInfoDao.findDataIsList(entity), UserExtInfoDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public UserExtInfoDto findDataById(@RequestBody UserExtInfoDto dto) throws Exception {
            UserExtInfoDto result = null;
            try {
                UserExtInfo entity = copyTo(dto, UserExtInfo.class);
                result = copyTo(userExtInfoDao.selectByPrimaryKey(entity),UserExtInfoDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


}