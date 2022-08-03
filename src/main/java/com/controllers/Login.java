package com.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import com.utils.DBConnection;


@WebServlet(value = "/pages")
public class Login extends HttpServlet{
        private static final long serialVersionUID = 1L;

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {}

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            HttpSession session = request.getSession(true);

            try {
                Connection con = DBConnection.OpenCon();
                List<String> result = doSelectUserRoleInfo(name, password, con);
                DBConnection.CloseCon(con);

                if(!result.isEmpty()){
                    // 'A' stands for Manager
                    if (result.get(0).equals("A") == true) {
    //                    session.setAttribute("userRole", "Manager site");
                        response.sendRedirect(request.getContextPath() + "/pages/main.jsp");
                    }
                    // 'S' stands for Staff
                    else if (result.get(0).equals("S") == true) {
                        session.setAttribute("userid", result.get(1));
                        response.sendRedirect(request.getContextPath() + "/pages/_staff.jsp");
                    }
                    // 'C' stands for Customer
                    else if (result.get(0).equals("C") == true) {
                        session.setAttribute("userid", result.get(1));
                        response.sendRedirect(request.getContextPath() + "/pages/_customer.jsp");
                    }
                }
                else
                {
                    session.setAttribute("errorMessage", "Username and Password is Invalid. Please try again.");
                    response.sendRedirect(request.getContextPath() + "/");
                }
            }
            catch (SQLException ex){
                session.setAttribute("error", ex.getMessage());
                response.sendRedirect(request.getContextPath() + "/pages/_error.jsp");
            } catch(ClassNotFoundException ex)
            {
                session.setAttribute("error", ex.getMessage());
                response.sendRedirect(request.getContextPath() + "/pages/_error.jsp");
            }

        }

        public List<String> doSelectUserRoleInfo(String name, String password, Connection con) throws SQLException
        {
            List<String> list = new LinkedList<String>();
            String selectUsersInfo = "select * from sys_user where name = '" + name + "' and password = '" + password + "'";
            PreparedStatement statement = con.prepareStatement(selectUsersInfo.toString());
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                String role = result.getString("role");
                String userid = result.getString("user_id");
                list.add(role);
                list.add(userid);
            }
            return list;
        }
    }
