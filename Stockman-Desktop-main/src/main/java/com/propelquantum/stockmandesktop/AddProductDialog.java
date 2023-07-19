package com.propelquantum.stockmandesktop;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddProductDialog {
    private static String name;
    private static String description;
    private static double price;

    private static final int defaultQuantity = 0;

    public static void show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextField productName = new TextField();
        productName.setPromptText("Enter the name of the product");
        productName.setMinHeight(30);

        TextField productDescription = new TextField();
        productDescription.setPromptText("Describe the product");
        productDescription.setMinHeight(30);

        TextField productPrice = new TextField();
        productPrice.setPromptText("How much is the product");
        productPrice.setMinHeight(30);

        Button button = new Button("Add");
        button.setMinSize(150, 30);
        button.setOnAction(e -> {
            name = productName.getText();
            description = productDescription.getText();

            try {
                price = Double.parseDouble(productPrice.getText());
            } catch (NumberFormatException ne) {
                System.out.println("Error parsing price string to double: " + ne);

                Utility.showAlert(Alert.AlertType.ERROR, null, "Product Addition Error!", "The price is in the wrong format");
                return;
            }


            if (productName.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Product Addition Error!", "Please enter a product's name");
                return;
            }

            if (productDescription.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Product Addition Error!", "Please describe the product");
                return;
            }

            if (productPrice.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Product Addition Error!", "Please price the product");
                return;
            }

            System.out.println("The value of name is: " + name);
            System.out.println("The value of description is: " + description);
            System.out.println("The value of price is: " + price);

            Product product = new Product(name, description, price, defaultQuantity);

            if (Utility.insertProductIntoDatabase(product)) {
                Utility.informationDisplay("Product successfully added!", null, "New product Added");
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(productName, productDescription, productPrice, button);

        Scene scene = new Scene(vBox, 350, 200);
        stage.setTitle("Add a product");
        stage.setScene(scene);
        stage.showAndWait();

    }
}
