# Inventory Management System (IMS)

**Inventory Management System (IMS)** is a desktop application in Java for inventory and order management. This application enables users to add, delete, and view products in inventory and create orders. IMS uses architectural design patterns such as Singleton, Factory Method, Facade, and MVC to improve code organization and scalability.

## Table of Contents

- [Technologies](#technologies)
- [Features](#features)
- [Design Patterns](#design-patterns)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Database](#database)
- [Potential Improvements](#potential-improvements)

## Technologies

- **Java 17+**
- **PostgreSQL** as the database
- **Maven** for dependency management

## Features

- **Inventory Management**: Add, delete, and view products in the inventory.
- **Order Creation**: Create orders with product ID and quantity.
- **Order View**: View all orders with detailed information for each order.
- **Graphical Interface**: JavaFX provides an intuitive GUI with buttons and tables for data management.

## Design Patterns

The project implements the following design patterns:

1. **Singleton**: Used for creating a single instance of the database connection via `DatabaseConnection`, preventing excessive connections and ensuring efficiency.
2. **Factory Method**: `ProductFactory` creates `Product` objects, abstracting and standardizing the product creation logic.
3. **Facade**: `InventoryController` and `OrderController` provide a simple, intuitive interface for working with products and orders, encapsulating database interaction details.
4. **MVC (Model-View-Controller)**: The project is divided into three layers — Model (data models), View (graphical interface), and Controller (business logic handling), simplifying maintenance and scalability.

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dkcodec/ims.git

2. **Set up the PostgreSQL database**:
   - Create a database named `inventory_management` and add the `products` and `orders` tables.
   - Example SQL commands to create the tables:
     ```sql
     CREATE TABLE products (
         id SERIAL PRIMARY KEY,
         name VARCHAR(100) NOT NULL,
         category VARCHAR(50),
         quantity INT NOT NULL
     );

     CREATE TABLE orders (
         id SERIAL PRIMARY KEY,
         product_id INT REFERENCES products(id),
         quantity INT NOT NULL,
         order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     );
     ```

3. **Configure the properties file**:
   - In the `resources` folder, create a file named `database.properties` and add the following database connection settings:
     ```properties
     db.url=jdbc:postgresql://localhost:5432/inventory_management
     db.user=your_username
     db.password=your_password
     ```

4. **Build the project with Maven**:
   ```bash
   mvn clean install
This command will compile the project and resolve all necessary dependencies.

5. **Run the application**:
   - Use your IDE to run the `MainApp` class, which will launch the JavaFX GUI.

   - Alternatively, if a JAR file has been generated, you can run the application from the command line:
     ```bash
     java -jar target/inventory-management-system.jar
     ```

   **Note**: Ensure that your PostgreSQL server is running, and the connection properties in `database.properties` are correct before launching the application.

6. **Using the Application**:
   - The main screen provides buttons for adding, deleting, and viewing products, as well as creating and viewing orders.
   - Click the relevant button to open a new window for each action:
      - **Add Product**: Opens a form to enter product details (name, category, quantity).
      - **Delete Product**: Allows you to delete a product by entering its ID.
      - **View Products**: Displays a table with all products in the inventory.
      - **Create Order**: Opens a form to create an order by specifying the product ID and quantity.
      - **View Orders**: Shows a table of all orders with details such as product name, quantity, and order date.

You’re now ready to use the Inventory Management System!

## Project Structure

The project is organized as follows:

```plaintext
InventoryManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.inventorymanagement/
│   │   │       ├── controller/
│   │   │       │   ├── InventoryController.java
│   │   │       │   ├── OrderController.java
│   │   │       │   └── MainController.java
│   │   │       ├── model/
│   │   │       │   ├── DatabaseConnection.java
│   │   │       │   ├── Product.java
│   │   │       │   ├── Order.java
│   │   │       │   ├── ProductFactory.java
│   │   │       │   └── SimpleProductFactory.java
│   │   │       └── view/
│   │   │           ├── ConsoleView.java
│   │   │           ├── MainView.fxml
│   │   │           ├── ProductForm.fxml
│   │   │           └── OrderForm.fxml
├── resources/
│   └── database.properties
└── pom.xml
```   


## Potential Improvements

- **Implementing the Observer Pattern**: This pattern could be used to dynamically update the interface when data changes in the database, ensuring that the latest information is displayed in real-time.

- **Adding Undo Functionality with the Command Pattern**: The Command pattern could facilitate undo/redo actions for operations on products and orders by encapsulating each action in a standardized format, making it easier to manage them consistently.

- **Internationalization**: Implementing localization to support multiple languages for a wider audience.

- **Adding Reporting and Analytics**: Advanced reporting features to provide insights into inventory levels, order history, and trends, making inventory management more strategic and data-driven.

## Conclusion

The Inventory Management System (IMS) is a robust application for managing inventory and orders, designed with scalability and maintainability in mind. The use of design patterns such as Singleton, Factory Method, Facade, and MVC helps create a well-organized and modular codebase, making the system suitable for real-world business use and future enhancements.
