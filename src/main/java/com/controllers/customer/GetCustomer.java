package com.controllers.customer;

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

@WebServlet(value = "/customer/get")
public class GetCustomer extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String id = request.getParameter("userid");
        String path = request.getParameter("path");

        if(!id.equals(null)){
            try{
                Connection con = DBConnection.OpenCon();
                User result = doSelectUSerInfo(Integer.parseInt(id), con);
                DBConnection.CloseCon(con);
                session.setAttribute("CustomerObject", result);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);
            }
            catch (SQLException ex){} catch(ClassNotFoundException ex){}
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    public User doSelectUSerInfo(int id, Connection con) throws SQLException {

        List<User> list = new LinkedList<User>();
        String selectUserInfo = "select * from sys_user where user_id = " + id;
        PreparedStatement statement = con.prepareStatement(selectUserInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            User user = new User();
            user.setUserid(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list.get(0);
    }
}
