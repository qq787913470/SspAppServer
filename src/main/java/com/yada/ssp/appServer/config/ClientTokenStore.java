package com.yada.ssp.appServer.config;

import com.yada.ssp.appServer.model.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 只支持OAuth2.0的client_credentials模式
 */
public class ClientTokenStore implements TokenStore {

    private Map<String, Token> tokenStore = new HashMap<>();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenValue) {
        OAuth2Authentication auth = null;
        Token tokenInfo = tokenStore.get(tokenValue);
        if(tokenInfo != null) {
            OAuth2Request oAuth2Request = new OAuth2Request(
                    Collections.singletonMap("grant_type", "client_credentials"),
                    tokenInfo.getMerNo() + "@" + tokenInfo.getLoginName(),
                    AuthorityUtils.createAuthorityList(tokenInfo.getRoles().split(",")),
                    true, Collections.singleton("all"),
                    null, null, null, null);
            auth = new OAuth2Authentication(oAuth2Request, null);
        }
        return auth;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        DefaultOAuth2AccessToken token = null;
        Token tokenInfo = tokenStore.get(tokenValue);
        if(tokenInfo != null) {
            token = new DefaultOAuth2AccessToken(tokenValue);
            token.setExpiration(tokenInfo.getExpiration());
        }
        return token;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String[] client = authentication.getOAuth2Request().getClientId().split("@");
        Token tokenInfo = new Token();
        tokenInfo.setMerNo(client[0]);
        tokenInfo.setLoginName(client[1]);
        tokenInfo.setToken(token.getValue());
        StringBuilder roles = new StringBuilder();
        for(GrantedAuthority ga: authentication.getAuthorities()) {
            roles.append(",").append(ga.getAuthority());
        }
        tokenInfo.setRoles(roles.toString().replaceFirst(",", ""));
        tokenInfo.setExpiration(token.getExpiration());
        // TODO 数据库存储
        tokenStore.put(token.getValue(), tokenInfo);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        tokenStore.remove(token.getValue());
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {

    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {

    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {

    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        return null;
    }
}
