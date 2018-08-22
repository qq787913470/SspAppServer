package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.PushDeviceTypeDao;
import com.yada.ssp.appServer.model.PushDeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjy on 2018/8/21.
 * 推送设备Service
 */

@Service
public class PushDeviceTypeService {
    private final PushDeviceTypeDao pushDeviceTypeDao;


    @Autowired
    public PushDeviceTypeService(PushDeviceTypeDao pushDeviceTypeDao) {
        this.pushDeviceTypeDao = pushDeviceTypeDao;
    }

    public void saveAndUpdate(OAuth2Authentication token, String type, String deviceId, String platform) {
        String merNo = token.getOAuth2Request().getClientId().split("@")[0];
        PushDeviceType pushDeviceType = new PushDeviceType();
        pushDeviceType.setDeviceId(deviceId);
        pushDeviceType.setMerNo(merNo);
        pushDeviceType.setType(type);
        pushDeviceType.setPlatform(platform);
        pushDeviceTypeDao.saveAndFlush(pushDeviceType);
    }

    public void delete(String deviceId) {
        pushDeviceTypeDao.deleteById(deviceId);
    }

    public List<PushDeviceType> findListByMerNo(String merNo) {
        return pushDeviceTypeDao.findByMerNo(merNo);
    }
}
