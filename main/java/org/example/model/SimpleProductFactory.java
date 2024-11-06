package org.example.model;

public class SimpleProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, String category, int quantity) {
        return new Product(id, name, category, quantity);
    }
}