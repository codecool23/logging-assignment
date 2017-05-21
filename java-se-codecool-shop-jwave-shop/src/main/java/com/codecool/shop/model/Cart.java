package com.codecool.shop.model;

/**
 * Created by zahoranszky on 2017.04.27..
 */
import java.util.ArrayList;
import java.util.List;


public class Cart {

    List<Pro> cartItems = new ArrayList<Pro>();

    public void addProductToCartByPID(int pid) {
        Pro pro = getProductByProductID(pid);
        addToCart(pro);
    }

    private Pro getProductByProductID(int pid) {
        Pro pro = null;
        List<Pro> products = new Products().getProducts();
        for (Pro prod: products) {
            if (prod.getPid() == pid) {
                pro = prod;
                break;
            }
        }
        return pro;
    }

    private void addToCart(Pro pro) {
        cartItems.add(pro);
    }

    public void removeProductByPID(int pid) {
        Pro prod = getProductByProductID(pid);
        cartItems.remove(prod);
    }

    void printCartItems() {
        for (Pro prod: cartItems) {
            System.out.println(prod.getName());
        }
    }


}
