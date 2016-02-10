package com.peak2peakmedia.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by colinhill on 2/2/16.
 */
public class GroceryList2 {
    private final StringProperty name;
    private final IntegerProperty listItemCount;

    public ObservableList<GroceryItem> itemOrders = FXCollections.observableArrayList();

    public GroceryList2(){
        this(null,null, 0);
    }

//    public GroceryList2(String name){
//        this.name = new SimpleStringProperty(name);
//    }

    public GroceryList2(String name, ObservableList<GroceryItem> itemOrders, int count) {
        this.name = new SimpleStringProperty(name);
        this.listItemCount = new SimpleIntegerProperty(count);
        this.itemOrders = itemOrders;

    }

    public void addItem(GroceryItem item){
        if (itemOrders.size() > 9){
            System.out.println("List is Full - Please create a new List");
        } else {
            itemOrders.add(item);
        }
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getListItemCount() {
        return listItemCount.get();
    }

    public IntegerProperty listItemCountProperty() {
        return listItemCount;
    }

    public void setListItemCount() {
        this.listItemCount.set(itemOrders.size());
    }

    public double getTotalCost(){
        double totalCost = 0;

        for (GroceryItem item: itemOrders) {
            totalCost += item.getUnitTotalPrice();
        }
        return totalCost;
    }
    public String toString() {
        System.out.println("List Name: "+name + "\n\n");

        for (GroceryItem item : itemOrders) {
            System.out.println(item);
        }
        System.out.printf("\n\nTotal Cost: \t\t\t\t\t $%.2f\n", getTotalCost());
        return "";
    }
}
