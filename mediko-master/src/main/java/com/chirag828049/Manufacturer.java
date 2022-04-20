package com.chirag828049;

import java.util.Vector;

public class Manufacturer extends Entity {

    private Vector<Product> productList = new Vector<Product>();

    protected Manufacturer(int ID, String name) {
        super(ID, name);
    }

    protected void addProduct(Product newProduct) {

        if (productList.contains(newProduct)) {
            System.out.println(this.getName() + " already manufactured it. Duplication not allowed.");
        } else {
            productList.add(newProduct);
            System.out.println(this.getName() + " now manufactures " + newProduct.getName());
        }
    }

    protected void deleteProduct(Product existingProduct) {

        if (productList.contains(existingProduct)) {
            productList.remove(existingProduct);
            System.out.println(existingProduct.getName() + " removed from " + this.getName() + " manufacture list");
        } else {
            System.out.println(this.getName() + " does not manufacture " + existingProduct.getName());
        }
    }

    protected Vector<Product> getAllProductsList() {

        return productList;
    }

    protected void deleteManufacturer() {

        // to be invoked when this manufacturer object is being deleted
        for (Product product : productList) {
            product.deleteManufacturer();
        }
    }
}
