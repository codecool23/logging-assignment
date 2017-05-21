package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by flavia on 2017.05.10..
 */
public class SupplierDaoJdbc extends JdbcConnection implements SupplierDao {
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoJdbc.class);

    private static SupplierDaoJdbc instance = null;
    protected SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            logger.debug("Creating new {}", SupplierDaoJdbc.class.getSimpleName());
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        String query =
                "INSERT INTO suppliers (supplier_id, name, description) " +
                        "VALUES ('" + supplier.getId() + "', '" + supplier.getName() + "', '" + supplier.getDescription() + "')" +
                        "ON CONFLICT (name) DO UPDATE " +
                        "SET supplier_id = '" + supplier.getId() + "', name = '" + supplier.getName() + "', description = '" + supplier.getDescription() + "';";
        executeQuery(query);
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE supplier_id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                Supplier result = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Supplier find(String name) {
        String query = "SELECT * FROM suppliers WHERE LOWER(name) ='" + name + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                Supplier result = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM suppliers WHERE supplier_id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM suppliers;";

        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier actSupplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                resultList.add(actSupplier);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
