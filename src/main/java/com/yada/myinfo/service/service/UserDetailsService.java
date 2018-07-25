package com.yada.myinfo.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsService implements ClientDetailsService {

    private final PasswordEncoder md5PasswordEncoder;

    @Autowired
    public UserDetailsService(PasswordEncoder md5PasswordEncoder) {
        this.md5PasswordEncoder = md5PasswordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        // TODO 从数据库中获取用户信息
        if ("user".equals(clientId)) {
            BaseClientDetails details = new BaseClientDetails();
            details.setClientId(clientId);
            details.setClientSecret(md5PasswordEncoder.encode("123"));
            details.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            details.setAuthorizedGrantTypes(Collections.singletonList("client_credentials"));
            details.setScope(Collections.singletonList("all"));
            details.setAccessTokenValiditySeconds(1200);

            return details;
        }

        if("admin".equals(clientId)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            BaseClientDetails details = new BaseClientDetails();
            details.setClientId(clientId);
            details.setClientSecret(md5PasswordEncoder.encode("123"));
            details.setAuthorities(authorities);
            details.setAuthorizedGrantTypes(Collections.singletonList("client_credentials"));
            details.setScope(Collections.singletonList("all"));
            details.setAccessTokenValiditySeconds(1200);

            return details;
        }

        throw new UsernameNotFoundException(clientId);
    }
}
