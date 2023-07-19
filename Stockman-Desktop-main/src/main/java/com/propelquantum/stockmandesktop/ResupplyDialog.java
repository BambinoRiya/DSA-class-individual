package com.propelquantum.stockmandesktop;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResupplyDialog {
    private static int quantity;

    public static void show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextField quantityInStock = new TextField();
        quantityInStock.setPromptText("How many quantities?");
        quantityInStock.setMinHeight(30);

        Button button = new Button("Supply");
        button.setMinSize(150, 30);
        button.setOnAction(e -> {
            try {
                quantity = Integer.parseInt(quantityInStock.getText());
            } catch (NumberFormatException ne) {
                System.out.println("Error parsing quantity string to int: " + ne);

                Utility.showAlert(Alert.AlertType.ERROR, null, "Resupply Failure!", "Quantity is in the wrong format");
                return;
            }


            if (quantityInStock.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Resupply Failure!", "Please enter the supplied quantity");
                return;
            }

            if (Utility.updateQuantityInStock(quantity)) {
                Utility.informationDisplay("Resupply successful!", null, "Resupplied");
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(quantityInStock, button);

        Scene scene = new Scene(vBox, 350, 100);
        stage.setTitle("Resupply");
        stage.setScene(scene);
        stage.showAndWait();
    }
}

