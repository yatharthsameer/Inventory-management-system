package com.chirag828049;

public abstract class Entity {

    private int ID;
    private String name;

    protected Entity(int ID, String name) {

        this.ID = ID;
        this.name = name;
    }

    protected int getID() {
        return this.ID;
    }

    protected String getName() {
        return this.name;
    }
}
