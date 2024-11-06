package org.example;

import org.example.controller.InventoryController;
import org.example.controller.OrderController;
import org.example.view.ConsoleView;

public class MainApp {
    public static void main(String[] args) {
        InventoryController inventoryController = new InventoryController();
        OrderController orderController = new OrderController();
        ConsoleView view = new ConsoleView(inventoryController, orderController);

        view.showMenu();
    }
}
