package com.example.contactrecview.model;

public class Contact {
    private int ID;
    private String Name;

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                '}';
    }

    public Contact(String name, String numer) {
        Name = name;
        Number = numer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumer() {
        return Number;
    }

    public void setNumer(String numer) {
        Number = numer;
    }

    private String Number;
}


