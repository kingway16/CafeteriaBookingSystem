package com.models;
import java.io.Serializable;

public class Booking implements Serializable {

    private Integer bookingid;
    private Integer customerid;
    private String name;
    private Integer dishid;
    private Integer orderid;
    private String dishname;
    private String notes;
    private Integer rating;
    private String feedback;
    private String pending;
    private Integer total;
    private String creationDate;

    public Booking() {}

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDishid() {
        return dishid;
    }

    public void setDishid(Integer dishid) {
        this.dishid = dishid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString()
    {
        String booking = "[" + this.bookingid + "," + this.customerid + "," + this.orderid + "," + this.name + "," + this.dishid + "," + this.dishname + "," +this.notes + "," + this.rating + "," + this.feedback + "," + this.total + "," + this.pending + "," + this.creationDate + "]";
        return booking;
    }
}
