package com.chirag828049;

import java.util.Vector;

import org.javatuples.Pair;

public class Shop extends Entity {

    private int ZipCode;

    private Vector<Pair<Product, Integer>> productList = new Vector<Pair<Product, Integer>>();

    protected Shop(int ID, String name, int ZipCode) {

        super(ID, name);
        this.ZipCode = ZipCode;
    }

    protected int getZipCode() {

        return this.ZipCode;
    }

    protected void addProduct(Product newProduct, Integer quantity) {

        boolean isPresent[] = new boolean[1]; // using array because lambda expression inside foreach doesn't allow
                                              // changing value of local variable.
        isPresent[0] = false;

        productList.forEach((product) -> {
            if (product.getValue0() == newProduct) {
                product.setAt1(product.getValue1() + quantity);
                isPresent[0] = true;
            }
        });

        if (!isPresent[0]) {
            productList.add(new Pair<Product, Integer>(newProduct, quantity));
        }
    }

    protected void deleteProduct(Product existingProduct) {

        boolean isFound = false;
        int index = -1;

        for (Pair<Product, Integer> temp : productList) {

            index++;
            if (temp.getValue0().getID() == existingProduct.getID()) {
                isFound = true;
                break;
            }
        }

        if (isFound) {
            productList.remove(index);
        }
    }

    protected int getQuantity(Product existingProduct) {

        int quantity[] = { 0 };

        productList.forEach((product) -> {
            if (product.getValue0() == existingProduct) {
                quantity[0] = product.getValue1().intValue();
            }
        });
        return quantity[0];
    }

    protected void decreaseQuantity(Product existingProduct, Integer decrease) {

        productList.forEach((product) -> {
            if (product.getValue0() == existingProduct) {
                product.setAt1(product.getValue1() - decrease);
            }
        });
    }

    protected Vector<Pair<Product, Integer>> getAllProducts() {

        return productList;
    }

    protected void printAllProducts() {

        System.out.println("\nPrinting all the products of " + this.getName());
        System.out.println("+------+-------------------+----------+");

        productList.forEach((product) -> {
            System.out.print("|  " + product.getValue0().getID());
            for (int i = 0; i < 4 - lengthOfNumber(product.getValue0().getID()); i++) {
                System.out.print(" ");
            }
            System.out.print("|    " + product.getValue0().getName());
            for (int i = 0; i < 15 - product.getValue0().getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|  " + product.getValue1());
            for (int i = 0; i < 8 - lengthOfNumber(product.getValue1()); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        });
        System.out.println("+------+-------------------+----------+");
        for (Pair<Product, Integer> product : productList) {

            System.out.println(
                    product.getValue0().getID() + " " + product.getValue0().getName() + " " + product.getValue1());
        }
    }

    private int lengthOfNumber(int i) {
        if (i == 0)
            return 1;
        else
            return (int) Math.log10(i) + 1;
    }
}
