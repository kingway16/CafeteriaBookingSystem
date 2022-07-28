package com.controllers.customer;

import com.models.Order;
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

@WebServlet(value = "/customer/update")
public class UpdateCustomer extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String id = request.getParameter("userid");
        String path = request.getParameter("path");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telno = request.getParameter("telno");
        String address = request.getParameter("address");
        String day = request.getParameter("day");
        String gender = request.getParameter("gender");
        List<String> str = new LinkedList<String>();
        System.out.println(gender);

        if(!name.equals(null))
        {
            String nameq = "name = '" + name + "'";
            str.add(nameq);
        }
        if(!password.equals(null))
        {
            String passq = "password = '" + password + "'";
            str.add(passq);
        }
        if(!email.equals(null))
        {
            String emailq = "email = '" + email + "'";
            str.add(emailq);
        }
        if(!telno.equals(null))
        {
            String telnoq = "telno = '" + telno + "'";
            str.add(telnoq);
        }
        if(!address.equals(null))
        {
            String addressq = "address = '" + address + "'";
            str.add(addressq);
        }
        if(!day.equals(null))
        {
            String dayq = "dob = '" + day + "'";
            str.add(dayq);
        }
        if(!gender.equals(null))
        {
            String genderq = "gender = '" + gender + "'";
            str.add(genderq);
        }

        String str1 = str.toString().substring(1, str.toString().length() - 1);
        String query = "update sys_user set " + str1 + " where user_id = " + id;

        if(id.equals(null)) {
            response.sendRedirect(request.getContextPath() + "/");
        }
        else
        {
            try {
                Connection con = DBConnection.OpenCon();
                if (name != null) {
                    doUpdateStaffInfo(query, con);
                }
                DBConnection.CloseCon(con);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);

            } catch (SQLException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
    }

    public void doUpdateStaffInfo(String query, Connection con) throws SQLException, IOException, ServletException {
        PreparedStatement statement = con.prepareStatement(query);
        int result = statement.executeUpdate();
        System.out.println(result);
    }
}
