package com.controllers.book;


import com.utils.DBConnection;
import org.json.JSONObject;

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

@WebServlet(value = "/booking/create")
public class CreateBooking extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String userid = (String) session.getAttribute("userid");
        String dishId = request.getParameter("dishId");
        String notes = request.getParameter("notes");
        String path = (String) session.getAttribute("path");

        if(userid.equals(null))
        {
            response.sendRedirect(request.getContextPath() + "/");
        }
        else
        {
            try{
                Connection con = DBConnection.OpenCon();
                int result1 = onCreateNewBooking(Integer.parseInt(userid), Integer.parseInt(dishId), notes, con);
                DBConnection.CloseCon(con);
                request.setAttribute("result", result1);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);
            }
            catch (SQLException ex)
            {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int onCreateNewBooking(Integer userid, Integer dishId, String notes, Connection con) throws SQLException {
        Date date = new Date();
        String createNewBooking = "INSERT INTO booking (customer_id, dishes_id, notes, pending, creation_date) VALUES (" + userid + "," + dishId + ",'" + notes + "','Y','" + date +"')";
        PreparedStatement statement = con.prepareStatement(createNewBooking);
        int reader = statement.executeUpdate();
        System.out.println("onCreateNewBooking :" + reader);
        return reader;
    }
}
