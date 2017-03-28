package com.wu1g.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 拓展shiro 中的UsernamePasswordToken
 */

public class MyShiroUserToken extends UsernamePasswordToken {
    private UserType userType;

    public enum UserType {
        admin(1, "管理员"), member(2, "会员");

        private Integer id;
        private String name;
        UserType(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

    }
    public MyShiroUserToken(String accid, String password, UserType userType) {
        super(accid, password);
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
}