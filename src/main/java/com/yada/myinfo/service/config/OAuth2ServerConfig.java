package com.yada.myinfo.service.config;

import com.yada.myinfo.service.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;
    private final AccessTokenConverter accessTokenConverter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder md5PasswordEncoder;

    @Autowired
    public OAuth2ServerConfig(TokenStore tokenStore, AccessTokenConverter accessTokenConverter,
                              UserDetailsService userDetailsService, PasswordEncoder md5PasswordEncoder) {
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
        this.userDetailsService = userDetailsService;
        this.md5PasswordEncoder = md5PasswordEncoder;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(md5PasswordEncoder)
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(userDetailsService);
    }
}
