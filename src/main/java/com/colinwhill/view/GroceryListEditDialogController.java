package com.colinwhill.view;

import com.colinwhill.model.GroceryList2;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by colinhill on 2/10/16.
 */
public class GroceryListEditDialogController{

    private GroceryList2 list;
    private Stage dialogStage;
    private boolean okClicked = false;

    @FXML
    private TextField listNameField;

    @FXML
    private void okButtonClicked(){
        list.setName(listNameField.getText());
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void cancelButtonClicked(){
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setList(GroceryList2 list) {
        this.list = list;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}
