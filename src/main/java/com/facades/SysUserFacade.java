package com.facades;

import com.models.SysUser;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SysUserFacade extends AbstractFacade<SysUser> implements SysUserFacadeLocal{

    public SysUserFacade(Class<SysUser> entityClass) {
        super(entityClass);
    }

    protected EntityManager getEntityManager() {
        return null;
    }

    public static int doInsertNewStaffInfo(String email, String name, String password, String address, String telno, String dob, String gender, Connection con) throws SQLException {
        Date date = new Date();
        String insertNewStaffInfo = "INSERT INTO sys_user (email, password, role, name, address, telno, dob, gender, creation_date)VALUES ('" + email + "', '" + password + "','S', '" + name + "', '" + address + "', '" + telno + "','" + dob + "', '" + gender + "','" + date.toString() + "')";
        PreparedStatement statement = con.prepareStatement(insertNewStaffInfo.toString());
        int result = statement.executeUpdate();
        return result;
    }

    public static String onGenSelectedDate(String date, String month, String year)
    {
        return date + month + year;
    }

    public static void doDeleteDish(Integer id, Connection con) throws SQLException {
        String deleteDish = "delete from sys_user where user_id = " + id;
        PreparedStatement statement = con.prepareStatement(deleteDish);
        statement.executeUpdate();
    }

    public static List<SysUser> doSelectAllStaffObject1(Connection con) throws SQLException {

        List<SysUser> list = new LinkedList<SysUser>();
        String selectAllStaffInfo = "select * from sys_user where role = 'S'";
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo);
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            user.setUserId(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list;
    }

    public static SysUser doSelectStaffObject(Integer userid, Connection con) throws SQLException {

        List<SysUser> list = new LinkedList<SysUser>();
        String selectAllStaffInfo = "select * from sys_user where user_id = "+ userid;
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo);
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            user.setUserId(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            user.setName(result.getString("name"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list.get(0);
    }

    public static List<SysUser> doSelectAllStaffObject(Connection con) throws SQLException {

        List<SysUser> list = new LinkedList<SysUser>();
        String selectAllStaffInfo = "select * from sys_user where role = 'S'";
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            user.setUserId(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list;
    }

    public static void doUpdateStaffInfo(String query, Connection con) throws SQLException, IOException, ServletException {
        PreparedStatement statement = con.prepareStatement(query);
        int result = statement.executeUpdate();
        System.out.println(result);
    }

    public static int doInsertNewCustomerInfo(String email, String name, String password, String address, String telno, String dob, String gender, Connection con) throws SQLException {
        Date date = new Date();
        String insertNewStaffInfo = "INSERT INTO sys_user (email, password, role, name, address, telno, dob, gender, creation_date)VALUES ('" + email + "', '" + password + "','C','" + name + "', '" + address + "', '" + telno + "','" + dob + "', '" + gender + "','" + date.toString() + "')";
        PreparedStatement statement = con.prepareStatement(insertNewStaffInfo.toString());
        int result = statement.executeUpdate();
        return result;
    }

    public static List<SysUser> doSelectAllCustInfo(Connection con) throws SQLException {

        List<SysUser> list = new LinkedList<SysUser>();
        String selectAllStaffInfo = "select * from sys_user where role = 'C'";
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            user.setUserId(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list;
    }

    public static int doDeleteCustomer(Integer id, Connection con) throws SQLException {
        String deleteDish = "delete from sys_user where user_id = " + id;
        PreparedStatement statement = con.prepareStatement(deleteDish);
        int reader = statement.executeUpdate();
        System.out.println(reader);
        return reader;
    }

    public static SysUser doSelectUSerInfo(int id, Connection con) throws SQLException {

        List<SysUser> list = new LinkedList<SysUser>();
        String selectUserInfo = "select * from sys_user where user_id = " + id;
        PreparedStatement statement = con.prepareStatement(selectUserInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            user.setUserId(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list.get(0);
    }

    public static List<SysUser> doSearchAllCustInfo(Connection con) throws SQLException {

        List<SysUser> list = new LinkedList<SysUser>();
        String selectAllStaffInfo = "select * from sys_user where role = 'C'";
        PreparedStatement statement = con.prepareStatement(selectAllStaffInfo.toString());
        ResultSet result = statement.executeQuery();
        while(result.next())
        {
            SysUser user = new SysUser();
            user.setUserId(result.getInt("user_id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setAddress(result.getString("address"));
            user.setTelno(result.getString("telno"));
            user.setDob(result.getString("dob"));
            user.setGender(result.getString("gender"));
            user.setCreationDate(result.getString("creation_date"));
            list.add(user);
        }
        return list;
    }

    public static void doUpdateCustomerInfo(String query, Connection con) throws SQLException, IOException, ServletException {
        PreparedStatement statement = con.prepareStatement(query);
        int result = statement.executeUpdate();
        System.out.println(result);
    }

    public static void doUpdateAdminInfo(String query, Connection con) throws SQLException, IOException, ServletException {
        PreparedStatement statement = con.prepareStatement(query);
        int result = statement.executeUpdate();
        System.out.println(result);
    }
}
