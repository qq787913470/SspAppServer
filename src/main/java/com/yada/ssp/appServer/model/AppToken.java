package com.yada.ssp.appServer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bjy on 2018/8/27.
 * APPToken
 */

@Entity
@Table(name = "T_B_APP_TOKEN")
@IdClass(UserInfoPK.class)
public class AppToken implements Serializable {
    //商户号
    @Id
    @Column(nullable = false)
    private String merNo;
    //登录名
    @Id
    @Column(nullable = false)
    private String loginName;
    //token
    @Column
    private String token;
    //授权ID
    @Column
    private String authenticationId;
    //用户名
    @Column
    private String userName;
    //客户端ID
    @Column
    private String clientId;
    //授权信息
    @Column
    private String authentication;
    //更新Token
    @Column
    private String refreshToken;


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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
