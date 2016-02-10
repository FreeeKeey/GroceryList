package com.peak2peakmedia;

import com.peak2peakmedia.view.ItemEditDialogController;
import com.peak2peakmedia.view.RootLayoutController;
import javafx.application.Application;
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

    public static void main(String[] args){launch(args);}

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

    private boolean showListOverview() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FX_Main.class.getResource("view/ItemEditDialog.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            //Create Dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(root);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ItemEditDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPerson(p);

            dialogStage.showAndWait();

            return true;


//            return controller.isOKClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
