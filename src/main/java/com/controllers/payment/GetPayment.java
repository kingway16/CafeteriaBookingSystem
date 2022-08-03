package com.controllers.payment;

import com.facades.SysPaymentFacade;
import com.models.*;
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

@WebServlet(value = "/payment/get")
public class GetPayment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        try {
            Connection con = DBConnection.OpenCon();
            List<SysPayment> result = SysPaymentFacade.getAllPayment(con);
            DBConnection.CloseCon(con);
            session.setAttribute("paymentList", result);
            response.sendRedirect(request.getContextPath() + "/pages/_view-report.jsp");

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }
}
