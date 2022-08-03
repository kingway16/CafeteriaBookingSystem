package com.controllers.dish;


import com.facades.DishesFacade;
import com.utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(value = "/dish/create")
public class CreateDish extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String dishName = request.getParameter("dishName");
        String ingredients = request.getParameter("ingredients");
        String total = request.getParameter("total");

        try{
            Connection con = DBConnection.OpenCon();
            int result = DishesFacade.onCreateNewDish(dishName, ingredients, total, con);
            DBConnection.CloseCon(con);
            request.setAttribute("result", result);
            response.sendRedirect(request.getContextPath() + "/pages/_dishes-list.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}
    }

}
