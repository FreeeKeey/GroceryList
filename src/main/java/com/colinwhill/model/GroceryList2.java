package com.colinwhill.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by colinhill on 2/2/16.
 */
public class GroceryList2 extends Observable {
    private final StringProperty name;
    private final IntegerProperty listItemCount;

    public ObservableList<GroceryItem> currentGroceryList = FXCollections.observableArrayList();
    public ArrayList<Observer> observers = new ArrayList<Observer>();

    public GroceryList2(){
        this(null, null, 0);
    }


    public GroceryList2(String name, ObservableList itemList, int listSize) {
        this.name = new SimpleStringProperty(name);
        this.currentGroceryList = itemList;
        this.listItemCount = new SimpleIntegerProperty(listSize);
        observers = new ArrayList<Observer>();

    }

    public void setCurrentGroceryList(ObservableList<GroceryItem> currentGroceryList) {
        this.currentGroceryList = currentGroceryList;
    }

    @Override
    public void addObserver(Observer obs) {
        if(obs == null) throw new NullPointerException("Cannot add Observer with Null Value");
        if (!observers.contains(obs))
            observers.add(obs);

    }


    @Override
    public void notifyObservers(){
        for (int i = 0; i < countObservers() ; i++) {
            observers.get(i).update(this, observers);
        }
    }


    public void addItem(GroceryItem item){
        if (currentGroceryList.size() > 9){
            System.out.println("List is Full - Please create a new List");
        } else {
            currentGroceryList.add(item);
        }

        item.setStock(item.getStock() - item.getQty());
        notifyObservers();


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
        this.listItemCount.set(currentGroceryList.size());
    }

    public double getTotalCost(){
        double totalCost = 0;

        for (GroceryItem item: currentGroceryList) {
            totalCost += item.getUnitTotalPrice();
        }
        return totalCost;
    }
    public String toString() {
        System.out.println("List Name: "+name + "\n\n");

        for (GroceryItem item : currentGroceryList) {
            System.out.println(item);
        }
        System.out.printf("\n\nTotal Cost: \t\t\t\t\t $%.2f\n", getTotalCost());
        return "";
    }
}
