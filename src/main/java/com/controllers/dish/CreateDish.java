package com.controllers.dish;


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
            int result = onCreateNewDish(dishName, ingredients, total, con);
            DBConnection.CloseCon(con);
            request.setAttribute("result", result);
            response.sendRedirect(request.getContextPath() + "/pages/_dishes-list.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}
    }

    public int onCreateNewDish(String dishName,String ingredients,String total, Connection con) throws SQLException {
        String insertNewDishInfo = "INSERT INTO dishes (dishes_name, ingredients, total) VALUES ('" + dishName + "', '" + ingredients + "'," + Integer.parseInt(total) + ")";
        PreparedStatement statement = con.prepareStatement(insertNewDishInfo);
        int result = statement.executeUpdate();
        System.out.println(result);
        return result;
    }

}
