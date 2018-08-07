package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import com.yada.ssp.appServer.service.MerchantService;
import com.yada.ssp.appServer.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserInfoService userInfoService;
    private final MerchantService merchantService;

    @Autowired
    public UserController(UserInfoService userInfoService, MerchantService merchantService) {
        this.userInfoService = userInfoService;
        this.merchantService = merchantService;
    }

    /**
     * 获取用户信息
     *
     * @param token 授权信息
     * @return 商户信息
     */
    @GetMapping(value = "/")
    public UserInfo index(OAuth2Authentication token) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return userInfoService.getUserInfo(new UserInfoPK(id[0], id[1]));
    }

    /**
     * 获取商户信息
     *
     * @param token 授权信息
     * @return 子商户信息
     */
    @GetMapping(value = "/subMer")
    public Map<String, String> subMerList(OAuth2Authentication token) {
        String merNo = token.getOAuth2Request().getClientId().split("@")[0];
        return merchantService.getSubMer(merNo);
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
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return userInfoService.updatePwd(new UserInfoPK(id[0], id[1]), oldPwd, newPwd);
    }
}
