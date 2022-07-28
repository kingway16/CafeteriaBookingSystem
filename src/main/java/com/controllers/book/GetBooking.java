package com.controllers.book;

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
                List<com.models.Booking> result = doSelectedBookingInfo(Integer.parseInt(id), con);
                DBConnection.CloseCon(con);
                System.out.println("result:" + result);
                session.setAttribute("bookingList", result);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);
            } catch (SQLException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
    }

    public List<com.models.Booking> doSelectedBookingInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<com.models.Booking> list = new LinkedList<com.models.Booking>();
        String selectAllBookingInfo = "select b.*, d.dishes_name, s.name, d.total from booking b inner join dishes d on d.dishes_id = b.dishes_id inner join sys_user s on s.user_id = b.customer_id where b.customer_id =" + id;
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            com.models.Booking booking = new com.models.Booking();
            booking.setBookingid(reader.getInt("booking_id"));
            booking.setCustomerid(reader.getInt("customer_id"));
            booking.setName(reader.getString("name"));
            booking.setDishid(reader.getInt("dishes_id"));
            booking.setDishname(reader.getString("dishes_name"));
            booking.setNotes(reader.getString("notes"));
            booking.setRating(reader.getInt("rating"));
            booking.setFeedback(reader.getString("feedback"));
            booking.setTotal(reader.getInt("total"));
            booking.setPending(reader.getString("pending"));
            booking.setCreationDate(reader.getString("creation_date"));
            list.add(booking);
        }
        System.out.println("result:" + list);
        return list;
    }
}
