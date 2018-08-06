package com.yada.myinfo.service.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "T_B_MERCHANT")
public class Merchant {

    @Id
    private String merNo;

    @Column
    private String merName;

    @Column
    private String merType;

    @ManyToOne
    @JoinColumn(name = "P_MER_NO", referencedColumnName = "MER_NO")
    private Merchant merchant;

    @OneToMany(mappedBy = "merchant")
    @OrderBy("merNo ASC")
    private Set<Merchant> children;

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Set<Merchant> getChildren() {
        return children;
    }

    public void setChildren(Set<Merchant> children) {
        this.children = children;
    }
}
