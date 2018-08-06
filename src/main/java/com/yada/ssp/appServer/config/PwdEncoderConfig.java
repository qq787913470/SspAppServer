package com.yada.ssp.appServer.config;

import com.yada.ssp.appServer.util.Md5keyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PwdEncoderConfig {

    @Bean
    public PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder(new Md5keyBean());
    }

    public class Md5PasswordEncoder implements PasswordEncoder {

        private Md5keyBean md5;

        Md5PasswordEncoder(Md5keyBean md5) {
            this.md5 = md5;
        }

        @Override
        public String encode(CharSequence rawPassword) {
            return md5.getkeyBeanofStr(rawPassword.toString());
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encodedPassword.equals(md5.getkeyBeanofStr(rawPassword.toString()));
        }
    }
}
