package org.example.controller;

import org.example.model.DatabaseConnection;
import org.example.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private DatabaseConnection dbConnection;

    public OrderController() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    // Метод для создания нового заказа
    public void createOrder(Scanner scanner) {
        System.out.print("Введите ID продукта для заказа: ");
        int productId = scanner.nextInt();
        System.out.print("Введите количество для заказа: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера после ввода числа

        try {
            // Проверка наличия продукта в инвентаре
            if (!isProductAvailable(productId, quantity)) {
                System.out.println("Недостаточное количество продукта на складе для создания заказа.");
                return;
            }

            // Создание заказа
            String insertOrderQuery = "INSERT INTO orders (product_id, quantity) VALUES (?, ?)";
            PreparedStatement orderStatement = dbConnection.getConnection().prepareStatement(insertOrderQuery);
            orderStatement.setInt(1, productId);
            orderStatement.setInt(2, quantity);
            orderStatement.executeUpdate();

            // Обновление количества продукта в инвентаре
            updateProductQuantity(productId, -quantity);
            System.out.println("Заказ успешно создан.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для отображения всех заказов
    public void viewOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT o.id AS order_id, o.quantity AS order_quantity, o.order_date, " +
                    "p.name AS product_name, p.category AS product_category " +
                    "FROM orders o " +
                    "JOIN products p ON o.product_id = p.id";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Список всех заказов:");
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int orderQuantity = resultSet.getInt("order_quantity");
                Timestamp orderDate = resultSet.getTimestamp("order_date");
                String productName = resultSet.getString("product_name");
                String productCategory = resultSet.getString("product_category");

                Order order = new Order(orderId, productName, productCategory, orderQuantity, orderDate);
                orders.add(order);
                System.out.println(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод проверки доступного количества продукта
    private boolean isProductAvailable(int productId, int requestedQuantity) throws SQLException {
        String query = "SELECT quantity FROM products WHERE id = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int availableQuantity = resultSet.getInt("quantity");
            return availableQuantity >= requestedQuantity;
        }
        return false;
    }

    // Метод обновления количества продукта после создания заказа
    private void updateProductQuantity(int productId, int quantityChange) throws SQLException {
        String updateQuery = "UPDATE products SET quantity = quantity + ? WHERE id = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(updateQuery);
        statement.setInt(1, quantityChange);
        statement.setInt(2, productId);
        statement.executeUpdate();
    }
}
