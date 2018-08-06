package com.yada.ssp.appServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Set;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final TokenStore tokenStore;
    private final RoleProperties roleProperties;

    @Autowired
    public ResourceServerConfig(TokenStore tokenStore, RoleProperties roleProperties) {
        this.tokenStore = tokenStore;
        this.roleProperties = roleProperties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().authorizeRequests().antMatchers("/h2/**").permitAll();
        // 通过配置文件配置权限
        Set<String> roles = roleProperties.getRole().keySet();
        for (String role : roles) {
            String[] matchers = roleProperties.getRole().get(role).toArray(new String[0]);
            http.authorizeRequests().antMatchers(matchers).hasRole(role.toUpperCase());
        }
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
    }
}