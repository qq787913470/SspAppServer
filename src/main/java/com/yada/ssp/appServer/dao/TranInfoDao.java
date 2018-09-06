package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.TranInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TranInfoDao extends JpaRepository<TranInfo, Long>, CrudRepository<TranInfo, Long> {

    List<TranInfo> findByMerNoAndTranDateLike(String merNo, String tranDate);
}