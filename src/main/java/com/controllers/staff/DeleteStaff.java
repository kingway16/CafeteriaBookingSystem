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

@WebServlet(value = "/staff/delete")
public class DeleteStaff extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String id = request.getParameter("id");
        String path = request.getParameter("path");
        System.out.println("id=" + id);

        try{
            Connection con = DBConnection.OpenCon();
            SysUserFacade.doDeleteDish(Integer.parseInt(id), con);
            DBConnection.CloseCon(con);
            response.sendRedirect(request.getContextPath() + "/pages/" + path);
        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }

}
