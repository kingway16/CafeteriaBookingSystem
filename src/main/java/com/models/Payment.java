package com.models;

public class Payment {

    private Integer id;
    private Integer bookingId;
    private Integer orderId;
    private String trxDate;
    private Integer total;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        String payment = "[" + this.id + "," + this.bookingId + "," + this.orderId + "," + this.trxDate + "," + this.total + "," + this.name + "]";
        return payment;
    }

}
