package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return getQrCode("01", amt, termLsNo, userInfo.getCry(), userInfo.getTermNo(), userInfo.getMerNo(), "01");
    }

    /**
     *
     * @param tranType 交易类型 动态二维码请求-01
     * @param tranAmt 金额
     * @param termLsNo 终端流水号
     * @param tranCry 币种
     * @param termNo 终端号
     * @param merNo 商户号
     * @param channel 交易渠道 目前默认填01
     * @return 报文结果
     */
    private Map<String, String> getQrCode(String tranType, String tranAmt, String termLsNo,
                                          String tranCry, String termNo, String merNo, String channel) {
        // TODO 发起生成二维码请求
        return null;
    }

    public Map<String, String> queryResult(String queryNo, UserInfoPK id) {
        String termNo = userInfoService.getTermNo(id);
        String termLsNo = String.valueOf(new Date().getTime());
        return queryResult("02", termLsNo, termNo, id.getMerNo(), queryNo);
    }

    /**
     *
     * @param tranType 交易类型 动态二维码请求-02
     * @param termLsNo 终端流水号
     * @param termNo 终端号
     * @param merNo 商户号
     * @param queryNo 交易查询号
     * @return 报文结果
     */
    private Map<String, String> queryResult(String tranType, String termLsNo,
                                            String termNo, String merNo, String queryNo) {
        // TODO 发起交易查询请求
        return null;
    }
}
