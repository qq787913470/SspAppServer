package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.TranInfo;
import com.yada.ssp.appServer.service.MerchantService;
import com.yada.ssp.appServer.service.TranInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/tran")
public class TranController {

    private final TranInfoService tranInfoService;
    private final MerchantService merchantService;

    @Autowired
    public TranController(TranInfoService tranInfoService, MerchantService merchantService) {
        this.tranInfoService = tranInfoService;
        this.merchantService = merchantService;
    }

    /**
     * 获取商户信息
     */
    @GetMapping(value = "/subMer")
    public Map<String, String> subMerList(OAuth2Authentication token) {
        String merNo = token.getOAuth2Request().getClientId().split("@")[0];
        return merchantService.getSubMer(merNo);
    }

    @GetMapping(value = "/{merNo}")
    public List<TranInfo> list(OAuth2Authentication token, @PathVariable("merNo") String merNo, @RequestParam String tranDate) {
        String pMerNo = token.getOAuth2Request().getClientId().split("@")[0];
        if (merchantService.checkSubMer(pMerNo, merNo)) {
            return tranInfoService.getList(merNo, tranDate);
        }
        return null;
    }

    @GetMapping(value = "/{merNo}/{id}")
    public TranInfo info(OAuth2Authentication token, @PathVariable("merNo") String merNo, @PathVariable("id") Long id) {
        String pMerNo = token.getOAuth2Request().getClientId().split("@")[0];
        if (merchantService.checkSubMer(pMerNo, merNo)) {
            return tranInfoService.getInfo(id);
        }
        return null;
    }
}
