package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabor on 2017.04.28..
 */
public class ShopCart {

    private Integer id;
    private String status;
    private List<LineItem> shoppingCart;
    private static ShopCart instance;
    float totalPrice = 0;


    public void addShoppingCart(Product product) {
        boolean notFound = true;
        for(LineItem lineItem  : shoppingCart) {
            if(lineItem.getProduct().getId() == product.getId()) {
                lineItem.setQuantity(lineItem.getQuantity()+1);
                notFound = false;
            }
        }
        if (notFound) {
            LineItem lineItem = new LineItem(product, 1);
            shoppingCart.add(lineItem);
        }
    }

    public LineItem getShoppingCart(int id) {
        return shoppingCart.get(id);
    }

    public static ShopCart getInstance() {
        if (instance == null) {
            instance = new ShopCart();
        }
        return instance;
    }

    public List<LineItem> getAllCarts() {
        return shoppingCart;
    }

    public ShopCart() {
        shoppingCart = new ArrayList<>();

    }

    public float getTotalPrice() {
        for (LineItem lineItem: shoppingCart) {
            this.totalPrice += lineItem.getPrice();
        }
        return totalPrice;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }



}