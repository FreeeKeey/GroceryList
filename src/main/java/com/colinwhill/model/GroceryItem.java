package com.colinwhill.model;

import javafx.beans.property.*;

/**
 * Created by colinhill on 2/1/16.
 */
public class GroceryItem {
    // Just an item
    private final StringProperty itemName;
    private final IntegerProperty qty;
    private final DoubleProperty unitPrice;
    private final DoubleProperty unitTotalPrice;
    private final IntegerProperty stock;
//    private int ID;


    public GroceryItem() {
        this(null,0, 0, 0);
    }

    public GroceryItem(String itemName, int qty, double price, int stock){
        this.itemName = new SimpleStringProperty(itemName);
        this.qty = new SimpleIntegerProperty(qty);
        this.unitPrice = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        unitTotalPrice = new SimpleDoubleProperty(getQty() * getUnitPrice());
    }

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public int getQty() {
        return qty.get();
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public double getUnitTotalPrice() {
        return unitTotalPrice.get();
    }

    public DoubleProperty unitTotalPriceProperty() {
        return unitTotalPrice;
    }

    public void setUnitTotalPrice(double unitTotalPrice) {
        this.unitTotalPrice.set(unitTotalPrice);
    }

    public int getStock() {
        return stock.get();
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    @Override
    public String toString() {
        return String.format("%s\t\t Qty: %d\t\t  Cost $%.2f \t\t Inventory: %d", itemName, getQty(), getUnitTotalPrice(), getStock());
    }
}
