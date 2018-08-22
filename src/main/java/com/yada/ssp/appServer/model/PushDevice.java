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
    private String id;
    //商户号
    @Column
    private String merNo;
    //推送服务标识
    @Column
    private String type;
    //设备平台
    @Column
    private String platform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
