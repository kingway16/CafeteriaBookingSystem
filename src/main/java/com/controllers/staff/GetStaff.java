package com.controllers.staff;
import com.facades.SysUserFacade;
import com.models.SysUser;
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
                SysUser staffObject = SysUserFacade.doSelectStaffObject(Integer.parseInt(userid), con);
                DBConnection.CloseCon(con);
                session.setAttribute("staffObject", staffObject);
                System.out.println(staffObject);
                response.sendRedirect(request.getContextPath() + "/pages/" + path);
            }
            catch (SQLException ex){} catch (ClassNotFoundException ex) {}
        }
    }
}
