package com.facades;


import com.models.Booking;
import com.models.Dishes;
import com.models.SysOrder;
import com.models.SysUser;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SysOrderFacade extends AbstractFacade<SysOrder> implements SysOrderFacadeLocal{

    public SysOrderFacade(Class<SysOrder> entityClass) {
        super(entityClass);
    }

    protected EntityManager getEntityManager() {
        return null;
    }

    public static void doSelectAllBookingInfo(String bookingId, String staffId, Connection con) throws SQLException {
        String insertNewOrder = "insert into sys_order (booking_id, staff_id, order_status) values ("+ Integer.parseInt(bookingId) +"," + Integer.parseInt(staffId) + ", 'P' )";
        PreparedStatement statement = con.prepareStatement(insertNewOrder);
        int result = statement.executeUpdate();
        System.out.println("result1:" + result);
    }

    public static void doUpdateBookingStatus(String bookingId, Connection con) throws SQLException {
        String updateNewBooking = "update booking set pending = 'O' where booking_id = "+ Integer.parseInt(bookingId);
        PreparedStatement statement = con.prepareStatement(updateNewBooking);
        int result = statement.executeUpdate();
        System.out.println("result2:" + result);
    }

    public static void doCreateNewPayment(int orderID, Connection con) throws SQLException {
        Date date = new Date();
        String updateNewBooking = "insert into sys_payment (order_id, trx_date) values (" + orderID + ", '"+ date +"')";
        PreparedStatement statement = con.prepareStatement(updateNewBooking);
        int result = statement.executeUpdate();
        System.out.println("result3:" + result);
    }

    public static int doSelectOrderID(String bookingId, Connection con) throws SQLException {
        String updateNewBooking = "select order_id from sys_order where booking_id =" + bookingId;
        PreparedStatement statement = con.prepareStatement(updateNewBooking);
        ResultSet reader = statement.executeQuery();
        while(reader.next()){
            System.out.println(reader.getInt("order_id"));
            return reader.getInt("order_id");
        }
        return 0;
    }

    public static List<SysOrder> doSelectedOrderInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<SysOrder> list = new LinkedList<SysOrder>();
        String selectAllBookingInfo = "select s.*, u.user_id, u.name, r.dishes_id, b.notes, r.dishes_name, b.booking_id from sys_order s inner join booking b on b.booking_id = s.booking_id inner join sys_user u on u.user_id = b.customer_id inner join dishes r on r.dishes_id = b.dishes_id where s.staff_id =" + id + " and b.pending = 'O' and s.order_status = 'P'";
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            Dishes dish = new Dishes();
            Booking booking = new Booking();
            SysOrder order = new SysOrder();

            dish.setDishesId(result.getInt("dishes_id"));
            dish.setDishesName(result.getString("dishes_name"));

            user.setUserId(result.getInt("user_id"));
            user.setName(result.getString("name"));

            booking.setNotes(result.getString("notes"));
            booking.setBookingId(result.getInt("booking_id"));
            booking.setSysUser(user);
            booking.setDishesId(dish);

            order.setOrderId(result.getInt("order_id"));
            order.setOrderStatus(result.getString("order_status"));
            order.setBookingId(booking);

            System.out.println(order.getBookingId().getNotes());
            list.add(order);
        }
        return list;
    }

    public static void doUpdateOrderInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {
        String updateOrderInfo = "update sys_order set order_status = 'C' where order_id =" + id;
        PreparedStatement statement = con.prepareStatement(updateOrderInfo.toString());
        int result = statement.executeUpdate();
        System.out.println(result);
    }

    public static void doUpdateBookingInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {
        String updateOrderInfo = "update booking set pending = 'N' where booking_id =" + id;
        PreparedStatement statement = con.prepareStatement(updateOrderInfo.toString());
        int result = statement.executeUpdate();
        System.out.println(result);
    }

    public void doCreateNewPayment(Integer id, Connection con) throws SQLException, IOException, ServletException {
        Date date = new Date();
        String updateOrderInfo = "insert into booking (order_id, trx_date) values ("+ id + "," + date + ")";
        PreparedStatement statement = con.prepareStatement(updateOrderInfo.toString());
        int result = statement.executeUpdate();
        System.out.println(result);
    }

}
