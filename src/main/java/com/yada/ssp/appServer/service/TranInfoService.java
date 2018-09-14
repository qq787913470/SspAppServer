package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.TranInfoDao;
import com.yada.ssp.appServer.model.TranInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranInfoService {

    private final TranInfoDao tranInfoDao;

    @Autowired
    public TranInfoService(TranInfoDao tranInfoDao) {
        this.tranInfoDao = tranInfoDao;
    }

    @Cacheable(value = "tranInfo", unless = "#result == null")
    public TranInfo getInfo(String id) {
        return tranInfoDao.findById(id).orElse(null);
    }

    @Cacheable(value = "trans", unless = "#result.size() == 0",
            condition = "#tranDate ne T(com.yada.ssp.appServer.util.DateUtil).getCurDate()")
    public List<TranInfo> getList(String merNo, String tranDate) {
        return tranInfoDao.findByMerNoAndTranDateLike(merNo, tranDate + "%");
    }
}
