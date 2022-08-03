package com.facades;

import com.models.Booking;
import com.models.Dishes;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DishesFacade extends AbstractFacade<Dishes> implements DishesFacadeLocal{

    public DishesFacade(Class<Dishes> entityClass) {
        super(entityClass);
    }

    protected EntityManager getEntityManager() {
        return null;
    }

    public static int onCreateNewDish(String dishName, String ingredients, String total, Connection con) throws SQLException {
        String insertNewDishInfo = "INSERT INTO dishes (dishes_name, ingredients, total) VALUES ('" + dishName + "', '" + ingredients + "'," + Integer.parseInt(total) + ")";
        PreparedStatement statement = con.prepareStatement(insertNewDishInfo);
        int result = statement.executeUpdate();
        System.out.println(result);
        return result;
    }

    public static int onCreateNewDish(Integer id, Connection con) throws SQLException {
        String deleteDishInfo = "delete from dishes where dishes_id = " + id;
        PreparedStatement statement = con.prepareStatement(deleteDishInfo);
        int result = statement.executeUpdate();
        System.out.println(result);
        return result;
    }

    public static List<Dishes> onDisplayDishesInfo(Connection con) throws SQLException {
        List<Dishes> list = new LinkedList<Dishes>();
        String insertNewDishInfo = "select * from dishes";
        PreparedStatement statement = con.prepareStatement(insertNewDishInfo);
        ResultSet reader = statement.executeQuery();
        while(reader.next())
        {
            Dishes dish = new Dishes();

            dish.setDishesId(reader.getInt("dishes_id"));
            dish.setDishesName(reader.getString("dishes_name"));
            dish.setIngredients(reader.getString("ingredients"));
            dish.setTotal(reader.getInt("total"));

            list.add(dish);
        }
        System.out.println(list);
        return list;
    }
}
