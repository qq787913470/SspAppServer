package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.Device;
import com.yada.ssp.appServer.model.UserInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bjy on 2018/8/21.
 * 推送设备Dao
 */
public interface DeviceDao extends JpaRepository<Device, UserInfoPK>, CrudRepository<Device, UserInfoPK> {

    List<Device> findByMerNo(String merNo);
}
