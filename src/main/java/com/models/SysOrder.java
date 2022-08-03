/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author limwa
 */
@Entity
@Table(name = "sys_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysOrder.findAll", query = "SELECT s FROM SysOrder s"),
    @NamedQuery(name = "SysOrder.findByOrderId", query = "SELECT s FROM SysOrder s WHERE s.orderId = :orderId"),
    @NamedQuery(name = "SysOrder.findByOrderStatus", query = "SELECT s FROM SysOrder s WHERE s.orderStatus = :orderStatus")})
public class SysOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_status")
    private String orderStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private List<SysPayment> sysPaymentList;
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    @ManyToOne(optional = false)
    private Booking bookingId;
    @JoinColumn(name = "staff_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private SysUser staffId;

    public SysOrder() {
    }

    public SysOrder(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @XmlTransient
    public List<SysPayment> getSysPaymentList() {
        return sysPaymentList;
    }

    public void setSysPaymentList(List<SysPayment> sysPaymentList) {
        this.sysPaymentList = sysPaymentList;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public SysUser getStaffId() {
        return staffId;
    }

    public void setStaffId(SysUser staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysOrder)) {
            return false;
        }
        SysOrder other = (SysOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.SysOrder[ orderId=" + orderId + " ]";
    }
    
}
