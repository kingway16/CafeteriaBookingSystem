/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author limwa
 */
@Entity
@Table(name = "sys_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysPayment.findAll", query = "SELECT s FROM SysPayment s"),
    @NamedQuery(name = "SysPayment.findByPaymentId", query = "SELECT s FROM SysPayment s WHERE s.paymentId = :paymentId"),
    @NamedQuery(name = "SysPayment.findByTrxDate", query = "SELECT s FROM SysPayment s WHERE s.trxDate = :trxDate")})
public class SysPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_id")
    private Integer paymentId;
    @Column(name = "trx_date")
    private String trxDate;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private SysOrder orderId;

    public SysPayment() {
    }

    public SysPayment(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public SysOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(SysOrder orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysPayment)) {
            return false;
        }
        SysPayment other = (SysPayment) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.SysPayment[ paymentId=" + paymentId + " ]";
    }
    
}
