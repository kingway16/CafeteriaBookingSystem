package com.facades;

import com.models.Booking;
import com.models.Dishes;
import com.models.SysOrder;
import com.models.SysUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class BookingFacade extends AbstractFacade<Booking> implements BookingFacadeLocal{

    public BookingFacade(Class<Booking> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return null;
    }

    public static List<com.models.Booking> doSelectAllBookingInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException, ServletException {

        List<com.models.Booking> list = new LinkedList<Booking>();
        String selectAllBookingInfo = "select b.*, u.name, d.total, d.dishes_name from booking b inner join sys_user u on b.customer_id = u.user_id inner join dishes d on d.dishes_id = b.dishes_id";
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            com.models.Booking booking = new com.models.Booking();
            SysUser user = new SysUser();
            Dishes dish = new Dishes();
            user.setUserId(reader.getInt("customer_id"));
            user.setName(reader.getString("name"));
            dish.setTotal(reader.getInt("total"));
            dish.setDishesId(reader.getInt("dishes_id"));
            dish.setDishesName(reader.getString("dishes_name"));


            booking.setBookingId(reader.getInt("booking_id"));
            booking.setSysUser(user);
            booking.setDishesId(dish);
            booking.setNotes(reader.getString("notes"));
            booking.setRating(reader.getInt("rating"));
            booking.setFeedback(reader.getString("feedback"));
            booking.setPending(reader.getString("pending"));
            booking.setCreationDate(reader.getString("creation_date"));
            list.add(booking);
        }
        return list;
    }

    public static int onCreateNewBooking(Integer userid, Integer dishId, String notes, Connection con) throws SQLException {
        Date date = new Date();
        String createNewBooking = "INSERT INTO booking (customer_id, dishes_id, notes, pending, creation_date) VALUES (" + userid + "," + dishId + ",'" + notes + "','Y','" + date +"')";
        PreparedStatement statement = con.prepareStatement(createNewBooking);
        int reader = statement.executeUpdate();
        System.out.println("onCreateNewBooking :" + reader);
        return reader;
    }

    public static void doUpdateBookingRateAndFeedback(Integer rate, String feedback, Integer bookingId, Connection con) throws SQLException, IOException, ServletException {

        String selectAllBookingInfo = "UPDATE booking SET rating = " + rate + " , feedback = '" + feedback + "' WHERE booking_id =" + bookingId;
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo.toString());
        int result = statement.executeUpdate();
    }

    public static int onDeleteBooking(Integer id, Connection con) throws SQLException {
        String deleteBooking = "delete from booking where booking_id = " + id;
        PreparedStatement statement = con.prepareStatement(deleteBooking);
        int result = statement.executeUpdate();
        return result;
    }

    public static List<com.models.Booking> doSelectedBookingInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<com.models.Booking> list = new LinkedList<com.models.Booking>();
        String selectAllBookingInfo = "select b.*, d.dishes_name, s.name, d.total from booking b inner join dishes d on d.dishes_id = b.dishes_id inner join sys_user s on s.user_id = b.customer_id where b.customer_id =" + id;
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            SysUser user = new SysUser();
            Dishes dishes = new Dishes();

            user.setUserId(reader.getInt("customer_id"));
            user.setName(reader.getString("name"));
            dishes.setDishesId(reader.getInt("dishes_id"));
            dishes.setDishesName(reader.getString("dishes_name"));
            dishes.setTotal(reader.getInt("total"));

            com.models.Booking booking = new com.models.Booking();
            booking.setBookingId(reader.getInt("booking_id"));
            booking.setSysUser(user);
            booking.setDishesId(dishes);
            booking.setNotes(reader.getString("notes"));
            booking.setRating(reader.getInt("rating"));
            booking.setFeedback(reader.getString("feedback"));
            booking.setPending(reader.getString("pending"));
            booking.setCreationDate(reader.getString("creation_date"));
            list.add(booking);
        }
        System.out.println("result:" + list);
        return list;
    }

    public static List<SysOrder> doSelectedBookingInfo1(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<SysOrder> list = new LinkedList<SysOrder>();
        String selectBookingInfo = "select b.*, d.dishes_name, s.name, d.total, o.order_id from booking b inner join dishes d on d.dishes_id = b.dishes_id inner join sys_user s on s.user_id = b.customer_id inner join sys_order o on o.booking_id = b.booking_id where o.staff_id =" + id + " and b.pending = 'N'";
        PreparedStatement statement = con.prepareStatement(selectBookingInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            com.models.Booking booking = new com.models.Booking();
            SysUser user = new SysUser();
            Dishes dish = new Dishes();
            SysOrder order = new SysOrder();

            user.setName(reader.getString("name"));
            user.setUserId(reader.getInt("customer_id"));

            dish.setDishesId(reader.getInt("dishes_id"));
            dish.setTotal(reader.getInt("total"));
            dish.setDishesName(reader.getString("dishes_name"));

            booking.setBookingId(reader.getInt("booking_id"));
            booking.setNotes(reader.getString("notes"));
            booking.setRating(reader.getInt("rating"));
            booking.setFeedback(reader.getString("feedback"));
            booking.setPending(reader.getString("pending"));
            booking.setCreationDate(reader.getString("creation_date"));
            booking.setSysUser(user);
            booking.setDishesId(dish);

            order.setOrderId(reader.getInt("order_id"));
            order.setBookingId(booking);
            list.add(order);
        }
        return list;
    }

    public static List<com.models.Booking> doSelectedBookingInfo3(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<com.models.Booking> list = new LinkedList<com.models.Booking>();
        String selectAllBookingInfo = "select * from booking where customer_id =" + id;
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            Dishes dish = new Dishes();

            dish.setTotal(result.getInt("total"));
            com.models.Booking booking = new com.models.Booking();
            booking.setBookingId(result.getInt("booking_id"));
            booking.setCustomerId(result.getInt("customer_id"));
            booking.setRating(result.getInt("rating"));
            booking.setFeedback(result.getString("feedback"));
            booking.setDishesId(dish);
            booking.setCreationDate(result.getString("creation_date"));
            list.add(booking);
        }
        return list;
    }
}
