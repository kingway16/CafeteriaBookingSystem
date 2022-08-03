package com.controllers.staff;


import com.facades.SysUserFacade;
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
import java.sql.SQLException;
import java.util.Date;

@WebServlet(value = "/staff/register")
public class CreateStaff extends HttpServlet {

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

        System.out.println(email + name + password + dobDate + dobMonth + dobYear);
        String genInputDate = SysUserFacade.onGenSelectedDate(dobDate, dobMonth, dobYear);

        try{
            Connection con = DBConnection.OpenCon();
            int result = SysUserFacade.doInsertNewStaffInfo(email, name, password, address, telno, genInputDate, gender, con);
            DBConnection.CloseCon(con);
            session.setAttribute("result", result);
            response.sendRedirect(request.getContextPath() + "/pages/main.jsp");

        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }


}
