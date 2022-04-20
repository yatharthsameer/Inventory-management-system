package com.chirag828049;

public class Purchase {

    private int ID;
    private Customer customer;
    private Product product;
    private int quantity;
    private Shop shop;
    private DeliveryAgent deliveryAgent;

    protected Purchase(int ID, Customer customer, Product product, int quantity, Shop shop,
            DeliveryAgent deliveryAgent) {

        this.ID = ID;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.shop = shop;
        this.deliveryAgent = deliveryAgent;
    }

    protected int getID() {

        return this.ID;
    }

    protected Customer getCustomer() {

        return this.customer;
    }

    protected Product getProduct() {

        return this.product;
    }

    protected int getQuantity() {

        return this.quantity;
    }

    protected Shop getShop() {

        return this.shop;
    }

    protected DeliveryAgent getDeliveryAgent() {

        return this.deliveryAgent;
    }
}
