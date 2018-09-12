package com.yada.ssp.appServer.model;

import javax.persistence.*;

@Entity
@Table(name = "T_V_TRANS")
public class TranInfo {

    @Id
    private Long traceNo; // 流水号
    @Column
    private Long batchNo; // 批次号
    @Column
    private Long invoiceNo; // 票据号
    @Column
    private Float tranAmt; // 交易金额
    @Column
    private String tranType; // 交易类型
    @Column
    private String tranDate; // 交易时间
    @Column
    private String channel; // 交易渠道
    @Column
    private String cardNo; // 卡号
    @Column
    private String merNo; // 商户号
    @Column
    private String termNo; // 终端号
    @Column
    private String authNo; // 授权号
    @Column
    private String rrn; // 参考号
    @Column
    private String mcc; // mcc码
    @Column
    private String respCode; // 返回码

    public Long getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(Long traceNo) {
        this.traceNo = traceNo;
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Float getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(Float tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
}
