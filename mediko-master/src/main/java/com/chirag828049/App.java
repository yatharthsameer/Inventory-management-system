package com.chirag828049;

import java.util.Scanner;
import java.util.Vector;

public final class App {

    private static Vector<Manufacturer> manufacturerList = new Vector<Manufacturer>();
    private static Vector<Shop> shopList = new Vector<Shop>();
    private static Vector<Product> productList = new Vector<Product>();
    private static Vector<DeliveryAgent> deliveryAgentList = new Vector<DeliveryAgent>();
    private static Vector<Customer> customerList = new Vector<Customer>();

    // ID for purchases is autogenarated by this static variable
    private static int purchaseID = 1;

    // ID of other entities are given by the user and care is taken that the ID
    // doesn't previously exist for that type of entity.

    private App() {
    }

    // METHODS RELATED TO MANUFACTURER

    private static void addManufacturer(Scanner sc) {

        System.out.println("Enter ID and Name of manufacturer to add");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.print("Name : ");
            String name = sc.nextLine();

            boolean isPresent = false;
            for (Manufacturer temp : manufacturerList) {

                if (temp.getID() == ID) {
                    System.out.println("Manufacturer with this ID is already present. Not Added.");
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {

                boolean isAdded = manufacturerList.add(new Manufacturer(ID, name));
                if (isAdded)
                    System.out.println("Successfully added manufacturer.");
                else
                    System.out.println("Error saving manufacturer. Please try again");
            }

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
        }
    }

    private static void printManufacturers() {

        System.out.println("\nPrinting list of Manufacturers.");
        System.out.println("+------+-------------------+");
        manufacturerList.forEach((manufacturer) -> {
            System.out.print("|  " + manufacturer.getID());
            for (int i = 0; i < 4 - lengthOfNumber(manufacturer.getID()); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + manufacturer.getName());
            for (int i = 0; i < 15 - manufacturer.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        });
        System.out.println("+------+-------------------+");
    }

    private static int getManufacturerIndex(Scanner sc) {

        printManufacturers();
        System.out.println("\nEnter a Manufacturer ID to select it.");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            boolean isFound = false;
            int index = -1;

            for (Manufacturer temp : manufacturerList) {

                index++;
                if (temp.getID() == ID) {
                    System.out.println("Selected " + temp.getID() + " " + temp.getName());
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                return index;
            } else
                System.out.println("Manufacturer ID not found. Please try again.");

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
            return -1;
        }

        return -1;
    }

    private static void deleteManufacturer(Scanner sc) {

        int index = getManufacturerIndex(sc);

        if (index != -1) {
            System.out.println(
                    "Deleting " + manufacturerList.get(index).getID() + " " + manufacturerList.get(index).getName());

            // invoke manufacturer's delete function to nullify its product's manufacturer.
            manufacturerList.get(index).deleteManufacturer();

            // remove manufacturer from list
            manufacturerList.remove(index);
        }
    }

    private static void printProductsOfManufacturer(Scanner sc) {

        int index = getManufacturerIndex(sc);

        if (index != -1) {
            Manufacturer tempManufacturer = manufacturerList.get(index);
            Vector<Product> tempProductList = tempManufacturer.getAllProductsList();
            System.out.println("\nPrinting all products of " + tempManufacturer.getName());
            System.out.println("+------+-------------------+");
            tempProductList.forEach((product) -> {
                System.out.print("|  " + product.getID());
                for (int i = 0; i < 4 - lengthOfNumber(product.getID()); i++) {
                    System.out.print(" ");
                }
                System.out.print("|    " + product.getName());
                for (int i = 0; i < 15 - product.getName().length(); i++) {
                    System.out.print(" ");
                }
                System.out.println("|");
            });
            System.out.println("+------+-------------------+");
        }
    }

    // Panel containing manufacturer specific options.
    private static void manufacturerPanel(Scanner sc) {

        int choice = 0;
        System.out.println("\nWelcome to Manufacturer panel");

        do {
            System.out.println("\nChoose from the below options.");
            System.out.println("1. Add a manufacturer");
            System.out.println("2. Delete a manufacturer");
            System.out.println("3. Print all manufacturers");
            System.out.println("4. Print all products of a manufacturer");
            System.out.println("Enter a choice between 1-4. Enter 5 to go back to main menu.\n\n");

            try {

                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                choice = 0;
                System.out.println("Invalid choice.\n");
            }

            switch (choice) {

                case 1:
                    // Add a manufacturer.
                    addManufacturer(sc);
                    break;

                case 2:
                    // Delete a manufacturer
                    deleteManufacturer(sc);
                    break;

                case 3:
                    // Print all manufacturers.
                    printManufacturers();
                    break;

                case 4:
                    // Print all products of a manufacturer.
                    printProductsOfManufacturer(sc);
                    break;

                default:
                    break;
            }
        } while (choice != 5);

        System.out.println("Exiting Manufacturer panel.\n");
    }

    // METHODS RELATED TO SHOP

    private static void addShop(Scanner sc) {

        System.out.println("Enter ID, Name and zipcode of shop to add");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.print("Name : ");
            String name = sc.nextLine();
            System.out.print("ZipCode : ");
            int zipcode = Integer.parseInt(sc.nextLine());

            boolean isPresent = false;
            for (Shop temp : shopList) {

                if (temp.getID() == ID) {
                    System.out.println("Shop with this ID is already present. Not Added.");
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {

                boolean isAdded = shopList.add(new Shop(ID, name, zipcode));
                if (isAdded)
                    System.out.println("Successfully added shop.");
                else
                    System.out.println("Error saving shop. Please try again");
            }

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
        }
    }

    private static void printShops() {

        System.out.println("\nPrinting list of Shops.");
        System.out.println("+------+-------------------+----------+");

        shopList.forEach((shop) -> {
            System.out.print("|  " + shop.getID());
            for (int i = 0; i < 4 - lengthOfNumber(shop.getID()); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + shop.getName());
            for (int i = 0; i < 15 - shop.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|  " + shop.getZipCode());
            for (int i = 0; i < 8 - lengthOfNumber(shop.getZipCode()); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        });
        System.out.println("+------+-------------------+----------+");

    }

    private static int getShopIndex(Scanner sc) {

        printShops();
        System.out.println("\nEnter a Shop ID to select it.");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            boolean isFound = false;
            int index = -1;

            for (Shop temp : shopList) {

                index++;
                if (temp.getID() == ID) {
                    System.out.println("Selected " + temp.getID() + " " + temp.getName());
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                return index;
            } else
                System.out.println("Shop ID not found. Please try again.");

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
            return -1;
        }

        return -1;
    }

    private static void deleteShop(Scanner sc) {

        int index = getShopIndex(sc);

        if (index != -1) {

            shopList.remove(index);
        }

    }

    private static void printProductsOfShop(Scanner sc) {

        int index = getShopIndex(sc);

        if (index != -1) {

            shopList.get(index).printAllProducts();
        }
    }

    private static void addProductToShop(Scanner sc) {

        int shopIndex = getShopIndex(sc);

        if (shopIndex != -1) {

            // select product
            int productIndex = getProductIndex(sc);

            if (productIndex != -1) {

                if (productList.get(productIndex).getManufacturer() == null) {

                    System.out.println("The manufacturer of this product is closed. Please try some other product.");
                    return;
                }

                try {

                    System.out.println("Enter the quantity of product you want to add");
                    int quantity = Integer.parseInt(sc.nextLine());

                    if (quantity > 0) {
                        shopList.get(shopIndex).addProduct(productList.get(productIndex), quantity);
                    } else {
                        System.out.println("Quantity should be positive");
                    }
                } catch (Exception e) {

                    System.out.println("Invalid input.");
                }
            }
        }
    }

    // Panel containing shop specific options.
    private static void shopPanel(Scanner sc) {

        int choice = 0;
        System.out.println("\nWelcome to Shop panel");

        do {
            System.out.println("\nChoose from the below options.");
            System.out.println("1. Add a shop");
            System.out.println("2. Delete a shop");
            System.out.println("3. Print all shops");
            System.out.println("4. Print all products of a shop with their quantity");
            System.out.println("5. Add product to a shop");
            System.out.println("Enter a choice between 1-5. Enter 6 to go back to main menu.\n\n");

            try {

                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                choice = 0;
                System.out.println("Invalid choice.\n");
            }

            switch (choice) {

                case 1:
                    // Add a Shop.
                    addShop(sc);
                    break;

                case 2:
                    // Delete a shop
                    deleteShop(sc);
                    break;

                case 3:
                    // Print all shops.
                    printShops();
                    break;

                case 4:
                    // Print all products available in a shop.
                    printProductsOfShop(sc);
                    break;

                case 5:
                    // add product to a shop
                    addProductToShop(sc);
                    break;

                default:
                    break;
            }
        } while (choice != 6);

        System.out.println("Exiting Shop panel.\n");
    }

    // METHODS RELATED TO PRODUCT

    private static void addProduct(Scanner sc) {

        System.out.println("Enter ID and Name of product to add");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.print("Name : ");
            String name = sc.nextLine();

            boolean isPresent = false;
            for (Product temp : productList) {

                if (temp.getID() == ID) {
                    System.out.println("Product with this ID is already present. Not Added.");
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {

                // select a manufacturer for this product
                int manufacturerIndex = -1;
                do {

                    manufacturerIndex = getManufacturerIndex(sc);
                } while (manufacturerIndex == -1);

                boolean isAdded = productList.add(new Product(ID, name, manufacturerList.get(manufacturerIndex)));
                if (isAdded)
                    System.out.println("Successfully added product.");
                else
                    System.out.println("Error saving product. Please try again");
            }

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
        }
    }

    private static int getProductIndex(Scanner sc) {

        printProducts();
        System.out.println("\nEnter a Product ID to select it.");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            boolean isFound = false;
            int index = -1;

            for (Product temp : productList) {

                index++;
                if (temp.getID() == ID) {
                    System.out.println("Selected " + temp.getID() + " " + temp.getName());
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                return index;
            } else
                System.out.println("Product ID not found. Please try again.");

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
            return -1;
        }

        return -1;
    }

    private static void printProducts() {

        System.out.println("\nPrinting list of Products.");
        System.out.println("+------+-------------------+-------------------+");

        productList.forEach((product) -> {

            System.out.print("|  " + product.getID());
            for (int i = 0; i < 4 - lengthOfNumber(product.getID()); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + product.getName());
            for (int i = 0; i < 15 - product.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + product.getManufacturer().getName());
            for (int i = 0; i < 15 - product.getManufacturer().getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        });
        System.out.println("+------+-------------------+-------------------+");

    }

    private static void deleteProduct(Scanner sc) {

        int index = getProductIndex(sc);

        if (index != -1) {

            // remove product from manufacturer list
            productList.get(index).getManufacturer().deleteProduct(productList.get(index));

            // remove product from all shops
            for (Shop shop : shopList) {
                shop.deleteProduct(productList.get(index));
            }

            // remove product from product list
            productList.remove(index);
        }
    }

    // Panel containing product specific options.
    private static void productPanel(Scanner sc) {

        int choice = 0;
        System.out.println("\nWelcome to Product panel");

        do {
            System.out.println("\nChoose from the below options.");
            System.out.println("1. Add a product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print all products");
            System.out.println("Enter a choice between 1-3. Enter 4 to go back to main menu.\n\n");

            try {

                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                choice = 0;
                System.out.println("Invalid choice.\n");
            }

            switch (choice) {

                case 1:
                    // Add a product.
                    addProduct(sc);
                    break;

                case 2:
                    // Delete a product.
                    deleteProduct(sc);
                    break;

                case 3:
                    // Print all products.
                    printProducts();
                    break;

                default:
                    break;
            }
        } while (choice != 4);

        System.out.println("Exiting Product panel.\n");
    }

    // METHODS RELATED TO DELIVERY AGENT

    private static void addDeliveryAgent(Scanner sc) {

        System.out.println("Enter ID, Name and zipcode of delivery agent to add");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.print("Name : ");
            String name = sc.nextLine();
            System.out.print("ZipCode : ");
            int zipcode = Integer.parseInt(sc.nextLine());

            boolean isPresent = false;
            for (DeliveryAgent temp : deliveryAgentList) {

                if (temp.getID() == ID) {
                    System.out.println("Delivery Agent with this ID is already present. Not Added.");
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {

                boolean isAdded = deliveryAgentList.add(new DeliveryAgent(ID, name, zipcode));
                if (isAdded)
                    System.out.println("Successfully added delivery agent.");
                else
                    System.out.println("Error saving delivery agent. Please try again");
            }

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
        }
    }

    private static void printDeliveryAgents() {

        System.out.println("\nPrinting list of Delivery Agents.");
        System.out.println("+------+-------------------+----------+");

        deliveryAgentList.forEach((agent) -> {
            System.out.print("|  " + agent.getID());
            for (int i = 0; i < 4 - lengthOfNumber(agent.getID()); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + agent.getName());
            for (int i = 0; i < 15 - agent.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|  " + agent.getZipCode());
            for (int i = 0; i < 8 - agent.getZipCode(); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        });
        System.out.println("+------+-------------------+----------+");

    }

    private static void deleteDeliveryAgent(Scanner sc) {

        printDeliveryAgents();
        System.out.println("\nEnter a Delivery Agent ID to delete it.");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            boolean isFound = false;
            int index = -1;

            for (DeliveryAgent temp : deliveryAgentList) {

                index++;
                if (temp.getID() == ID) {
                    System.out.println("Deleting " + temp.getID() + " " + temp.getName());
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                deliveryAgentList.remove(index);
            } else
                System.out.println("Delivery Agent ID not found. Please try again.");

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
        }
    }

    // Panel containing delivery agent specific options.
    private static void deliveryAgentPanel(Scanner sc) {

        int choice = 0;
        System.out.println("\nWelcome to Delivery Agent panel");

        do {
            System.out.println("\nChoose from the below options.");
            System.out.println("1. Add a Delivery Agent.");
            System.out.println("2. Delete a Delivery Agent.");
            System.out.println("3. Print all Delivery Agents.");
            System.out.println("Enter a choice between 1-3. Enter 4 to go back to main menu.\n\n");

            try {

                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                choice = 0;
                System.out.println("Invalid choice.\n");
            }

            switch (choice) {

                case 1:
                    // Add a product.
                    addDeliveryAgent(sc);
                    break;

                case 2:
                    // Delete a product.
                    deleteDeliveryAgent(sc);
                    break;

                case 3:
                    // Print all products.
                    printDeliveryAgents();
                    break;

                default:
                    break;
            }
        } while (choice != 4);

        System.out.println("Exiting Delivery Agent panel.\n");
    }

    // METHODS RELATED TO CUSTOMER

    private static int getCustomerIndex(Scanner sc) {

        printCustomers();
        System.out.println("\nEnter a Customer ID to select it.");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            boolean isFound = false;
            int index = -1;

            for (Customer temp : customerList) {

                index++;
                if (temp.getID() == ID) {
                    System.out.println("Selected " + temp.getID() + " " + temp.getName());
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                return index;
            } else
                System.out.println("Customer ID not found. Please try again.");

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
            return -1;
        }

        return -1;
    }

    private static void addCustomer(Scanner sc) {

        System.out.println("Enter ID, Name and zipcode of customer to add");
        try {

            System.out.print("ID : ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.print("Name : ");
            String name = sc.nextLine();
            System.out.print("ZipCode : ");
            int zipcode = Integer.parseInt(sc.nextLine());

            boolean isPresent = false;
            for (Customer temp : customerList) {

                if (temp.getID() == ID) {
                    System.out.println("Customer with this ID is already present. Not Added.");
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {

                boolean isAdded = customerList.add(new Customer(ID, name, zipcode));
                if (isAdded)
                    System.out.println("Successfully added customer.");
                else
                    System.out.println("Error saving customer. Please try again");
            }

        } catch (Exception e) {

            System.out.println("Some error occured. Please try again");
        }
    }

    private static void printCustomers() {

        System.out.println("\nPrinting list of Customers.");
        System.out.println("+------+-------------------+----------+");

        customerList.forEach((customer) -> {
            System.out.print("|  " + customer.getID());
            for (int i = 0; i < 4 - lengthOfNumber(customer.getID()); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + customer.getName());
            for (int i = 0; i < 15 - customer.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|  " + customer.getZipCode());
            for (int i = 0; i < 8 - customer.getZipCode(); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        });
        System.out.println("+------+-------------------+----------+");

    }

    private static void deleteCustomer(Scanner sc) {

        int index = getCustomerIndex(sc);

        if (index != -1) {
            customerList.remove(index);
        }
    }

    private static void printPurchasesOfCustomer(Scanner sc) {

        int index = getCustomerIndex(sc);

        if (index != -1) {

            System.out.println("\nPrinting list of Purchases by " + customerList.get(index).getName());
            System.out.println("+------+-------------------+----------+");

            customerList.get(index).getAllPurchasesList().forEach((purchase) -> {
                System.out.print("|  " + purchase.getID());
                for (int i = 0; i < 4 - lengthOfNumber(purchase.getID()); i++) {
                    System.out.print(" ");
                }
                System.out.print("|    " + purchase.getProduct().getName());
                for (int i = 0; i < 15 - purchase.getProduct().getName().length(); i++) {
                    System.out.print(" ");
                }
                System.out.print("|  " + purchase.getQuantity());
                for (int i = 0; i < 8 - purchase.getQuantity(); i++) {
                    System.out.print(" ");
                }
                System.out.println("|");
            });
            System.out.println("+------+-------------------+----------+");

            for (Purchase purchase : customerList.get(index).getAllPurchasesList()) {

                System.out.println(
                        purchase.getID() + " " + purchase.getProduct().getName() + " " + purchase.getQuantity());
            }
        }
    }

    // Evidently the most important function. the function to create purchase.
    // Purchase will be automatically processed but therer should be a delivery
    // agent and shop in your zipcode.
    private static void createPurchase(Scanner sc) {

        int customerIndex = getCustomerIndex(sc);

        if (customerIndex != -1) {

            DeliveryAgent selectedDeliveryAgent = null;

            for (DeliveryAgent deliveryAgent : deliveryAgentList) {

                if (deliveryAgent.getZipCode() == customerList.get(customerIndex).getZipCode()) {

                    // agent with least deliveries is choosen
                    if (selectedDeliveryAgent == null
                            || deliveryAgent.getDeliveryCount() < selectedDeliveryAgent.getDeliveryCount())
                        selectedDeliveryAgent = deliveryAgent;
                }
            }

            if (selectedDeliveryAgent == null) {

                System.out.println("Sorry. No delivery agent in your zipcode.");
                return;
            }

            int productIndex = getProductIndex(sc);
            Product selectedProduct = productList.get(productIndex);

            Shop selectedShop = null;

            for (Shop shop : shopList) {

                if (shop.getQuantity(selectedProduct) > 0) {

                    // shop having the highest quantity of that product is choosen.
                    if (selectedShop == null
                            || shop.getQuantity(selectedProduct) > selectedShop.getQuantity(selectedProduct))
                        selectedShop = shop;
                }
            }

            if (selectedShop == null) {

                System.out.println("Sorry. No shop in your locality contains this medicine.");
                return;
            }

            int quantity = 0;

            try {

                System.out.println("Enter a positive quantity of " + selectedProduct.getName()
                        + ". Enter a non-positive to cancel order. Entering anything else selects max available quantity");
                System.out.println("Enter quantity of " + selectedProduct.getName() + " Max["
                        + selectedShop.getQuantity(selectedProduct) + "] : ");
                quantity = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                System.out.println("Invalid response. " + selectedShop.getQuantity(selectedProduct) + " "
                        + selectedProduct.getName() + " will be shipped");
            }

            if (quantity <= 0)
                return;

            if (quantity > selectedShop.getQuantity(selectedProduct)) {
                quantity = selectedShop.getQuantity(selectedProduct);
            }

            customerList.get(customerIndex).addPurchase(new Purchase(purchaseID++, customerList.get(customerIndex),
                    selectedProduct, quantity, selectedShop, selectedDeliveryAgent));
            selectedShop.decreaseQuantity(selectedProduct, quantity);
            selectedDeliveryAgent.addDeliveryCount(quantity);

            System.out.println(
                    "Purchase ID " + 0 + " : " + quantity + " " + selectedProduct.getName() + " delivered by Agent "
                            + selectedDeliveryAgent.getName() + " from Shop " + selectedShop.getName());
        }
    }

    // Panel containing customer specific options.
    private static void customerPanel(Scanner sc) {

        int choice = 0;
        System.out.println("\nWelcome to Customer panel");

        do {
            System.out.println("\nChoose from the below options.");
            System.out.println("1. Add a Customer");
            System.out.println("2. Delete a Customer");
            System.out.println("3. Print all Customers");
            System.out.println("4. Print all purchases of a customer");
            System.out.println("5. Create purchase for a customer");
            System.out.println("Enter a choice between 1-5. Enter 6 to go back to main menu.\n\n");

            try {

                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                choice = 0;
                System.out.println("Invalid choice.\n");
            }

            switch (choice) {

                case 1:
                    // Add a Customer.
                    addCustomer(sc);
                    break;

                case 2:
                    // Delete a Customer
                    deleteCustomer(sc);
                    break;

                case 3:
                    // Print all Customers.
                    printCustomers();
                    break;

                case 4:
                    // Print all purchases of a Customer.
                    printPurchasesOfCustomer(sc);
                    break;

                case 5:
                    // Create purchase.
                    createPurchase(sc);
                    break;

                default:
                    break;
            }
        } while (choice != 6);

        System.out.println("Exiting Customer panel.\n");
    }

    // helper function to find the number of digits. Required for designing the
    // output tables.
    public static int lengthOfNumber(int i) {
        if (i == 0)
            return 1;
        else
            return (int) Math.log10(i) + 1;
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        // Don't create any more scanner objects. Use this everywhere.
        Scanner sc = new Scanner(System.in);

        // Mian menu. This will then lead to individual panels.
        System.out.println("WELCOME TO MEDIKO SOLUTIONS.");
        System.out.println("Here is a list of roles you can assume. please choose one from the list below.\n\n\n");

        int choice = 0;

        do {

            System.out.println("1. Manufacturer");
            System.out.println("2. Product");
            System.out.println("3. Shop/Warehouse");
            System.out.println("4. Delivery Agent");
            System.out.println("5. Customer");
            System.out.println(
                    "Enter a choice from 1-5. Enter 6 to exit from the program. Enter anything else to repeat the above message.\n\n");

            try {

                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

                choice = 0;
                System.out.println("Invalid choice.\n");
            }

            switch (choice) {
                case 1:
                    manufacturerPanel(sc);
                    break;
                case 2:
                    productPanel(sc);
                    break;
                case 3:
                    shopPanel(sc);
                    break;
                case 4:
                    deliveryAgentPanel(sc);
                    break;
                case 5:
                    customerPanel(sc);
                    break;
                default:
                    break;
            }
        } while (choice != 6);

        System.out.println("\nHave a nice day. See you again.");
        sc.close();
    }
}
