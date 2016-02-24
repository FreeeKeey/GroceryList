package com.peak2peakmedia.view;

import com.peak2peakmedia.model.GroceryList2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by colinhill on 2/23/16.
 */
public class Supermarket implements Observer {

    private GroceryList2 list = null;

    public Supermarket(Observable o, GroceryList2 list) {
        o.addObserver(this);
        this.list = list;
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < list.itemOrders.size(); i++) {
            System.out.printf("The Super market has %d of %s in stock", list.itemOrders.get(i).getQty(), list.itemOrders.get(i).getItemName());
        }
    }
}
