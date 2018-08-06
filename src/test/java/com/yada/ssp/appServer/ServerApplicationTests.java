package com.yada.ssp.appServer;

import com.yada.ssp.appServer.config.PwdEncoderConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PwdEncoderConfig.class)
public class ServerApplicationTests {

    @Autowired
    private PasswordEncoder md5PasswordEncoder;

    @Test
    public void md5Test() {
        Assert.assertEquals("96E79218965EB72C92A549DD5A330112", md5PasswordEncoder.encode("111111"));
    }

}
