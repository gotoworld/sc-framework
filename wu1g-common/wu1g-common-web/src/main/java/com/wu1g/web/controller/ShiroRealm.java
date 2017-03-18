package com.wu1g.web.controller;

import com.wu1g.api.auth.IRoleSourceService;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.auth.AuthPerm;
import com.wu1g.vo.auth.AuthRole;
import com.wu1g.vo.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
//    public ShiroRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
//        super(cacheManager, matcher);
//    }
    /**
     * 角色资源 业务处理。
     */
    @Autowired
    private IRoleSourceService roleSourceService;

    /**
     * 这里编写授权代码
     * 认证回调函数,登录时调用. 是判断用户是否有权限执行某操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info(" 由于加入了缓存, 此处只会load一次：doGetAuthorizationInfo.................");
//		得到 doGetAuthenticationInfo 方法中传入的凭证
        String accid = (String) principals.fromRealm(getName()).iterator().next();
//		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();  
        if (accid == null) {
            return null;
        }
        //1.根据用户登录名获取用户信息
//		OrgUser orgUserBean=roleSourceService.findUserByLoginName(accid);
        OrgUser orgUserBean = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
        if (orgUserBean == null) {
            return null;
        }
//		//存放用户信息  信息传递.
//		SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USER,orgUserBean);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Map<String,Object> dto = new HashMap<>();
        dto.put("uid", orgUserBean.getId());
        if (roleSourceService.isSuperAdmin(dto) > 0) {
            //超级管理员标记
            dto.put("iissuperman", 1);
            SecurityUtils.getSubject().getSession().setAttribute("isSuper", "1");
        }
        //2.获取角色集合
        List<AuthRole> roleList = roleSourceService.getRoleListByUId(dto);
        if (roleList != null) {
            for (AuthRole role : roleList) {
                info.addRole(role.getName());
            }
        }
        //3.获取功能集合
        List<AuthPerm> permList = roleSourceService.getPermListByUId(dto);
        if (permList != null) {
            for (AuthPerm perm : permList) {
                if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
                    info.addStringPermission(perm.getMatchStr());
                }
            }
        }
        //4.返回SimpleAuthorizationInfo对象
        return info;
    }

    /**
     * 这里编写认证代码
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken anthToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) anthToken;
        String accid = token.getUsername();
        log.info("accid: " + accid);
        //几种用法 1.缓存所有用户信息 2.使用数据库
        //2.获取根据用户登录名获取用户信息
        OrgUser orgUser = roleSourceService.findUserByLoginName(accid);
        if (orgUser != null) {
            //缓存用户信息 减少一步查询数据库
            SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USER, orgUser);
            //创建AuthenticationInfo对象并返回
            return new SimpleAuthenticationInfo(accid, orgUser.getPwd(), getName());
        }
        return null;
    }
}
