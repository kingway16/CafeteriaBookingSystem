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
@Table(name = "sys_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysUser.findAll", query = "SELECT s FROM SysUser s"),
    @NamedQuery(name = "SysUser.findByUserId", query = "SELECT s FROM SysUser s WHERE s.userId = :userId"),
    @NamedQuery(name = "SysUser.findByEmail", query = "SELECT s FROM SysUser s WHERE s.email = :email"),
    @NamedQuery(name = "SysUser.findByPassword", query = "SELECT s FROM SysUser s WHERE s.password = :password"),
    @NamedQuery(name = "SysUser.findByRole", query = "SELECT s FROM SysUser s WHERE s.role = :role"),
    @NamedQuery(name = "SysUser.findByName", query = "SELECT s FROM SysUser s WHERE s.name = :name"),
    @NamedQuery(name = "SysUser.findByAddress", query = "SELECT s FROM SysUser s WHERE s.address = :address"),
    @NamedQuery(name = "SysUser.findByTelno", query = "SELECT s FROM SysUser s WHERE s.telno = :telno"),
    @NamedQuery(name = "SysUser.findByDob", query = "SELECT s FROM SysUser s WHERE s.dob = :dob"),
    @NamedQuery(name = "SysUser.findByGender", query = "SELECT s FROM SysUser s WHERE s.gender = :gender"),
    @NamedQuery(name = "SysUser.findByCreationDate", query = "SELECT s FROM SysUser s WHERE s.creationDate = :creationDate")})
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "telno")
    private String telno;
    @Column(name = "dob")
    private String dob;
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Column(name = "creation_date")
    private String creationDate;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sysUser")
    private Booking booking;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId")
    private List<SysOrder> sysOrderList;

    public SysUser() {
    }

    public SysUser(Integer userId) {
        this.userId = userId;
    }

    public SysUser(Integer userId, String email, String password, String role, String name, String creationDate) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysUser)) {
            return false;
        }
        SysUser other = (SysUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.SysUser[ userId=" + userId + " ]";
    }
    
}
