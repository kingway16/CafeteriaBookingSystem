package com.controllers.book;

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

@WebServlet(value = "/booking/update")
public class UpdateBooking extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String id = request.getParameter("userid");
        System.out.println("id:" + id);

        try{
            Connection con = DBConnection.OpenCon();
            List<com.models.Booking> result = doSelectedBookingInfo(Integer.parseInt(id), con);
            DBConnection.CloseCon(con);
            System.out.println("result:" + result);
            session.setAttribute("bookingList", result);
            response.sendRedirect(request.getContextPath() + "/pages/_customer.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}

    }

    public List<com.models.Booking> doSelectedBookingInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<com.models.Booking> list = new LinkedList<com.models.Booking>();
        String selectAllBookingInfo = "select * from booking where customer_id =" + id;
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            com.models.Booking booking = new com.models.Booking();
            booking.setBookingid(result.getInt("booking_id"));
            booking.setCustomerid(result.getInt("customer_id"));
            booking.setRating(result.getInt("rating"));
            booking.setFeedback(result.getString("feedback"));
            booking.setTotal(result.getInt("total"));
            booking.setCreationDate(result.getString("creation_date"));
            list.add(booking);
        }
        return list;
    }
}
