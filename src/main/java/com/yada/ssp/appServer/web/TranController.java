package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.TranInfo;
import com.yada.ssp.appServer.service.MerchantService;
import com.yada.ssp.appServer.service.TranInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{merNo}")
    public List<TranInfo> list(OAuth2Authentication token, @PathVariable("merNo") String merNo, @RequestParam String tranDate) {
        // TODO 如何查询集团商户数据
        String pMerNo = token.getOAuth2Request().getClientId().split("@")[0];
        if (merchantService.checkSubMer(pMerNo, merNo)) {
            return tranInfoService.getList(merNo, tranDate);
        }
        return null;
    }

    @GetMapping(value = "/{merNo}/{id}")
    public TranInfo info(OAuth2Authentication token, @PathVariable("merNo") String merNo, @PathVariable("id") String id) {
        String pMerNo = token.getOAuth2Request().getClientId().split("@")[0];
        if (merchantService.checkSubMer(pMerNo, merNo)) {
            return tranInfoService.getInfo(id);
        }
        return null;
    }
}
