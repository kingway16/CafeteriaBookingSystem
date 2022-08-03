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

@WebServlet(value = "/staff/getName")
public class GetAllStaffName extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        try{
            Connection con = DBConnection.OpenCon();
            List<SysUser> staffObject = SysUserFacade.doSelectAllStaffObject(con);
            DBConnection.CloseCon(con);
            session.setAttribute("staffObjectList", staffObject);
            response.sendRedirect(request.getContextPath() + "/pages/_booking-assign.jsp");
        }
        catch (SQLException ex){} catch (ClassNotFoundException ex) {}

    }
}
