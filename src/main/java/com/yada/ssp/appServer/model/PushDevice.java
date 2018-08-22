package com.yada.ssp.appServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bjy on 2018/8/21.
 * 推送设备表
 */

@Entity
@Table(name = "T_B_PUSH_DEVICE")
public class PushDevice {
    //设备ID
    @Id
    private String deviceId;
    //商户号
    @Column
    private String merNo;
    //推送方式
    @Column
    private String type;
    //推送平台
    @Column
    private String platform;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
