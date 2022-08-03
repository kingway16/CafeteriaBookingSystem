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

@WebServlet(value = "/booking/get")
public class GetBooking extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String id = (String) session.getAttribute("userid");
        String path = (String) session.getAttribute("path");
        System.out.println("uid:" + id);

        if(id.equals(null)) {
            response.sendRedirect(request.getContextPath() + "/");
        }
        else
        {
            try {
                Connection con = DBConnection.OpenCon();
                List<com.models.Booking> result = BookingFacade.doSelectedBookingInfo(Integer.parseInt(id), con);
                DBConnection.CloseCon(con);
                System.out.println("result:" + result);
                session.setAttribute("bookingList", result);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);
            } catch (SQLException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
    }
}
