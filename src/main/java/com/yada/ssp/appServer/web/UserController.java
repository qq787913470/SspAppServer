package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import com.yada.ssp.appServer.service.UserInfoService;
import com.yada.ssp.appServer.view.UpdatePwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 获取用户信息
     *
     * @param token 授权信息
     * @return 商户信息
     */
    @GetMapping
    public UserInfo index(OAuth2Authentication token) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return userInfoService.getUserInfo(new UserInfoPK(id[0], id[1]));
    }

    /**
     * 修改商户密码
     *
     * @param token 授权信息
     * @param pwd   旧密码、新密码
     * @return 修改是否成功
     */
    @PutMapping(value = "/updatePwd")
    public boolean updatePwd(OAuth2Authentication token, @RequestBody @Validated UpdatePwd pwd) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return userInfoService.updatePwd(new UserInfoPK(id[0], id[1]), pwd.getOldPwd(), pwd.getNewPwd());
    }

    /**
     * 获取用户二维码
     *
     * @param token 授权信息
     * @return 二维码的内容
     */
    @GetMapping(value = "/qrCode")
    public String getQrCode(OAuth2Authentication token) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        // TODO 生成二维码
        return id[0];
    }
}
