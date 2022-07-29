package com.controllers.payment;

import com.models.Payment;
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

@WebServlet(value = "/payment/get")
public class GetPayment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);

        try {
            Connection con = DBConnection.OpenCon();
            List<Payment> result = getAllPayment(con);
            DBConnection.CloseCon(con);
            session.setAttribute("paymentList", result);
            response.sendRedirect(request.getContextPath() + "/pages/_view-report.jsp");

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public List<Payment> getAllPayment(Connection con) throws SQLException {

        List<Payment> list = new LinkedList<Payment>();
        String selectAllPayment = "select s.*, u.name, d.total, b.booking_id from sys_payment s inner join sys_order o on s.order_id = o.order_id inner join booking b on o.booking_id = b.booking_id inner join sys_user u on u.user_id = b.customer_id inner join dishes d on d.dishes_id = b.dishes_id";
        PreparedStatement statement = con.prepareStatement(selectAllPayment);
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            Payment payment = new Payment();
            payment.setOrderId(result.getInt("order_id"));
            payment.setBookingId(result.getInt("booking_id"));
            payment.setId(result.getInt("payment_id"));
            payment.setName(result.getString("name"));
            payment.setTotal(result.getInt("total"));
            payment.setTrxDate(result.getString("trx_date"));
            list.add(payment);
        }
        return list;
    }
}
