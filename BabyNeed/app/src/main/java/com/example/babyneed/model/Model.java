package com.example.babyneed.model;

public class Model {
    private int ID;
    private int size;
    private int quantity;
    private String p_name;
    private String color;

    public Model() {
    }

    @Override
    public String toString() {
        return "Model{" +
                "ID=" + ID +
                ", size=" + size +
                ", quantity=" + quantity +
                ", p_name='" + p_name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public Model(int size, int quantity, String p_name, String color) {
        this.size = size;
        this.quantity = quantity;
        this.p_name = p_name;
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
