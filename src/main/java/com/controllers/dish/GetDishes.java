package com.controllers.dish;


import com.models.Dish;
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
            List<Dish> result = onDisplayDishesInfo(con);
            DBConnection.CloseCon(con);
            session.setAttribute("dishesList", result);
            response.sendRedirect(request.getContextPath() + "/pages/" + path);
        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}
    }

    public List<Dish> onDisplayDishesInfo(Connection con) throws SQLException {
        List<Dish> list = new LinkedList<Dish>();
        String insertNewDishInfo = "select * from dishes";
        PreparedStatement statement = con.prepareStatement(insertNewDishInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            Dish dish = new Dish();
            dish.setId(reader.getInt("dishes_id"));
            dish.setName(reader.getString("dishes_name"));
            dish.setIngredients(reader.getString("ingredients"));
            dish.setPrice(reader.getInt("total"));
            list.add(dish);
        }
        System.out.println(list);
        return list;
    }

}
