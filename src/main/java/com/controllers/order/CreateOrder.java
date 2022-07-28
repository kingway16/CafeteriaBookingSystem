package com.controllers.order;

import com.models.User;
import com.utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet(value = "/order/create")
public class CreateOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String bookingId = request.getParameter("bookingId");
        String staffId = request.getParameter("staffDropdownList");
        try{
            Connection con = DBConnection.OpenCon();
            doSelectAllBookingInfo(bookingId, staffId, con);
            doUpdateBookingStatus(bookingId, con);
            int orderId = doSelectOrderID(bookingId, con);
            doCreateNewPayment(orderId, con);
            DBConnection.CloseCon(con);
            response.sendRedirect(request.getContextPath() + "/pages/_book-list.jsp");
        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }

    public void doSelectAllBookingInfo(String bookingId, String staffId, Connection con) throws SQLException {
        String insertNewOrder = "insert into sys_order (booking_id, staff_id, order_status) values ("+ Integer.parseInt(bookingId) +"," + Integer.parseInt(staffId) + ", 'P' )";
        PreparedStatement statement = con.prepareStatement(insertNewOrder);
        int result = statement.executeUpdate();
        System.out.println("result1:" + result);
    }

    public void doUpdateBookingStatus(String bookingId, Connection con) throws SQLException {
        String updateNewBooking = "update booking set pending = 'O' where booking_id = "+ Integer.parseInt(bookingId);
        PreparedStatement statement = con.prepareStatement(updateNewBooking);
        int result = statement.executeUpdate();
        System.out.println("result2:" + result);
    }

    public void doCreateNewPayment(int orderID, Connection con) throws SQLException {
        Date date = new Date();
        String updateNewBooking = "insert into sys_payment (order_id, trx_date) values (" + orderID + ", '"+ date +"')";
        PreparedStatement statement = con.prepareStatement(updateNewBooking);
        int result = statement.executeUpdate();
        System.out.println("result3:" + result);
    }

    public int doSelectOrderID(String bookingId, Connection con) throws SQLException {
        String updateNewBooking = "select order_id from sys_order where booking_id =" + bookingId;
        PreparedStatement statement = con.prepareStatement(updateNewBooking);
        ResultSet reader = statement.executeQuery();
        while(reader.next()){
            System.out.println(reader.getInt("order_id"));
            return reader.getInt("order_id");
        }
        return 0;
    }
}
