package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.UserInfo;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, UserInfoPK>, CrudRepository<UserInfo, UserInfoPK> {
}