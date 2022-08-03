package com.facades;

import com.models.*;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SysPaymentFacade extends AbstractFacade<SysPayment> implements SysPaymentFacadeLocal{

    public SysPaymentFacade(Class<SysPayment> entityClass) {
        super(entityClass);
    }

    protected EntityManager getEntityManager() {
        return null;
    }
    public static List<SysPayment> getAllPayment(Connection con) throws SQLException {

        List<SysPayment> list = new LinkedList<SysPayment>();
        String selectAllPayment = "select s.*, u.name, d.total, b.booking_id from sys_payment s inner join sys_order o on s.order_id = o.order_id inner join booking b on o.booking_id = b.booking_id inner join sys_user u on u.user_id = b.customer_id inner join dishes d on d.dishes_id = b.dishes_id";
        PreparedStatement statement = con.prepareStatement(selectAllPayment);
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            Dishes dish = new Dishes();
            SysUser user = new SysUser();
            Booking booking = new Booking();
            SysOrder order = new SysOrder();
            SysPayment payment = new SysPayment();

            dish.setTotal(result.getInt("total"));
            user.setName(result.getString("name"));
            booking.setBookingId(result.getInt("booking_id"));
            booking.setDishesId(dish);
            booking.setSysUser(user);
            order.setOrderId(result.getInt("order_id"));
            order.setBookingId(booking);
            payment.setPaymentId(result.getInt("payment_id"));
            payment.setOrderId(order);
            payment.setTrxDate(result.getString("trx_date"));

            list.add(payment);
        }
        return list;
    }
}
