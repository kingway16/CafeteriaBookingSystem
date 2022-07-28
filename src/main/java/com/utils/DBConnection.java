package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection OpenCon() throws SQLException, ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root", "p@ssw0rd");
            return con;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void CloseCon(Connection con) throws SQLException
    {
        if (con != null)
        {
            con.close();
        }
    }
}
