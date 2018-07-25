package com.yada.myinfo.service.web;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tran")
public class TranController {

    @GetMapping(value = "/")
    public String index(OAuth2Authentication token) {
        return token.getOAuth2Request().getClientId();
    }
}
