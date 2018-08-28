package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.TokenDao;
import com.yada.ssp.appServer.model.Token;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bjy on 2018/8/28.
 * TokenService
 */

@Service
public class TokenService {

    private final TokenDao tokenDao;

    @Autowired
    public TokenService(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    public void saveAndUpdate(Token token) {
        tokenDao.saveAndFlush(token);
    }

    public void deleteById(UserInfoPK userInfoPK) {
        tokenDao.deleteById(userInfoPK);
    }

    public void deleteByToken(String token){
        tokenDao.deleteByToken(token);
    }

    public Token findOne(UserInfoPK userInfoPK) {
        return tokenDao.getOne(userInfoPK);
    }

    public Token findByToken(String token){
        return tokenDao.findByToken(token);
    }
}
