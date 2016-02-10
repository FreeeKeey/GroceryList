package com.peak2peakmedia.model;

import com.peak2peakmedia.model.GroceryItem;

import java.util.ArrayList;

/**
 * Created by colinhill on 2/2/16.
 */
public class GroceryList2 {
    private String name;
    public ArrayList<GroceryItem> itemOrders = new ArrayList<GroceryItem>();

    public GroceryList2(){}

    public GroceryList2(String name, ArrayList<GroceryItem> itemOrders) {
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalCost(){
        double totalCost = 0;

        for (GroceryItem item: itemOrders) {
            totalCost += item.getCost();
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
