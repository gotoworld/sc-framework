package com.hsd.account.actor.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dao.actor.IMemberDao;
import com.hsd.account.actor.dao.identity.IIdentityDao;
import com.hsd.account.actor.dao.identity.IIdentityLogDao;
import com.hsd.account.actor.dao.user.IUserDao;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.entity.actor.Member;
import com.hsd.account.actor.entity.identity.Identity;
import com.hsd.account.actor.entity.identity.IdentityLog;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@FeignService
@Slf4j
public class UserService extends BaseService implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IIdentityDao identityDao;
    @Autowired
    private IIdentityLogDao identityLogDao;
    @Autowired
    private IMemberDao memberDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
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
                    throw new RuntimeException("账号["+dto.getAccount()+"]已存在!");
                }
                if(ValidatorUtil.notEmpty(dto.getCellphone()) && userDao.isCellphoneYN(dto.getCellphone())>0){
                    throw new RuntimeException("手机号["+dto.getCellphone()+"]已注册!");
                }
                if(ValidatorUtil.notEmpty(dto.getEmail()) && userDao.isEmailYN(dto.getEmail())>0){
                    throw new RuntimeException("邮箱["+dto.getEmail()+"]已注册!");
                }
                if(ValidatorUtil.notEmpty(dto.getPwd())) entity.setPwd(MD5.pwdMd5Hex(entity.getPwd()));
                if(ValidatorUtil.notEmpty(dto.getTradePwd())) entity.setTradePwd(MD5.pwdMd5Hex(entity.getTradePwd()));
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
//  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
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
    @Override
    public UserDto findUserByAccount(@RequestParam("account") String account, @RequestParam("userType")  Integer userType) {
        try {
            Map dto = new HashMap();
            dto.put("account", account);
            dto.put("userType", userType);
            return copyTo(userDao.findUserByAccount(dto),UserDto.class);
        } catch (Exception e) {
            log.error("用户信息>根据用户登录名,数据库处理异常!", e);
        }
        return null;
    }
    @Override
    public Integer lastLogin(@RequestBody UserDto dto) {
        try {
            return userDao.lastLogin(copyTo(dto, User.class));
        } catch (Exception e) {
            log.error("用户信息id,判断员工是否为超级管理员,要特权.,数据库处理异常!", e);
        }
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response register(@RequestBody UserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null)throw new RuntimeException("参数异常!");
            User entity = copyTo(dto, User.class);

            if(ValidatorUtil.isEmpty(dto.getAccount())){
                throw new RuntimeException("账号不能为空!");
            }
            if(userDao.isAccountYN(dto.getAccount())>0){
                throw new RuntimeException("账号["+dto.getAccount()+"]已存在!");
            }
            if(ValidatorUtil.notEmpty(dto.getCellphone()) && userDao.isCellphoneYN(dto.getCellphone())>0){
                throw new RuntimeException("手机号["+dto.getCellphone()+"]已注册!");
            }
            if(ValidatorUtil.notEmpty(dto.getEmail()) && userDao.isEmailYN(dto.getEmail())>0){
                throw new RuntimeException("邮箱["+dto.getEmail()+"]已注册!");
            }
            entity.setPwd(MD5.pwdMd5Hex(entity.getPwd()));
            //新增
            userDao.insert(entity);
            result.data=entity.getId();
        } catch (Exception e) {
            log.error("信息保存异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    public UserDto getAccount(@RequestBody UserDto dto) {
        try {
            User entity = copyTo(dto,User.class);
            return copyTo(userDao.getAccount(entity),UserDto.class);
        } catch (Exception e) {
            log.error("用户信息>根据用户登录名,数据库处理异常!", e);
        }
        return null;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response restPwd(@RequestBody UserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            User entity = copyTo(dto, User.class);
            entity.setPwd(MD5.pwdMd5Hex(entity.getPwd()));
            if(userDao.updateLoginPwd(entity)==0){
                throw new RuntimeException("密码修改失败!");
            }
        } catch (Exception e) {
            log.error("密码修改失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response phoneBind(@RequestBody UserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            User entity = copyTo(dto, User.class);

            if(userDao.isCellphoneYN(entity.getCellphone())>0) throw new RuntimeException("绑定失败!手机号["+entity.getCellphone()+"]已被其它账号绑定!");

            if(userDao.phoneBind(entity)==0){
                throw new RuntimeException("手机绑定失败!");
            }
        } catch (Exception e) {
            log.error("手机绑定失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response emailBind(@RequestBody UserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            User entity = copyTo(dto, User.class);

            if(userDao.isEmailYN(entity.getEmail())>0) throw new RuntimeException("绑定失败!邮箱["+entity.getEmail()+"]已被其它账号绑定!");

            if(userDao.emailBind(entity)==0){
                throw new RuntimeException("邮箱绑定失败!");
            }
        } catch (Exception e) {
            log.error("邮箱绑定失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response updatePwd(@RequestBody UserDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Integer count= (Integer) redisTemplate.opsForValue().get("user:setting:loginpwd:"+dto.getId());
            if(count==null) count=0;
            if(count>=5)  throw new RuntimeException("已超过24小时内最大重试次数!");
            User entity = copyTo(dto, User.class);
            //获取账号密码
            User user= userDao.getAccountPwd(entity);
            if(user==null) throw new RuntimeException("账号不存在!");
            //验证原密码
            String hashOldPwd=MD5.pwdMd5Hex(dto.getPwd());
            if(!(""+hashOldPwd).equals(user.getPwd())){
                redisTemplate.opsForValue().set("user:setting:loginpwd:"+dto.getId(),++count,24, TimeUnit.HOURS);
                throw new RuntimeException("原始密码错误!剩余重试次数,"+(5-count));
            }
            //修改新密码
            entity.setPwd(MD5.pwdMd5Hex(dto.getNewpwd()));
            if(userDao.updateLoginPwd(entity)==0){
                throw new RuntimeException("密码修改失败!");
            }
            redisTemplate.opsForValue().set("user:setting:loginpwd:"+dto.getId(),++count,0, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("密码修改失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response identity(@RequestBody IdentityDto dto) throws Exception {
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            dto.setState(0);
            dto.setCredentialType(0);
            Identity entity=copyTo(dto,Identity.class);
            if(identityDao.isDataYN(entity)>0) throw new RuntimeException("已实名认证,不能重复操作!");
            identityDao.insert(entity);
            identityLogDao.insert(copyTo(dto,IdentityLog.class));

            Member member=new Member(){{setUserId(dto.getUserId());setCredentialNumber(dto.getCredentialNumber());}};
           if(memberDao.isDataYN(member) != 0) {
               memberDao.updateCredentialNumber(member);
           }else{
               memberDao.insert(member);
           }
        } catch (Exception e) {
            log.error("实名认证异常!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response pwdTradeSetting(@RequestBody UserDto dto) throws Exception{
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            User entity = copyTo(dto, User.class);
            //获取账号密码
            User user= userDao.getAccountPwd(entity);
            if(user==null) throw new RuntimeException("账号不存在!");
            if(ValidatorUtil.notEmpty(user.getTradePwd())) throw new RuntimeException("登录密码已存在,不能重复操作!");

            entity.setTradePwd(MD5.pwdMd5Hex(dto.getTradePwd()));
            if(userDao.updateTradePwd(entity)==0){
                throw new RuntimeException("交易密码修改失败!");
            }
            redisTemplate.opsForValue().getOperations().delete("user:setting:tradepwd:"+dto.getId());
        } catch (Exception e) {
            log.error("交易密码修改失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response pwdTradeUpdate(@RequestBody UserDto dto) throws Exception{
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            Integer count= (Integer) redisTemplate.opsForValue().get("user:setting:tradepwd:"+dto.getId());
            if(count==null) count=0;
            if(count>=5)  throw new RuntimeException("已超过24小时内最大重试次数!");

            User entity = copyTo(dto, User.class);

            //获取账号密码
            User user= userDao.getAccountPwd(entity);
            if(user==null) throw new RuntimeException("账号不存在!");

            //验证原交易密码
            String hashOldTradePwd=MD5.pwdMd5Hex(dto.getTradePwd());
            if(!(""+hashOldTradePwd).equals(user.getTradePwd())){
                redisTemplate.opsForValue().set("user:setting:tradepwd:"+dto.getId(),++count,24, TimeUnit.HOURS);
                throw new RuntimeException("原始交易密码错误!剩余重试次数,"+(5-count));
            }

            entity.setTradePwd(MD5.pwdMd5Hex(dto.getNewpwd()));
            if(userDao.updateTradePwd(entity)==0){
                throw new RuntimeException("交易密码修改失败!");
            }
            redisTemplate.opsForValue().getOperations().delete("user:setting:tradepwd:"+dto.getId());
        } catch (Exception e) {
            log.error("交易密码修改失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = {Exception.class, RuntimeException.class})
    public Response pwdTradeResetSetting(@RequestBody UserDto dto) throws Exception{
        Response result = new Response(0,"seccuss");
        try {
            if (dto == null) throw new RuntimeException("参数异常!");
            User entity = copyTo(dto, User.class);
            entity.setTradePwd(MD5.pwdMd5Hex(dto.getTradePwd()));
            if(userDao.updateTradePwd(entity)==0){
                throw new RuntimeException("交易密码重置失败!");
            }
            redisTemplate.opsForValue().getOperations().delete("user:setting:tradepwd:"+dto.getId());
        } catch (Exception e) {
            log.error("交易密码重置失败!", e);
            throw new ServiceException(SysErrorCode.defaultError,e.getMessage());
        }
        return result;
    }
}