package com.yada.myinfo.service.dao;

import com.yada.myinfo.service.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, String> {

    UserInfo findByMerNoAndLoginName(String merNo, String loginName);
}