package com.yada.ssp.appServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bjy on 2018/9/5.
 * 币种表
 */

@Entity
@Table(name = "T_D_CCY_TYPE")
public class CcyType {

    public static final String TABLE_ALIAS = "币种";
    public static final String ALIAS_CCY_TYPE = "币种类型";
    public static final String ALIAS_CCY_NAME = "币种中文名称";
    public static final String ALIAS_CCY_ENAME = "币种名称英文缩写";
    public static final String ALIAS_CCY_SYMBOL = "币种符号";

    @Id
    @Column(nullable = false, length = 3)
    private String ccyType;
    @Column
    private String ccyName;
    @Column
    private String ccyEname;
    @Column
    private String ccySymbol;

    public String getCcySymbol() {
        return ccySymbol;
    }

    public void setCcySymbol(String ccySymbol) {
        this.ccySymbol = ccySymbol;
    }

    public String getCcyType() {
        return ccyType;
    }

    public void setCcyType(String ccyType) {
        this.ccyType = ccyType;
    }

    public String getCcyName() {
        return ccyName;
    }

    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    public String getCcyEname() {
        return ccyEname;
    }

    public void setCcyEname(String ccyEname) {
        this.ccyEname = ccyEname;
    }
}
