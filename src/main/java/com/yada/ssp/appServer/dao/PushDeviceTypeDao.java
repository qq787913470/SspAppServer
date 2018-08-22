package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.PushDeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bjy on 2018/8/21.
 * 推送设备Dao
 */
public interface PushDeviceTypeDao extends JpaRepository<PushDeviceType, String>, CrudRepository<PushDeviceType, String> {

    List<PushDeviceType> findByMerNo(String merNo);
}
