package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.MerchantDao;
import com.yada.ssp.appServer.dao.UserInfoDao;
import com.yada.ssp.appServer.model.Merchant;
import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
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
     * @param id 由merNo/loginName组成
     * @return 用户信息
     */
    public UserInfo getUserInfo(UserInfoPK id) {
        return userInfoDao.getOne(id);
    }

    /**
     * 更新用户密码
     *
     * @param id     由merNo/loginName组成
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 更新是否成功
     */
    public boolean putPwd(UserInfoPK id, String oldPwd, String newPwd) {
        UserInfo userInfo = userInfoDao.getOne(id);

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
