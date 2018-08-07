package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.UserInfoDao;
import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserDetailsService.class)
public class UserDetailsServiceTest {

    @Value("${tokenValiditySeconds}")
    private int tokenValiditySeconds;
    @MockBean
    private UserInfoDao userInfoDao;
    @Autowired
    private UserDetailsService service;

    @Test
    public void loadClientByClientId() {
        UserInfo userInfo = new UserInfo();
        userInfo.setMerNo("104000100020003");
        userInfo.setLoginName("admin");
        userInfo.setPassWord("password");
        userInfo.setRoles("admin,user,test");

        Mockito.when(userInfoDao.findById(Mockito.any(UserInfoPK.class)))
                .thenReturn(Optional.of(userInfo)).thenReturn(Optional.empty());
        String clientId = "104000100020003@admin";

        // userInfo is find
        ClientDetails details = service.loadClientByClientId(clientId);
        Assert.assertEquals(clientId, details.getClientId());
        Assert.assertEquals(userInfo.getPassWord(), details.getClientSecret());
        Assert.assertEquals(3, details.getAuthorities().size());
        Assert.assertEquals(1, details.getAuthorizedGrantTypes().size());
        Assert.assertEquals(tokenValiditySeconds, details.getAccessTokenValiditySeconds().intValue());

        // userInfo is not find
        try {
            service.loadClientByClientId(clientId);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UsernameNotFoundException);
            Assert.assertEquals(clientId, e.getMessage());
        }
    }
}