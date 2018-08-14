package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.UserInfoDao;
import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserInfoService {

    private final UserInfoDao userInfoDao;
    private final PasswordEncoder md5PasswordEncoder;

    @Autowired
    public UserInfoService(UserInfoDao userInfoDao, PasswordEncoder md5PasswordEncoder) {
        this.userInfoDao = userInfoDao;
        this.md5PasswordEncoder = md5PasswordEncoder;
    }

    /**
     * 获取用户信息
     *
     * @param id 由merNo/loginName组成
     * @return 用户信息
     */
    public UserInfo getUserInfo(UserInfoPK id) {
        return userInfoDao.findById(id).orElse(null);
    }

    /**
     * 更新用户密码
     *
     * @param id     由merNo/loginName组成
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 更新是否成功
     */
    public boolean updatePwd(UserInfoPK id, @RequestBody String oldPwd, String newPwd) {

        UserInfo userInfo = userInfoDao.findById(id).orElse(new UserInfo());

        if (userInfo.getPassWord() != null && userInfo.getPassWord().equals(md5PasswordEncoder.encode(oldPwd))) {
            userInfo.setPassWord(md5PasswordEncoder.encode(newPwd));
            userInfoDao.saveAndFlush(userInfo);
            return true;
        } else {
            return false;
        }
    }

}
