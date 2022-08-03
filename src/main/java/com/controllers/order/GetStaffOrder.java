package com.controllers.order;

import com.facades.SysOrderFacade;
import com.models.SysOrder;
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
import java.util.List;

@WebServlet(value = "/staff/order")
public class GetStaffOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String id = request.getParameter("userid");
        System.out.println("id:" + id);

        if(id.equals(null)) {
            response.sendRedirect(request.getContextPath() + "/");
        }
        else
        {
            try {
                Connection con = DBConnection.OpenCon();
                List<SysOrder> result = SysOrderFacade.doSelectedOrderInfo(Integer.parseInt(id), con);
                DBConnection.CloseCon(con);
                System.out.println("result:" + result);
                session.setAttribute("orderList", result);
                response.sendRedirect(request.getContextPath() + "/pages/_staff.jsp");
            } catch (SQLException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
    }
}
