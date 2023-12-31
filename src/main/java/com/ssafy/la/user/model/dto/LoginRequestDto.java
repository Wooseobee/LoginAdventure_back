package com.ssafy.la.user.model.dto;

import com.ssafy.la.util.exception.exceptions.MyException;

public class LoginRequestDto {

    private String userid, password, uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public void setUserid(String userid) {
        if (userid == null || userid.isEmpty()) {
            throw new MyException();
        }
        this.userid = userid;
    }

    public void setPassword(String password) {
        if (userid == null || userid.isEmpty()) {
            throw new MyException();
        }
        this.password = password;
    }

}
