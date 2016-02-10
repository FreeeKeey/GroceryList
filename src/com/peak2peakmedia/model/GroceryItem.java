package com.peak2peakmedia.model;

/**
 * Created by colinhill on 2/1/16.
 */
public class GroceryItem {
    // Just an item
    private String itemName;
    private int qty;
    private double price;
    private int ID;

    public GroceryItem(String itemName) {
        setItemName(itemName);
        setQty(1);
        setPrice(0);
    }

    public GroceryItem(int ID, String itemName, int qty, double price){
        this.ID = ID;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost(){
        return qty * price;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t Qty: %d\t\t  Cost $%.2f", itemName, qty, getCost());
    }
}
