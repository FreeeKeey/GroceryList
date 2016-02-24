package com.peak2peakmedia;

import com.peak2peakmedia.model.GroceryItem;
import com.peak2peakmedia.model.GroceryList2;
import com.peak2peakmedia.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by colinhill on 2/9/16.
 */
public class FX_Main extends Application {

    private BorderPane root;
    private Stage primaryStage;

    private ObservableList<GroceryList2> groceryListsData = FXCollections.observableArrayList();

    private ObservableList<GroceryItem> groceryItems = FXCollections.observableArrayList();


    public ObservableList<GroceryList2> getGroceryListsData(){return groceryListsData;}
    public ObservableList<GroceryItem> getGroceryItems(){return groceryItems;}

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args){launch(args);}

    public FX_Main(){
        GroceryList2 list = new GroceryList2("Listy", groceryItems);



        Supermarket market = new Supermarket(list);

        groceryItems.add(new GroceryItem("Tomatoes", 3, 1.99, 27));
        groceryItems.add(new GroceryItem("Pineapple", 2, 4.99, 12));
        groceryItems.add(new GroceryItem("Apples", 4, 2.99, 4));

        groceryListsData.add(new GroceryList2("MyList", groceryItems));
    }

    @Override
    public  void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Listy");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FX_Main.class.getResource("view/RootLayout.fxml"));

        root = (BorderPane) loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);



        primaryStage.show();

        showListOverview();
    }

    public void showListOverview() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FX_Main.class.getResource("view/GroceryListOverview.fxml"));

            AnchorPane listOverview = (AnchorPane) loader.load();

            root.setCenter(listOverview);

            GroceryListOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e){
            e.printStackTrace();

        }
    }

    public boolean showItemEditDialog(GroceryItem tempItem) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FX_Main.class.getResource("view/ItemEditDialog.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            //Create Dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ItemEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(tempItem);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean showGroceryListEditDialog(GroceryList2 tempItem) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FX_Main.class.getResource("view/GroceryListEditDialog.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            //Create Dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            GroceryListEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setList(tempItem);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
