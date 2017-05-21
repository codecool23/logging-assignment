package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
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
public class ProductDaoJdbc extends JdbcConnection implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoJdbc.class);


    private static ProductDaoJdbc instance = null;
    protected ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            logger.debug("Creating new {}", ProductDaoJdbc.class.getSimpleName());
            instance = new ProductDaoJdbc();
        }
        return instance;
    }
    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (id, name, defaultPrice, currencyString, description, cat_id, sup_id) " +
                        "VALUES ('" + product.getId() + "', '" + product.getName() + "', '" + product.getDefaultPrice() + "', '" + product.getDefaultCurrency() + "', '" + product.getDescription() + "', '" + product.getProductCategory().getId() + "', '" + product.getSupplier().getId() + "') " +
                        "ON CONFLICT (name) DO UPDATE " +
                        "SET id = '" + product.getId() + "', name = '" + product.getName() + "', defaultPrice = '" + product.getDefaultPrice() + "', currencyString = '" + product.getDefaultCurrency() + "', description = '" + product.getDescription() + "', cat_id = '" + product.getProductCategory().getId() + "', sup_id = '" + product.getSupplier().getId() + "';";
        executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products " +
                "INNER JOIN suppliers ON products.sup_id = suppliers.supplier_id " +
                "INNER JOIN categories ON products.cat_id = categories.category_id "+
                "WHERE id =" + id + ";";


        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){

            if (resultSet.next()){

                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));

                Supplier sup = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));

                Product result = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        prodCat,
                        sup);
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
        String query = "DELETE FROM products WHERE id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products;";

        List<Product> resultList = new ArrayList<>();
        ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                int prodCatId = resultSet.getInt("cat_id");
                ProductCategory productCategory = productCategoryDaoJdbc.find(prodCatId);
                int supId = resultSet.getInt("sup_id");
                Supplier supplier = supplierDaoJdbc.find(supId);
                Product actProduct = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier
                        );
                resultList.add(actProduct);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {

        String query = "SELECT * FROM products WHERE sup_id = '" + supplier.getId() + "';";

        List<Product> resultList = new ArrayList<>();
        ProductCategoryDaoJdbc productCategoryDataStore = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc supplierDataStore = new SupplierDaoJdbc();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
//                int prodCatId = resultSet.getInt("cat_id");
//                ProductCategory productCategory = productCategoryDaoJdbc.find(prodCatId);
//                int supId = resultSet.getInt("sup_id");
//                Supplier sup = supplierDaoJdbc.find(supplier.getId());
                Product actProduct = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        productCategoryDataStore.find(resultSet.getInt("cat_id")),
                        supplierDataStore.find(resultSet.getInt("sup_id"))
                );
                resultList.add(actProduct);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE cat_id = '" + productCategory.getId() + "';";

        List<Product> resultList = new ArrayList<>();
        ProductCategoryDaoJdbc productCategoryDataStore = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc supplierDataStore = new SupplierDaoJdbc();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
//                int prodCatId = resultSet.getInt("cat_id");
//                ProductCategory productCategory = productCategoryDaoJdbc.find(prodCatId);
//                int supId = resultSet.getInt("sup_id");
//                Supplier supplier = supplierDaoJdbc.find(supId);
                Product actProduct = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        productCategoryDataStore.find(resultSet.getInt("cat_id")),
                        supplierDataStore.find(resultSet.getInt("sup_id"))
                );
                resultList.add(actProduct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
