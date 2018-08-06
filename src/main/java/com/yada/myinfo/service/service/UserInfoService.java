package com.yada.myinfo.service.service;

import com.yada.myinfo.service.dao.MerchantDao;
import com.yada.myinfo.service.dao.UserInfoDao;
import com.yada.myinfo.service.model.Merchant;
import com.yada.myinfo.service.model.UserInfo;
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
     * @param id     用户ID
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 更新是否成功
     */
    public boolean putPwd(String id, String oldPwd, String newPwd) {
        UserInfo user = userInfoDao.getOne(id);

        if (user.getPassWord() != null && user.getPassWord().equals(md5PasswordEncoder.encode(oldPwd))) {
            user.setPassWord(md5PasswordEncoder.encode(newPwd));
            userInfoDao.saveAndFlush(user);
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
