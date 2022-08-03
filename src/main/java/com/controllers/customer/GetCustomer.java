package com.controllers.customer;

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
import java.sql.SQLException;

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
                SysUser result = SysUserFacade.doSelectUSerInfo(Integer.parseInt(id), con);
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
}
