package com.controllers.staff;
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

@WebServlet(value = "/staff/get")
public class GetStaff extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String userid = request.getParameter("userid");
        String path = request.getParameter("path");

        if(userid.equals(null)){
            response.sendRedirect(request.getContextPath() + "/");
        }
        else
        {
            try{
                Connection con = DBConnection.OpenCon();
                User staffObject = doSelectStaffObject(Integer.parseInt(userid), con);
                DBConnection.CloseCon(con);
                session.setAttribute("staffObject", staffObject);
                System.out.println(staffObject);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);
            }
            catch (SQLException ex){} catch (ClassNotFoundException ex) {}
        }
    }

    public User doSelectStaffObject(Integer userid, Connection con) throws SQLException {

        List<User> list = new LinkedList<User>();
        String selectAllStaffInfo = "select * from sys_user where user_id = "+ userid;
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo);
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            User user = new User();
            user.setUserid(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            user.setName(result.getString("name"));
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
