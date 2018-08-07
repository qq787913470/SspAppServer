package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.config.PwdEncoderConfig;
import com.yada.ssp.appServer.dao.UserInfoDao;
import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserInfoService.class, PwdEncoderConfig.class})
public class UserInfoServiceTest {

    @MockBean
    private UserInfoDao userInfoDao;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PasswordEncoder md5PasswordEncoder;

    @Test
    public void updatePwd() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassWord(md5PasswordEncoder.encode("111111"));

        Mockito.when(userInfoDao.findById(Mockito.any(UserInfoPK.class)))
                .thenReturn(Optional.empty()).thenReturn(Optional.of(userInfo));

        // UserInfo is not find
        boolean result1 = userInfoService.updatePwd(new UserInfoPK("", ""), "111111", "222222");
        Assert.assertFalse(result1);

        // Password is ne
        boolean result2 = userInfoService.updatePwd(new UserInfoPK("", ""), "123456", "222222");
        Assert.assertFalse(result2);

        // Password is eq
        boolean result3 = userInfoService.updatePwd(new UserInfoPK("", ""), "111111", "222222");
        Assert.assertTrue(result3);
    }
}