package com.colinwhill.view;

import com.colinwhill.model.GroceryItem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by colinhill on 2/9/16.
 */
public class ItemEditDialogController {

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemQtyField;

    @FXML
    private TextField itemPriceField;

    @FXML
    private Label totalCostLabel;

    private Stage dialogStage;
    private GroceryItem item;
    private boolean okClicked = false;

    @FXML
    private void initialize(){

    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setItem(GroceryItem item){
        this.item = item;

        itemNameField.setText(item.getItemName());
        itemQtyField.setText(Integer.toString(item.getQty()));
        itemPriceField.setText(Double.toString(item.getUnitPrice()));
        totalCostLabel.setText(Double.toString(item.getUnitTotalPrice()));

     }

    public boolean isOkClicked(){return okClicked;}

    @FXML
    private void okButtonClicked(){
        if (isInputValid()){
            item.setItemName(itemNameField.getText());
            item.setQty(Integer.parseInt(itemQtyField.getText()));
            item.setUnitPrice(Double.parseDouble(itemPriceField.getText()));
            item.setUnitTotalPrice(item.getQty()*item.getUnitPrice());


            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void cancelButtonClicked(){dialogStage.close();}

    private boolean isInputValid() {
        String errorMessage = "";

        if (itemNameField.getText() == null || itemNameField.getText().length() == 0) {
            errorMessage += "Not a valid item name!\n";
        }
        if (itemQtyField.getText() == null || itemQtyField.getText().length() == 0) {
            errorMessage += "Not a valid quantity!\n";
        }
        if (itemPriceField.getText() == null || itemPriceField.getText().length() == 0) {
            errorMessage += "Not a valid price!\n";
        }

        if (itemQtyField.getText() == null || itemQtyField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(itemQtyField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Not a valid quantity (must be an integer)!\n";
            }
        }

        if (itemPriceField.getText() == null || itemPriceField.getText().length() == 0) {
            errorMessage += "Not a valid price!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(itemPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Not a valid Price (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
