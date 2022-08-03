package com.facades;

import com.models.Booking;

import java.util.List;

public interface BookingFacadeLocal {

    void create(Booking booking);

    void edit(Booking booking);

    void remove(Booking booking);

}
