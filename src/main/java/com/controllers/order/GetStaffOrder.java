package com.controllers.order;

import com.models.Order;
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

@WebServlet(value = "/staff/order")
public class GetStaffOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String id = request.getParameter("userid");
        System.out.println("id:" + id);

        if(id.equals(null)) {
            try {
                Connection con = DBConnection.OpenCon();
                List<Order> result = doSelectedOrderInfo(Integer.parseInt(id), con);
                DBConnection.CloseCon(con);
                System.out.println("result:" + result);
                session.setAttribute("orderList", result);
                response.sendRedirect(request.getContextPath() + "/pages/_staff.jsp");

            } catch (SQLException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    public List<Order> doSelectedOrderInfo(Integer id, Connection con) throws SQLException, IOException, ServletException {

        List<Order> list = new LinkedList<Order>();
        String selectAllBookingInfo = "select s.*, u.user_id, u.name, r.dishes_id, b.notes, r.dishes_name from sys_order s inner join booking b on b.booking_id = s.booking_id inner join sys_user u on u.user_id = b.customer_id inner join dishes r on r.dishes_id = b.dishes_id where s.staff_id =" + id + " and b.pending = 'O' and s.order_status = 'P'";
        PreparedStatement statement = con.prepareStatement(selectAllBookingInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            Order order = new Order();
            order.setOrderId(result.getInt("order_id"));
            order.setStaffId(result.getInt("staff_id"));
            order.setBookingId(result.getInt("booking_id"));
            order.setUserId(result.getInt("user_id"));
            order.setUserName(result.getString("name"));
            order.setDishesId(result.getInt("dishes_id"));
            order.setDishesName(result.getString("dishes_name"));
            order.setNotes(result.getString("notes"));
            order.setOrderStatus(result.getString("order_status"));
            list.add(order);
        }
        return list;
    }
}
