package com.hsd.account.shiro;

import com.hsd.account.api.auth.IRoleSourceService;
import com.hsd.account.vo.auth.AuthPerm;
import com.hsd.account.vo.auth.AuthRole;
import com.hsd.framework.util.CommonConstant;
import com.hsd.vo.org.OrgUser;
import com.hsd.vo.shiro.MyShiroUserToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    public ShiroRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }

    public ShiroRealm(CacheManager cacheManager) {
        super(cacheManager);
    }

    public ShiroRealm() {
        super();
    }

    @Autowired
    private IRoleSourceService roleSourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info(" 由于加入了缓存, 此处只会load一次：doGetAuthorizationInfo.................");
//		得到 doGetAuthenticationInfo 方法中传入的凭证
        String accid = (String) super.getAvailablePrincipal(principals);//(String) principals.fromRealm(getName()).iterator().next();
        if (accid == null) {
            return null;
        }
        //1.根据用户登录名获取用户信息
        OrgUser orgUser = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
        if (orgUser == null) {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (0==(orgUser.getType()) && roleSourceService.isSuperAdmin(orgUser) > 0) {
            //超级管理员标记
            orgUser.setIissuperman(1);
            SecurityUtils.getSubject().getSession().setAttribute("isSuper", "1");
        }
        //2.获取角色集合
        List<AuthRole> roleList = roleSourceService.getRoleListByUId(orgUser);
        if (roleList != null) {
            for (AuthRole role : roleList) {
                info.addRole(role.getName());
            }
        }
        //3.获取功能集合
        List<AuthPerm> permList = roleSourceService.getPermListByUId(orgUser);
        if (permList != null) {
            for (AuthPerm perm : permList) {
                if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
                    info.addStringPermission(perm.getMatchStr());
                }
            }
        }
        SecurityUtils.getSubject().getSession().setAttribute("SimpleAuthorizationInfo", info);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken anthToken) throws AuthenticationException {
        MyShiroUserToken token = (MyShiroUserToken) anthToken;
        String accid = token.getUsername();
        log.info("用户[" + accid + "],类型:" + token.getUserType().getName());
        AuthenticationInfo info = null;
        OrgUser orgUser = roleSourceService.findUserByLoginName(accid,token.getUserType().getId());
        if (orgUser != null) {
            info = new SimpleAuthenticationInfo(accid, orgUser.getPwd(), getName());
            SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USER, orgUser);
            SecurityUtils.getSubject().getSession().setAttribute(token.getUserType().getCacheKey(), orgUser);
            roleSourceService.lastLogin(orgUser);
        }
        return info;
    }
}