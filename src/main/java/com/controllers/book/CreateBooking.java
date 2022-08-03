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
                int result1 = BookingFacade.onCreateNewBooking(Integer.parseInt(userid), Integer.parseInt(dishId), notes, con);
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


}
