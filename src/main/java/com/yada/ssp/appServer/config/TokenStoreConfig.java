package com.yada.ssp.appServer.config;

import com.yada.ssp.appServer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class TokenStoreConfig {
    @Autowired
    private TokenService tokenService;

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

    @Bean
    public TokenStore tokenStore() {
        return new ClientTokenStore(tokenService);
    }
}
