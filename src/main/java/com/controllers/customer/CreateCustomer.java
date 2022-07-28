package com.controllers.customer;


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

@WebServlet(value = "/customer/register")
public class CreateCustomer extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String address = "";
        String telno = "";
        String gender = "";

        String email = request.getParameter("email");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String dobDate = request.getParameter("inputDay");
        String dobMonth = request.getParameter("inputMonth");
        String dobYear = request.getParameter("inputYear");

        address = request.getParameter("address");
        telno = request.getParameter("telno");
        gender = request.getParameter("gender");

        String genInputDate = onGenSelectedDate(dobDate, dobMonth, dobYear);

        try{
            Connection con = DBConnection.OpenCon();
            int result = doInsertNewStaffInfo(email, name, password, address, telno, genInputDate, gender, con);
            DBConnection.CloseCon(con);
            session.setAttribute("result", result);
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}

    }

    public int doInsertNewStaffInfo(String email,String name,String password,String address,String telno,String dob,String gender,Connection con) throws SQLException {
        Date date = new Date();
        String insertNewStaffInfo = "INSERT INTO sys_user (email, password, role, name, address, telno, dob, gender, creation_date)VALUES ('" + email + "', '" + password + "','C','" + name + "', '" + address + "', '" + telno + "','" + dob + "', '" + gender + "','" + date.toString() + "')";
        PreparedStatement statement = con.prepareStatement(insertNewStaffInfo.toString());
        int result = statement.executeUpdate();
        return result;
    }

    public String onGenSelectedDate(String date, String month, String year)
    {
        return date + month + year;
    }
}
