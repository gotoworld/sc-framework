package com.hsd.config;

import com.hsd.account.api.auth.IRoleSourceService;
import com.hsd.account.dto.auth.AuthPermDto;
import com.hsd.account.dto.auth.AuthRoleDto;
import com.hsd.account.dto.org.OrgUserDto;
import com.hsd.account.dto.shiro.MyShiroUserToken;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.MD5Util;
import com.hsd.framework.util.ValidatorUtil;
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
        String account = (String) super.getAvailablePrincipal(principals);//(String) principals.fromRealm(getName()).iterator().next();
        if (account == null) {
            return null;
        }
        //1.根据用户登录名获取用户信息
        OrgUserDto orgUser = (OrgUserDto) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
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
        List<AuthRoleDto> roleList = roleSourceService.getRoleListByUId(orgUser);
        if (roleList != null) {
            roleList.forEach(role->{
                info.addRole(role.getName());
            });
        }
        //3.获取功能集合
        List<AuthPermDto> permList = roleSourceService.getPermListByUId(orgUser);
        if (permList != null) {
            permList.forEach(perm->{
                if (ValidatorUtil.notEmpty(perm.getMatchStr())) {
                    info.addStringPermission(perm.getMatchStr());
                }
            });
        }
        SecurityUtils.getSubject().getSession().setAttribute("SimpleAuthorizationInfo", info);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken anthToken) throws AuthenticationException {
        MyShiroUserToken token = (MyShiroUserToken) anthToken;
        String account = token.getUsername();
        log.info("用户[" + account + "],类型:" + token.getUserType().getName());
        String salt2 = "wei12es46254ri01i20ijd982e9hadkl12312";
        SimpleAuthenticationInfo info = null;
        OrgUserDto orgUser = roleSourceService.findUserByLoginName(account,token.getUserType().getId());
        if (orgUser != null) {
            info = new SimpleAuthenticationInfo(account, orgUser.getPwd(), getName());
            clearCache(info.getPrincipals());
//            info.setCredentialsSalt(ByteSource.Util.bytes(account+salt2)); //盐是用户名+随机数
        }
        return info;
    }
    public static void main(String[] args) {
        System.out.println(MD5Util.string2MD5(MD5Util.string2MD5("admin")+""));
    }
}