package com.peak2peakmedia.view;

import com.peak2peakmedia.FX_Main;
import com.peak2peakmedia.model.GroceryItem;
import com.peak2peakmedia.model.GroceryList2;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * Created by colinhill on 2/9/16.
 */
public class GroceryListOverviewController {

    private FX_Main main;

    @FXML
    private TableView<GroceryList2> listTable;

    @FXML
    private TableColumn<GroceryList2, String> listNameColumn;



    @FXML
    private TableColumn<GroceryList2, Integer> listCountColumn;

    //Item Table View
    @FXML
    private TableView<GroceryItem> itemTable;

    @FXML
    private TableColumn<GroceryItem, String> itemNameColumn;

    @FXML
    private TableColumn<GroceryItem, Integer> itemQtyColumn;

    @FXML
    private TableColumn<GroceryItem, Double> itemUnitPriceColumn;

    @FXML
    private TableColumn<GroceryItem, Double> itemTotalCostColumn;




    @FXML
    private Label listNameLabel;
    @FXML
    private Label itemCountLabel;
    @FXML
    private Label totalCostLabel;

    @FXML
    private void initialize(){
        listNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());


        //FIXME
        listCountColumn.setCellValueFactory(cellData -> cellData.getValue().listItemCountProperty().asObject());
        updateItemTable();

        listTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showListOverview(newValue)));
    }

    public void setMain(FX_Main main) {
        this.main = main;

        listTable.setItems(main.getGroceryListsData());
    }

    private void updateItemTable(){

        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemQtyColumn.setCellValueFactory(cellData -> cellData.getValue().qtyProperty().asObject());
        itemUnitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());
        itemTotalCostColumn.setCellValueFactory(cellData -> cellData.getValue().unitTotalPriceProperty().asObject());

    }
    private void showListOverview(GroceryList2 list){
        if (list != null){
            //Fill labels with info from the person object
            listNameLabel.setText(list.getName());
            itemCountLabel.setText(Integer.toString(list.itemOrders.size()));
            totalCostLabel.setText("$"+Double.toString(list.getTotalCost()));

            itemTable.setItems(main.getGroceryItems());

        } else {
            listNameLabel.setText("");
            itemCountLabel.setText("");
            itemNameColumn.setText("");
            itemQtyColumn.setText("");
            itemUnitPriceColumn.setText("");
            itemTotalCostColumn.setText("");

        }
    }
}
