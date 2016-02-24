package com.peak2peakmedia.view;

import com.peak2peakmedia.FX_Main;
import com.peak2peakmedia.model.GroceryItem;
import com.peak2peakmedia.model.GroceryList2;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;

/**
 * Created by colinhill on 2/9/16.
 */
public class GroceryListOverviewController implements Observer{

    private FX_Main main;
    GroceryList2 currentList = new GroceryList2();



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
        listCountColumn.setCellValueFactory(cellData -> cellData.getValue().listItemCountProperty().asObject());
        listTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showListOverview(newValue)));

        itemTable.setEditable(true);
        

        updateItemTable();



    }

    // Buttons for List

    @FXML
    private void newListButton(){
        GroceryList2 tempItem = new GroceryList2();
        boolean okClicked = main.showGroceryListEditDialog(tempItem);
        if (okClicked)
            main.getGroceryListsData().add(tempItem);
        newItemButton();
    }

    @FXML
    private void editListButton(){
        GroceryList2 selectedList = listTable.getSelectionModel().getSelectedItem();

        if (selectedList != null){

        }
    }

    @FXML
    private void deleteListButton(){
        int selectedIndex = listTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >=0){
            listTable.getItems().remove(selectedIndex);
        } else {
            //Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No List Selected");
            alert.setContentText("Please select a list before trying to delete");
            alert.showAndWait();
        }

    }

    // Buttons for Item
    @FXML
    private void newItemButton(){
        int selectedIndex = listTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >=0) {
            listTable.getItems();
            GroceryItem tempItem = new GroceryItem();
            boolean okClicked = main.showItemEditDialog(tempItem);
            if (okClicked)
                main.getGroceryItems().add(tempItem);
        }

    }

    @FXML
    private void editItemButton(){

    }

    @FXML
    private void deleteItemButton(){
        int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >=0){
            itemTable.getItems().remove(selectedIndex);
        } else {
            //Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No List Selected");
            alert.setContentText("Please select a list before trying to delete");
            alert.showAndWait();
        }

    }

    public void setMain(FX_Main main) {
        this.main = main;

        listTable.setItems(main.getGroceryListsData());
    }

    private void updateItemTable(){



        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemQtyColumn.setCellValueFactory(cellData -> cellData.getValue().qtyProperty().asObject());
        itemQtyColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<GroceryItem, Integer>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<GroceryItem, Integer> t) {
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQty(t.getNewValue());
                    }
                }
        );

        itemUnitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());
        itemTotalCostColumn.setCellValueFactory(cellData -> cellData.getValue().unitTotalPriceProperty().asObject());
        itemTable.getItems();


    }
    private void showListOverview(GroceryList2 list){
        if (list != null){
            //Fill labels with info from the person object
            listNameLabel.setText(list.getName());
            itemCountLabel.setText(Integer.toString(list.currentGroceryList.size()));
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

    @Override
    public void update(Observable o, Object arg) {
        if (currentList.currentGroceryList != null){
            //Fill labels with info from the list object
            listNameLabel.setText(currentList.getName());
            itemCountLabel.setText(Integer.toString(currentList.currentGroceryList.size()));
            totalCostLabel.setText("$"+Double.toString(currentList.getTotalCost()));

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
