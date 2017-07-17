package com.hsd.vo.shiro;

import com.hsd.framework.util.CommonConstant;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 拓展shiro 中的UsernamePasswordToken
 */

public class MyShiroUserToken extends UsernamePasswordToken {
    private UserType userType;

    public enum UserType {
        admin(0, "管理员", CommonConstant.SESSION_KEY_USER_ADMIN), member(1, "会员",CommonConstant.SESSION_KEY_USER_MEMBER);
        private Integer id;
        private String name;
        private String cacheKey;
        UserType(Integer id, String name,String cacheKey) {
            this.id = id;
            this.name = name;
            this.cacheKey=cacheKey;
        }
        public String getCacheKey() {
            return cacheKey;
        }
        public Integer getId() {
            return id;
        }
        public String getName() {
            return name;
        }
    }
    public MyShiroUserToken(String account, String password, UserType userType) {
        super(account, password);
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
}