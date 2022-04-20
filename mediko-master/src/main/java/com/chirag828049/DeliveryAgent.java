package com.chirag828049;

public class DeliveryAgent extends Entity {

    private int ZipCode;
    private int deliveryCount;

    protected DeliveryAgent(int ID, String name, int ZipCode) {

        super(ID, name);
        this.ZipCode = ZipCode;
        this.deliveryCount = 0;
    }

    protected int getZipCode() {

        return this.ZipCode;
    }

    protected int getDeliveryCount() {

        return this.deliveryCount;
    }

    protected void addDeliveryCount(int newDeliveries) {

        this.deliveryCount += newDeliveries;
    }
}
