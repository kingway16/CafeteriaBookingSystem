package com.controllers.staff;

import com.models.User;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet(value = "/staff")
public class Staff extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        try{
            Connection con = DBConnection.OpenCon();
            List<JSONObject> result = doSelectAllStaffInfo(con);
            List<User> staffObject = doSelectAllStaffObject(con);
            DBConnection.CloseCon(con);

            session.setAttribute("staffList", result.toString());
            session.setAttribute("staffObjectList", staffObject);

            response.sendRedirect(request.getContextPath() + "/pages/main.jsp");
//           request.getRequestDispatcher("pages/main.jsp").forward(request, response);
        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }

    public List<JSONObject> doSelectAllStaffInfo(Connection con) throws SQLException {

        List<JSONObject> list = new LinkedList<JSONObject>();
        String selectAllStaffInfo = "select * from sys_user where role = 'S'";
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            JSONObject json = new JSONObject();
            json.put("userid",result.getInt("user_id"));
            json.put("email",result.getString("email"));
            json.put("name",result.getString("name"));
            json.put("address",result.getString("address"));
            json.put("telno",result.getString("telno"));
            json.put("dob",result.getString("dob"));
            json.put("gender",result.getString("gender"));
            json.put("creationdate",result.getString("creation_date"));
            list.add(json);
        }
        return list;
    }

    public List<User> doSelectAllStaffObject(Connection con) throws SQLException {

        List<User> list = new LinkedList<User>();
        String selectAllStaffInfo = "select * from sys_user where role = 'S'";
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            User user = new User();
            user.setUserid(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list;
    }
}
