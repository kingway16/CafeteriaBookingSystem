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

@WebServlet(value = "/order/create")
public class CreateOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String bookingId = request.getParameter("bookingId");
        String staffId = request.getParameter("staffDropdownList");
        try{
            Connection con = DBConnection.OpenCon();
            SysOrderFacade.doSelectAllBookingInfo(bookingId, staffId, con);
            SysOrderFacade.doUpdateBookingStatus(bookingId, con);
            int orderId = SysOrderFacade.doSelectOrderID(bookingId, con);
            SysOrderFacade.doCreateNewPayment(orderId, con);
            DBConnection.CloseCon(con);
            response.sendRedirect(request.getContextPath() + "/pages/_book-list.jsp");
        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }
}
