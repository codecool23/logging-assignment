package com.codecool.shop.model;

/**
 * Created by gabor on 2017.05.10..
 */
public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return quantity*product.getDefaultPrice();
    }

    public String getName() {
        return product.getName();
    }


}

