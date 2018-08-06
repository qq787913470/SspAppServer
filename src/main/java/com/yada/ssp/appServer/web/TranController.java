package com.yada.ssp.appServer.web;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tran")
public class TranController {

    @GetMapping(value = "/")
    public String index(OAuth2Authentication token) {
        return token.getOAuth2Request().getClientId();
    }

    @GetMapping(value = "/list")
    public String list(OAuth2Authentication token, @RequestBody String merNo, @RequestBody String tranDate) {
        String loginName = token.getOAuth2Request().getClientId();
        // TODO 通过某个表获取该用户下能查询的所有商户
        // TODO 如何查询集团商户数据
        return loginName;
    }

    @GetMapping(value = "/info/{tranId}")
    public String info(OAuth2Authentication token, @PathVariable("tranId") String tranId) {
        String loginName = token.getOAuth2Request().getClientId();
        return token.getOAuth2Request().getClientId();
    }
}
