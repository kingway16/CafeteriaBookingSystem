package com.controllers.dish;


import com.facades.DishesFacade;
import com.models.Dishes;
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

@WebServlet(value = "/dish/get")
public class GetDishes extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String path = (String) session.getAttribute("path");
        Connection con;

        try{
            con = DBConnection.OpenCon();
            List<Dishes> result = DishesFacade.onDisplayDishesInfo(con);
            DBConnection.CloseCon(con);
            session.setAttribute("dishesList", result);
            response.sendRedirect(request.getContextPath() + "/pages/" + path);
        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}
    }



}
