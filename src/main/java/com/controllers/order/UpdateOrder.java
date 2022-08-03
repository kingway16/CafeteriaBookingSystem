package com.controllers.order;

import com.facades.SysOrderFacade;
import com.utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(value = "/order/update")
public class UpdateOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String orderId = request.getParameter("orderId");
        String bookingId = request.getParameter("bookingId");

        try{
            Connection con = DBConnection.OpenCon();
            SysOrderFacade.doUpdateOrderInfo(Integer.parseInt(orderId), con);
            SysOrderFacade.doUpdateBookingInfo(Integer.parseInt(bookingId), con);
            SysOrderFacade.doCreateNewPayment(Integer.parseInt(orderId), con);
            DBConnection.CloseCon(con);
            response.sendRedirect(request.getContextPath() + "/pages/_staff.jsp");

        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }
}
