package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.TranInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TranInfoDao extends JpaRepository<TranInfo, String>, JpaSpecificationExecutor<TranInfo> {

    List<TranInfo> findByMerNo(String merNo);

    List<TranInfo> findByMerNoAndTranDate(String merNo, String tranDate);
}