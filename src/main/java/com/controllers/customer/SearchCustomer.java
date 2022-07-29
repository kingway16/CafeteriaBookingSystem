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

@WebServlet(value = "/customer/search")
public class SearchCustomer extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String strSearch = request.getParameter("search");

        try{
            Connection con = DBConnection.OpenCon();
            List<User> result = doSearchAllCustInfo(con);
            DBConnection.CloseCon(con);
            session.setAttribute("CustomerList", result);
            response.sendRedirect(request.getContextPath() + "/pages/_cust-list.jsp");

        }
        catch (SQLException ex){} catch(ClassNotFoundException ex){}

    }

    public List<User> doSearchAllCustInfo(Connection con) throws SQLException {

        List<User> list = new LinkedList<User>();
        String selectAllStaffInfo = "select * from sys_user where role = 'C'";
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
