package com.chirag828049;

import java.util.Vector;

public class Customer extends Entity {

    private int ZipCode;
    private Vector<Purchase> purchaseList = new Vector<Purchase>();

    protected Customer(int ID, String name, int ZipCode) {
        super(ID, name);
        this.ZipCode = ZipCode;
    }

    protected int getZipCode() {

        return this.ZipCode;
    }

    protected void addPurchase(Purchase newPurchase) {

        purchaseList.add(newPurchase);
        System.out.println("Purchase ID: " + newPurchase.getID() + " ready to be shipped to " + this.getName());
    }

    protected Vector<Purchase> getAllPurchasesList() {

        return this.purchaseList;
    }
}
