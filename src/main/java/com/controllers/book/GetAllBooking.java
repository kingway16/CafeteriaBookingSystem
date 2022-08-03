package com.controllers.book;

import com.facades.BookingFacade;
import com.utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/booking")
public class GetAllBooking extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        try{
            Connection con = DBConnection.OpenCon();
            List<com.models.Booking> result = BookingFacade.doSelectAllBookingInfo(session, request, response, con);
            DBConnection.CloseCon(con);
            session.setAttribute("bookingList", result);
            response.sendRedirect(request.getContextPath() + "/pages/_book-list.jsp");
        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}
    }
}
