package com.yada.ssp.appServer.web;

import com.yada.ssp.appServer.model.TranInfo;
import com.yada.ssp.appServer.service.TranInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tran")
public class TranController {

    private final TranInfoService tranInfoService;

    @Autowired
    public TranController(TranInfoService tranInfoService) {
        this.tranInfoService = tranInfoService;
    }

    @GetMapping(value = "/list")
    public List<TranInfo> list(OAuth2Authentication token, @RequestBody String tranDate) {
        String merNo = token.getOAuth2Request().getClientId().split("@")[0];
        // TODO 通过某个表获取该用户下能查询的所有商户
        // TODO 如何查询集团商户数据
        return tranInfoService.getList(merNo, tranDate);
    }

    @GetMapping(value = "/info/{id}")
    public TranInfo info(OAuth2Authentication token, @PathVariable("id") String id) {
        String clientId = token.getOAuth2Request().getClientId();
        // TODO 判断merNo是否一致
        return tranInfoService.getInfo(id);
    }
}
