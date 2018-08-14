package com.yada.ssp.appServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "T_B_APP_USER")
@IdClass(UserInfoPK.class)
public class UserInfo {

    //商户号
    @Id
    private String merNo;
    //登录名
    @Id
    private String loginName;
    //用户名
    @Column
    private String userName;
    //用户密码
    @JsonIgnore
    @Column
    private String passWord;
    //角色ID
    @Column
    private String roles;

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
