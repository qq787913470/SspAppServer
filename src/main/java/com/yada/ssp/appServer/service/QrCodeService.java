package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCodeService {

    private final UserInfoService userInfoService;

    @Autowired
    public QrCodeService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public Map<String, String> getQrCode(String amt, UserInfoPK id) {
        UserInfo userInfo = userInfoService.getUserInfo(id);
        String termLsNo = String.valueOf(new Date().getTime());
        // 交易类型 动态二维码请求-01、交易渠道 目前默认填01
        return getQrCode("01", amt, termLsNo, userInfo.getCcyType().getCcyType(), userInfo.getTermNo(), userInfo.getMerNo(), "01");
    }

    /**
     * @param tranType 交易类型
     * @param tranAmt  金额
     * @param termLsNo 终端流水号
     * @param tranCry  币种
     * @param termNo   终端号
     * @param merNo    商户号
     * @param channel  交易渠道
     * @return 报文结果
     */
    private Map<String, String> getQrCode(String tranType, String tranAmt, String termLsNo,
                                          String tranCry, String termNo, String merNo, String channel) {
        // TODO 发起生成二维码请求
        Map<String, String> resp = new HashMap<>();
        resp.put("respCode", "00");
        resp.put("respMsg", "成功");
        resp.put("qrCode", "https://www.yadadev.com");
        resp.put("queryNo", "1234567890");
        resp.put("tranAmt", tranAmt);
        resp.put("tranCry", tranCry);
        resp.put("timeout", "60");
        return resp;
    }

    public Map<String, String> queryResult(String queryNo, UserInfoPK id) {
        String termNo = userInfoService.getTermNo(id);
        String termLsNo = String.valueOf(new Date().getTime());
        // 交易类型 动态二维码请求-02
        return queryResult("02", termLsNo, termNo, id.getMerNo(), queryNo);
    }

    /**
     * @param tranType 交易类型
     * @param termLsNo 终端流水号
     * @param termNo   终端号
     * @param merNo    商户号
     * @param queryNo  交易查询号
     * @return 报文结果
     */
    private Map<String, String> queryResult(String tranType, String termLsNo,
                                            String termNo, String merNo, String queryNo) {
        // TODO 发起交易查询请求
        Map<String, String> resp = new HashMap<>();
        resp.put("respCode", "00");
        resp.put("respMsg", "成功");
        resp.put("tranNo", "");
        resp.put("tranAmt", "");
        resp.put("tranCry", "");
        return null;
    }
}
