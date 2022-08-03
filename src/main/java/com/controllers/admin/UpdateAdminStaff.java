package com.controllers.admin;

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
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(value = "/pages/admin-update")
public class UpdateAdminStaff extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        String id = request.getParameter("userid");
        System.out.println("check id: " +id);
        String path = request.getParameter("path");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telno = request.getParameter("telno");
        String address = request.getParameter("address");
        String day = request.getParameter("day");
        String gender = request.getParameter("gender");
        List<String> str = new LinkedList<String>();

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
        System.out.println("s :" + str1);
        String query = "update sys_user set " + str1 + " where user_id = " + id;
        System.out.println("t :" + query);
        try{
            Connection con = DBConnection.OpenCon();
            if(path == "main.jsp")
            {
                session.setAttribute("userid", id);
                response.sendRedirect(request.getContextPath() + "/pages/main.jsp");
            }
            if(name != null)
            {
                SysUserFacade.doUpdateAdminInfo(query, con);
            }
            DBConnection.CloseCon(con);
            session.setAttribute("userid", id);
            response.sendRedirect(request.getContextPath() + "/pages/" + path + "?userid=" + id);

        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }


}
