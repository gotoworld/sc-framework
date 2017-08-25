package com.hsd.account.actor.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dao.user.IUserDao;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.entity.user.User;
import com.hsd.framework.Response;
import com.hsd.framework.SysErrorCode;
import com.hsd.framework.annotation.FeignService;
import com.hsd.framework.exception.ServiceException;
import com.hsd.framework.security.MD5;
import com.hsd.framework.service.BaseService;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignService
@Slf4j
public class UserService extends BaseService implements IUserService {
    @Autowired
    private IUserDao userDao;

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response saveOrUpdateData(@RequestBody UserDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                User entity = copyTo(dto, User.class);
                //判断数据是否存在
                if (userDao.isDataYN(entity) != 0) {
                    //数据存在
                    userDao.update(entity);
                } else {
                    if(ValidatorUtil.isEmpty(dto.getAccount())){
                        throw new RuntimeException("账号不能为空!");
                    }
                    if(userDao.isAccountYN(dto.getAccount())>0){
                        throw new RuntimeException("账号已存在!");
                    }
                    entity.setPwd(MD5.pwdMd5Hex(entity.getPwd()));
                    entity.setTradePwd(MD5.pwdMd5Hex(entity.getTradePwd()));
                    //新增
                     userDao.insert(entity);
                     result.data=entity.getId();
                }
            } catch (Exception e) {
                log.error("信息保存异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
        @Override
        public String deleteData(@RequestBody UserDto dto) throws Exception {
            String result = "seccuss";
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                User entity = copyTo(dto, User.class);
                if(userDao.deleteByPrimaryKey(entity)==0){
                    throw new RuntimeException("数据不存在!");
                }
            } catch (Exception e) {
                log.error("物理删除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }


        @Override
        public PageInfo findDataIsPage(@RequestBody UserDto dto) throws Exception {
           PageInfo pageInfo=null;
           try {
               if (dto == null)throw new RuntimeException("参数异常!");
               User entity = copyTo(dto, User.class);
               PageHelper.startPage(PN(dto.getPageNum()), PS(dto.getPageSize()));
               List list = userDao.findDataIsPage(entity);
               pageInfo=new PageInfo(list);
               pageInfo.setList(copyTo(pageInfo.getList(), UserDto.class));
           } catch (Exception e) {
               log.error("信息[分页]查询异常!", e);
               throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
           }
           return pageInfo;
        }

        @Override
        public List<UserDto> findDataIsList(@RequestBody UserDto dto) throws Exception {
            List<UserDto>  results = null;
            try {
                User entity = copyTo(dto, User.class);
                 results = copyTo(userDao.findDataIsList(entity), UserDto.class);
            } catch (Exception e) {
                log.error("信息[列表]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return  results;
        }

        @Override
        public UserDto findDataById(@RequestBody UserDto dto) throws Exception {
            UserDto result = null;
            try {
                User entity = copyTo(dto, User.class);
                result = copyTo(userDao.selectByPrimaryKey(entity),UserDto.class);
            } catch (Exception e) {
                log.error("信息[详情]查询异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response setTags(@RequestBody UserDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                userDao.setTags(copyTo(dto, User.class));
            } catch (Exception e) {
                log.error("标签设置异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
        @Override
//        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response setBlacklist(@RequestBody UserDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                if(userDao.setBlacklist(copyTo(dto, User.class))==0){
                    throw new RuntimeException("设置失败,请检查当前状态!");
                }
            } catch (Exception e) {
                log.error("黑名单设置异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
        @Override
//        @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
        public Response delBlacklist(@RequestBody UserDto dto) throws Exception {
            Response result = new Response(0,"seccuss");
            try {
                if (dto == null)throw new RuntimeException("参数异常!");
                if(userDao.delBlacklist(copyTo(dto, User.class))==0){
                    throw new RuntimeException("设置失败,请检查当前状态!");
                }
            } catch (Exception e) {
                log.error("黑名单移除异常!", e);
                throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
            }
            return result;
        }
}