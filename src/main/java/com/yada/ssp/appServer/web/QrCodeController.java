package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.UserInfoPK;
import com.yada.ssp.appServer.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/qrCode")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @Autowired
    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    /**
     * 获取用户二维码
     *
     * @param token 授权信息
     * @return 二维码的内容
     */
    @PostMapping(value = "/")
    public Map<String, String> create(OAuth2Authentication token, @RequestBody String amt) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return qrCodeService.getQrCode(amt, new UserInfoPK(id[0], id[1]));
    }

    /**
     * 根据交易查询号查询结果
     *
     * @param token   授权信息
     * @param queryNo 交易查询号
     * @return 交易结果
     */
    @GetMapping(value = "/{queryNo}")
    public Map<String, String> query(OAuth2Authentication token, @PathVariable String queryNo) {
        String[] id = token.getOAuth2Request().getClientId().split("@");
        return qrCodeService.queryResult(queryNo, new UserInfoPK(id[0], id[1]));
    }
}
