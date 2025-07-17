package com.practice.test;

import com.practice.model.*;
import com.practice.service.DataAccess;
import com.practice.service.InvoiceGenerator;
import com.practice.service.ShoppingCart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StoreApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DataAccess dataAccess = new DataAccess();

    private static List<Product> products;
    private static List<Customer> customers;
    private static List<Order> orders;

    public static void main(String[] args) {
        loadData();

       
        while (true) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Choose your role: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    handleAdminMenu();
                    break;
                case 2:
                    handleCustomerMenu();
                    break;
                case 3:
                    // Final save before exiting
                    saveAllData();
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        products = (List<Product>) dataAccess.loadData(DataAccess.PRODUCTS_FILE);
        customers = (List<Customer>) dataAccess.loadData(DataAccess.CUSTOMERS_FILE);
        orders = (List<Order>) dataAccess.loadData(DataAccess.ORDERS_FILE);
        System.out.println("Data loaded successfully.");
    }

    private static void saveAllData() {
        dataAccess.saveData(products, DataAccess.PRODUCTS_FILE);
        dataAccess.saveData(customers, DataAccess.CUSTOMERS_FILE);
        dataAccess.saveData(orders, DataAccess.ORDERS_FILE);
        System.out.println("All data saved successfully.");
    }

    private static void handleAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Display All Products");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    displayAllProducts();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = getDoubleInput();
        System.out.print("Enter discount percent: ");
        double discount = getDoubleInput();

        int newId = products.stream().mapToInt(Product::getId).max().orElse(0) + 1;
        products.add(new Product(newId, name, price, discount));
        dataAccess.saveData(products, DataAccess.PRODUCTS_FILE); // Save immediately
        System.out.println("Product added successfully!");
    }

    private static void removeProduct() {
        displayAllProducts();
        System.out.print("Enter product ID to remove: ");
        int id = getIntInput();
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) {
            dataAccess.saveData(products, DataAccess.PRODUCTS_FILE); // Save immediately
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void updateProduct() {
        displayAllProducts();
        System.out.print("Enter product ID to update: ");
        int id = getIntInput();
        Product productToUpdate = findProductById(id);

        if (productToUpdate == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new name (or press Enter to keep '" + productToUpdate.getName() + "'): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            productToUpdate.setName(newName);
        }

        System.out.print("Enter new price (or press Enter to keep '" + productToUpdate.getPrice() + "'): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.trim().isEmpty()) {
            try {
                productToUpdate.setPrice(Double.parseDouble(priceStr));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Price not updated.");
            }
        }
        dataAccess.saveData(products, DataAccess.PRODUCTS_FILE); 
        System.out.println("Product updated successfully!");
    }

    private static void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in the catalog.");
            return;
        }
        System.out.println("\n--- Available Products ---");
        products.forEach(System.out::println);
    }

    private static void handleCustomerMenu() {
        System.out.print("Enter your name to login/sign-up: ");
        String name = scanner.nextLine();
        Customer currentCustomer = customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        boolean isNewCustomer = false;
        if (currentCustomer == null) {
            int newId = customers.stream().mapToInt(Customer::getId).max().orElse(0) + 1;
            currentCustomer = new Customer(newId, name);
            customers.add(currentCustomer);
            isNewCustomer = true;
            System.out.println("Welcome, " + name + "! Your new account has been created.");
        } else {
            System.out.println("Welcome back, " + name + "!");
        }
        
        if (isNewCustomer) {
            dataAccess.saveData(customers, DataAccess.CUSTOMERS_FILE); // Save new customer
        }

        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View All Products");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Item from Cart");
            System.out.println("5. Place Order & Get Bill");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addItemToCart(cart);
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    removeItemFromCart(cart);
                    break;
                case 5:
                    placeOrder(currentCustomer, cart);
                    break;
                case 6:
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addItemToCart(ShoppingCart cart) {
        displayAllProducts();
        System.out.print("Enter the Product ID to add to your cart: ");
        int id = getIntInput();
        Product product = findProductById(id);

        if (product != null) {
            System.out.print("Enter quantity: ");
            int quantity = getIntInput();
            if (quantity > 0) {
                cart.addItem(product, quantity);
                System.out.println("'" + product.getName() + "' has been added to your cart.");
            } else {
                System.out.println("Invalid quantity.");
            }
        } else {
            System.out.println("Product ID not found.");
        }
    }

    private static void removeItemFromCart(ShoppingCart cart) {
        cart.viewCart();
        if (cart.getItems().isEmpty()) return;

        System.out.print("Enter the Product ID to remove from your cart: ");
        int id = getIntInput();

        if (cart.removeItem(id)) {
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product ID not found in your cart.");
        }
    }

    private static void placeOrder(Customer customer, ShoppingCart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Cannot place an order.");
            return;
        }

        int newOrderId = orders.stream().mapToInt(Order::getId).max().orElse(0) + 1;
        Order newOrder = new Order(newOrderId, new Date());
        newOrder.setItems(new ArrayList<>(cart.getItems()));

        orders.add(newOrder);
        customer.addOrder(newOrder);

        // Save orders and customers data immediately after placing an order
        dataAccess.saveData(orders, DataAccess.ORDERS_FILE);
        dataAccess.saveData(customers, DataAccess.CUSTOMERS_FILE);

        System.out.println("\nOrder placed successfully! Here is your bill:");
        System.out.println(InvoiceGenerator.generateInvoice(customer, newOrder));
        
        cart.clearCart();
    }

    // --- UTILITY METHODS ---

    private static Product findProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a whole number: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}