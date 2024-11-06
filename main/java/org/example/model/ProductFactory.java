package org.example.model;

public interface ProductFactory {
    Product createProduct(int id, String name, String category, int quantity);
}
