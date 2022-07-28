package com.controllers.order;

import com.models.Order;
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
import java.util.LinkedList;
import java.util.List;

@WebServlet(value = "/order/update")
public class UpdateOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String orderId = request.getParameter("orderId");
        String bookingId = request.getParameter("bookingId");

        try{
            Connection con = DBConnection.OpenCon();
            doUpdateOrderInfo(Integer.parseInt(orderId), con);
            doUpdateBookingInfo(Integer.parseInt(bookingId), con);
            DBConnection.CloseCon(con);
            response.sendRedirect(request.getContextPath() + "/pages/_staff.jsp");

        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }

    public void doUpdateOrderInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {
        String updateOrderInfo = "update sys_order set order_status = 'C' where order_id =" + id;
        PreparedStatement statement = con.prepareStatement(updateOrderInfo.toString());
        int result = statement.executeUpdate();
        System.out.println(result);
    }

    public void doUpdateBookingInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {
        String updateOrderInfo = "update booking set pending = 'N' where booking_id =" + id;
        PreparedStatement statement = con.prepareStatement(updateOrderInfo.toString());
        int result = statement.executeUpdate();
        System.out.println(result);
    }
}
