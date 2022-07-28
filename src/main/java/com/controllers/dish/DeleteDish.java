package com.controllers.dish;

import com.utils.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/dish/delete")
public class DeleteDish extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        System.out.println("delete id:" + id);

        if(id.equals(null)) {
            response.sendRedirect(request.getContextPath() + "/");
        }
        else
        {
            try {
                Connection con = DBConnection.OpenCon();
                int result = onCreateNewDish(Integer.parseInt(id), con);
                DBConnection.CloseCon(con);
                request.setAttribute("result", result);
                response.sendRedirect(request.getContextPath() + "/pages/_dishes-list.jsp");

            } catch (SQLException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }

    }

    public int onCreateNewDish(Integer id, Connection con) throws SQLException {
        String deleteDishInfo = "delete from dishes where dishes_id = " + id;
        PreparedStatement statement = con.prepareStatement(deleteDishInfo);
        int result = statement.executeUpdate();
        System.out.println(result);
        return result;
    }

}
