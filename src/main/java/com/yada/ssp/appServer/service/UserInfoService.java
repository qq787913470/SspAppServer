package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.MerchantDao;
import com.yada.ssp.appServer.dao.UserInfoDao;
import com.yada.ssp.appServer.model.Merchant;
import com.yada.ssp.appServer.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoService {

    private final UserInfoDao userInfoDao;
    private final MerchantDao merchantDao;
    private final PasswordEncoder md5PasswordEncoder;

    @Autowired
    public UserInfoService(UserInfoDao userInfoDao, MerchantDao merchantDao, PasswordEncoder md5PasswordEncoder) {
        this.userInfoDao = userInfoDao;
        this.merchantDao = merchantDao;
        this.md5PasswordEncoder = md5PasswordEncoder;
    }

    /**
     * 获取用户信息
     *
     * @param merNo     商户号
     * @param loginName 登录名称
     * @return 用户信息
     */
    public UserInfo getUserInfo(String merNo, String loginName) {
        return userInfoDao.findByMerNoAndLoginName(merNo, loginName);
    }

    /**
     * 更新用户密码
     *
     * @param clientId 由merNo@loginName组成
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return 更新是否成功
     */
    public boolean putPwd(String clientId, String oldPwd, String newPwd) {
        String[] client = clientId.split("@");
        UserInfo userInfo = userInfoDao.findByMerNoAndLoginName(client[0], client[1]);

        if (userInfo.getPassWord() != null && userInfo.getPassWord().equals(md5PasswordEncoder.encode(oldPwd))) {
            userInfo.setPassWord(md5PasswordEncoder.encode(newPwd));
            userInfoDao.saveAndFlush(userInfo);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下级商户
     *
     * @param merNo 集团商户号
     * @return 下级商户
     */
    public Map<String, String> getSubMer(String merNo) {
        Merchant merchant = merchantDao.getOne(merNo);
        Map<String, String> subMer = new HashMap<>();
        for (Merchant mer : merchant.getChildren()) {
            subMer.put(mer.getMerNo(), mer.getMerName());
        }
        return subMer;
    }

}
