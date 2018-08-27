package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.PushDeviceDao;
import com.yada.ssp.appServer.model.PushDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjy on 2018/8/21.
 * 推送设备Service
 */

@Service
public class PushDeviceService {
    private final PushDeviceDao pushDeviceDao;

    @Autowired
    public PushDeviceService(PushDeviceDao pushDeviceDao) {
        this.pushDeviceDao = pushDeviceDao;
    }

    public PushDevice saveAndUpdate(String merNo, String type, String id, String platform) {
        PushDevice pushDevice = new PushDevice();
        pushDevice.setId(id);
        pushDevice.setMerNo(merNo);
        pushDevice.setType(type);
        pushDevice.setPlatform(platform);
        return pushDeviceDao.saveAndFlush(pushDevice);
    }

    public void delete(String id) {
        pushDeviceDao.deleteById(id);
    }

    public List<PushDevice> findListByMerNo(String merNo) {
        return pushDeviceDao.findByMerNo(merNo);
    }
}
