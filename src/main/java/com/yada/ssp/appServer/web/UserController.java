package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import com.yada.ssp.appServer.service.DeviceService;
import com.yada.ssp.appServer.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    private final UserInfoService userInfoService;
    private final DeviceService deviceService;

    @Autowired
    public UserController(UserInfoService userInfoService, DeviceService deviceService) {
        this.userInfoService = userInfoService;
        this.deviceService = deviceService;
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
     * @param token  授权信息
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 修改是否成功
     */
    @PutMapping(value = "/updatePwd")
    public boolean updatePwd(OAuth2Authentication token, @NotEmpty String oldPwd, @NotEmpty String newPwd) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return userInfoService.updatePwd(new UserInfoPK(id[0], id[1]), oldPwd, newPwd);
    }

    /**
     * 推送绑定
     *
     * @param token    授权信息
     * @param pushType 设备类型
     * @param deviceNo 设备ID
     * @param platform 设备平台
     * @return 用户信息
     */
    @PostMapping(value = "/bindPush")
    public UserInfo bindPush(OAuth2Authentication token, @NotEmpty String pushType, @NotEmpty String deviceNo, @NotEmpty String platform) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        if (deviceService.saveAndUpdate(id[0], id[1], pushType, deviceNo, platform) != null) {
            return userInfoService.getUserInfo(new UserInfoPK(id[0], id[1]));
        }
        return null;
    }

    /**
     * 推送解绑
     *
     * @param token 授权信息
     */
    @DeleteMapping(value = "/unBindPush")
    public boolean unBindPush(OAuth2Authentication token) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        deviceService.delete(new UserInfoPK(id[0], id[1]));
        return true;
    }
}
