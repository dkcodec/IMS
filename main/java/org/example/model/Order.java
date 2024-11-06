package org.example.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private String productName;
    private String productCategory;
    private int quantity;
    private Timestamp orderDate;

    public Order(int id, String productName, String productCategory, int quantity, Timestamp orderDate) {
        this.id = id;
        this.productName = productName;
        this.productCategory = productCategory;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "ID заказа: " + id +
                ", Продукт: " + productName +
                " (" + productCategory + ")" +
                ", Количество: " + quantity +
                ", Дата заказа: " + orderDate;
    }
}
