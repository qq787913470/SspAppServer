package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, String> {

    UserInfo findByMerNoAndLoginName(String merNo, String loginName);
}