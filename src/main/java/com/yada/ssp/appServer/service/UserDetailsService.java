package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.UserInfoDao;
import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsService implements ClientDetailsService {

    @Value("${tokenValiditySeconds}")
    private int accessTokenValiditySeconds = 30 * 24 * 60 * 60;

    private final UserInfoDao userInfoDao;

    @Autowired
    public UserDetailsService(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        String[] id = clientId.split("@");
        UserInfo user = userInfoDao.findById(new UserInfoPK(id[0], id[1])).orElse(new UserInfo());

        if (user.getRoles() != null) {
            String[] roles = user.getRoles().split(",");
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            }

            BaseClientDetails details = new BaseClientDetails();
            details.setClientId(clientId);
            details.setClientSecret(user.getPassWord());
            details.setAuthorities(authorities);
            details.setAuthorizedGrantTypes(Collections.singletonList("client_credentials"));
            details.setScope(Collections.singletonList("all"));
            // 设置过期时间
            details.setAccessTokenValiditySeconds(accessTokenValiditySeconds);

            return details;
        }

        throw new UsernameNotFoundException(clientId);
    }
}
