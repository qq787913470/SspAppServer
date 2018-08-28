package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.Token;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bjy on 2018/8/27.
 * TokenDao
 */
public interface TokenDao extends JpaRepository<Token, UserInfoPK>, CrudRepository<Token, UserInfoPK> {

    Token findByToken(String token);

    void deleteByToken(String token);
}
