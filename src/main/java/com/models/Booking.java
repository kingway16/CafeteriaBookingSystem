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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author limwa
 */
@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findByBookingId", query = "SELECT b FROM Booking b WHERE b.bookingId = :bookingId"),
    @NamedQuery(name = "Booking.findByCustomerId", query = "SELECT b FROM Booking b WHERE b.customerId = :customerId"),
    @NamedQuery(name = "Booking.findByNotes", query = "SELECT b FROM Booking b WHERE b.notes = :notes"),
    @NamedQuery(name = "Booking.findByRating", query = "SELECT b FROM Booking b WHERE b.rating = :rating"),
    @NamedQuery(name = "Booking.findByFeedback", query = "SELECT b FROM Booking b WHERE b.feedback = :feedback"),
    @NamedQuery(name = "Booking.findByPending", query = "SELECT b FROM Booking b WHERE b.pending = :pending"),
    @NamedQuery(name = "Booking.findByCreationDate", query = "SELECT b FROM Booking b WHERE b.creationDate = :creationDate")})
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "booking_id")
    private Integer bookingId;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "notes")
    private String notes;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "pending")
    private String pending;
    @Basic(optional = false)
    @Column(name = "creation_date")
    private String creationDate;
    @JoinColumn(name = "dishes_id", referencedColumnName = "dishes_id")
    @ManyToOne(optional = false)
    private Dishes dishesId;
    @JoinColumn(name = "booking_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SysUser sysUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
    private List<SysOrder> sysOrderList;

    public Booking() {
    }

    public Booking(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Booking(Integer bookingId, int customerId, String creationDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.creationDate = creationDate;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Dishes getDishesId() {
        return dishesId;
    }

    public void setDishesId(Dishes dishesId) {
        this.dishesId = dishesId;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @XmlTransient
    public List<SysOrder> getSysOrderList() {
        return sysOrderList;
    }

    public void setSysOrderList(List<SysOrder> sysOrderList) {
        this.sysOrderList = sysOrderList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Booking[ bookingId=" + bookingId + " ]";
    }
    
}
