package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    // TODO 获取商户信息
    @GetMapping(value = "/")
    public String index(OAuth2Authentication token) {
        return token.getOAuth2Request().getClientId();
    }

    /**
     * 修改商户密码
     *
     * @param token  授权信息
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 修改是否成功
     */
    @PutMapping(value = "/updatePwd")
    public boolean updatePwd(OAuth2Authentication token, @RequestBody String oldPwd, @RequestBody String newPwd) {
        String clientId = token.getOAuth2Request().getClientId();
        return userInfoService.putPwd(clientId, oldPwd, newPwd);
    }
}
