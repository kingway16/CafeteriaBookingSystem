package com.controllers.book;

import com.models.Booking;
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

@WebServlet(value = "/booking")
public class GetAllBooking extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        try{
            Connection con = DBConnection.OpenCon();
            List<com.models.Booking> result = doSelectAllBookingInfo(session, request, response, con);
            DBConnection.CloseCon(con);
            session.setAttribute("bookingList", result);
            response.sendRedirect(request.getContextPath() + "/pages/_book-list.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}

    }

    public List<com.models.Booking> doSelectAllBookingInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException, ServletException {

        List<com.models.Booking> list = new LinkedList<com.models.Booking>();
        String selectAllBookingInfo = "select b.*, u.name, d.total, d.dishes_name from booking b inner join sys_user u on b.customer_id = u.user_id inner join dishes d on d.dishes_id = b.dishes_id";
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            com.models.Booking booking = new com.models.Booking();
            booking.setBookingid(reader.getInt("booking_id"));
            booking.setCustomerid(reader.getInt("customer_id"));
            booking.setName(reader.getString("name"));
            booking.setTotal(reader.getInt("total"));
            booking.setDishid(reader.getInt("dishes_id"));
            booking.setDishname(reader.getString("dishes_name"));
            booking.setNotes(reader.getString("notes"));
            booking.setRating(reader.getInt("rating"));
            booking.setFeedback(reader.getString("feedback"));
            booking.setPending(reader.getString("pending"));
            booking.setCreationDate(reader.getString("creation_date"));
            list.add(booking);
        }
        return list;
    }
}
