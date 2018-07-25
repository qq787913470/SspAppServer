package com.yada.myinfo.service.web;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    // TODO 获取商户信息
    @GetMapping(value = "/")
    public String index(OAuth2Authentication token) {
        return token.getOAuth2Request().getClientId();
    }

    // TODO 修改商户密码
    @PutMapping(value = "/")
    public String putPwd(String oldPwd, String newPwd) {
        return "";
    }
}
