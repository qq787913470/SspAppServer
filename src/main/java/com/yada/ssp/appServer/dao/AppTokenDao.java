package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.AppToken;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bjy on 2018/8/27.
 * AppTokenDao
 */
public interface AppTokenDao extends JpaRepository<AppToken, UserInfoPK>, CrudRepository<AppToken, UserInfoPK> {
}
