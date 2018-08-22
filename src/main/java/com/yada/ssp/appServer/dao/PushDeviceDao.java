package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.PushDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bjy on 2018/8/21.
 * 推送设备Dao
 */
public interface PushDeviceDao extends JpaRepository<PushDevice, String>, CrudRepository<PushDevice, String> {

    List<PushDevice> findByMerNo(String merNo);
}
