package org.example.view;


import org.example.controller.InventoryController;
import org.example.controller.OrderController;

import java.util.Scanner;

public class ConsoleView {
    private InventoryController inventoryController;
    private OrderController orderController;
    private Scanner scanner;

    public ConsoleView(InventoryController inventoryController, OrderController orderController) {
        this.inventoryController = inventoryController;
        this.orderController = orderController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Удалить продукт");
            System.out.println("3. Показать все продукты");
            System.out.println("4. Создать заказ");
            System.out.println("5. Показать все заказы");
            System.out.println("6. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> inventoryController.addProduct(scanner);
                case 2 -> inventoryController.removeProduct(scanner);
                case 3 -> inventoryController.viewProducts();
                case 4 -> orderController.createOrder(scanner);
                case 5 -> orderController.viewOrders();
                case 6 -> {
                    System.out.println("Завершение программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}

