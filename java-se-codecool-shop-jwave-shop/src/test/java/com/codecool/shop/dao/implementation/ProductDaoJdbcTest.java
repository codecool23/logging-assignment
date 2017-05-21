package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by kyrka on 2017.05.11..
 */
public class ProductDaoJdbcTest {

    @Test
    public void add() {
        ProductCategory productcategory = new ProductCategory(137, "", "", "");
        Supplier supplier = new Supplier(137, "", "");
        ProductDao productDao = new ProductDaoJdbc();
        Product product = new Product(137, " ", (float) 42.9, "USD", " ", productcategory, supplier);
        productDao.add(product);
        assertNotNull(productDao.find(137));
    }

    @Test
    public void find() {
        ProductDao productDao = new ProductDaoJdbc();
        assertEquals(1, productDao.find(1));
    }

    @Test
    public void getAll() {
        ProductDao productDao = new ProductDaoJdbc();
        assertEquals(null, productDao.find(-7));
    }

    @Test
    public void getBySupplier() {
        ProductDao productDao = new ProductDaoJdbc();
        Supplier supplier = new Supplier(137, "", "");
        assertTrue(productDao.getBy(supplier).isEmpty());
    }

    @Test
    public void getByProductCategory() {
        ProductDao productDao = new ProductDaoJdbc();
        ProductCategory productcategory = new ProductCategory(137, "", "", "");
        assertTrue(productDao.getBy(productcategory).isEmpty());
    }

    @Test
    public void remove() {
        ProductDao productDao = new ProductDaoJdbc();
        ProductCategory productcategory = new ProductCategory(731, "", "", "");
        Supplier supplier = new Supplier(731, "", "");
        Product product2 = new Product(731, " ", (float) 42.9, "USD", " ", productcategory, supplier);
        productDao.add(product2);
        productDao.remove(731);
        Product product_found = productDao.find(731);
        assertEquals(null, product_found);
    }
}
