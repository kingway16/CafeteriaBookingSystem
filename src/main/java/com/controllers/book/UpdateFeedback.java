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

@WebServlet(value = "/booking/feedback")
public class UpdateFeedback extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String rate = request.getParameter("rate");
        String feedback = request.getParameter("feedback");
        String bookingId = (String) session.getAttribute("bookingId");

        try{
            Connection con = DBConnection.OpenCon();
            BookingFacade.doUpdateBookingRateAndFeedback(Integer.parseInt(rate), feedback, Integer.parseInt(bookingId), con);
            DBConnection.CloseCon(con);
            response.sendRedirect(request.getContextPath() + "/pages/_customer.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}
    }
}
