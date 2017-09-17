package com.yzz.java.bean;

import scala.util.parsing.combinator.testing.Str;

/**
 * Created by yzz on 2017/9/17.
 * mail:yzzstyle@163.com
 */

public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
