package com.zhiyou100.model;

import java.util.Date;

/**
 * Created by lijike 2016/12/6.
 */
public class User {

    private String username;
    private String realName;
    private Integer age;
    private Date birthday;

    public User() {
    }

    public User(String username, String realName, Integer age) {
        this.username = username;
        this.realName = realName;
        this.age = age;
    }

    public User(String username, String realName, Integer age, Date birthday) {
        this.username = username;
        this.realName = realName;
        this.age = age;
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
