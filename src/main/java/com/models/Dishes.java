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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author limwa
 */
@Entity
@Table(name = "dishes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dishes.findAll", query = "SELECT d FROM Dishes d"),
    @NamedQuery(name = "Dishes.findByDishesId", query = "SELECT d FROM Dishes d WHERE d.dishesId = :dishesId"),
    @NamedQuery(name = "Dishes.findByDishesName", query = "SELECT d FROM Dishes d WHERE d.dishesName = :dishesName"),
    @NamedQuery(name = "Dishes.findByIngredients", query = "SELECT d FROM Dishes d WHERE d.ingredients = :ingredients"),
    @NamedQuery(name = "Dishes.findByTotal", query = "SELECT d FROM Dishes d WHERE d.total = :total")})
public class Dishes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dishes_id")
    private Integer dishesId;
    @Column(name = "dishes_name")
    private String dishesName;
    @Column(name = "ingredients")
    private String ingredients;
    @Column(name = "total")
    private Integer total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dishesId")
    private List<Booking> bookingList;

    public Dishes() {
    }

    public Dishes(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dishesId != null ? dishesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dishes)) {
            return false;
        }
        Dishes other = (Dishes) object;
        if ((this.dishesId == null && other.dishesId != null) || (this.dishesId != null && !this.dishesId.equals(other.dishesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Dishes[ dishesId=" + dishesId + " ]";
    }
    
}
