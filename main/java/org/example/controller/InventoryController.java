package org.example.controller;

import org.example.model.DatabaseConnection;
import org.example.model.Product;
import org.example.model.SimpleProductFactory;
import org.example.model.ProductFactory;

import java.sql.*;
import java.util.Scanner;

public class InventoryController {
    private DatabaseConnection dbConnection;
    private ProductFactory productFactory;

    public InventoryController() {
        this.dbConnection = DatabaseConnection.getInstance();
        this.productFactory = new SimpleProductFactory();
    }

    public void addProduct(Scanner scanner) {
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();
        System.out.print("Введите категорию: ");
        String category = scanner.nextLine();
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        try {
            String query = "INSERT INTO products (name, category, quantity) VALUES (?, ?, ?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setInt(3, quantity);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                Product newProduct = productFactory.createProduct(id, name, category, quantity); // Создание продукта через фабрику
                System.out.println("Продукт успешно добавлен: " + newProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(Scanner scanner) {
        System.out.print("Введите ID продукта для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Продукт успешно удален.");
            } else {
                System.out.println("Продукт не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewProducts() {
        try {
            String query = "SELECT * FROM products";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                System.out.println("ID: " + id + ", Название: " + name + ", Категория: " + category + ", Количество: " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
