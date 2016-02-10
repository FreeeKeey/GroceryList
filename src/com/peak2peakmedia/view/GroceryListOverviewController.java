package com.peak2peakmedia.view;

import com.peak2peakmedia.model.GroceryList2;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by colinhill on 2/9/16.
 */
public class GroceryListOverviewController {
    @FXML
    private TableView<GroceryList2> list;

    @FXML
    private TableColumn<GroceryList2, String> listName;

    @FXML
    private TableColumn<GroceryList2, Integer> listCount;


}
