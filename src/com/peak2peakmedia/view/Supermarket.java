package com.peak2peakmedia.view;

import com.peak2peakmedia.model.GroceryList2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by colinhill on 2/23/16.
 */
public class Supermarket implements Observer {


    public Supermarket(Observable o) {
        o.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < ((GroceryList2)o).currentGroceryList.size(); i++) {
            System.out.printf("The Super market has %d of %s in stock", ((GroceryList2)o).currentGroceryList.get(i).getStock(), ((GroceryList2)o).currentGroceryList.get(i).getItemName());
        }
    }
}
