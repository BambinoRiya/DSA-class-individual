package com.propelquantum.stockmandesktop;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddSupplierDialog {
    private static String name;
    private static String location;
    private static String telephone;
    private static String productName;
    private static double productPrice;

    public static void show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextField supplierName = new TextField();
        supplierName.setPromptText("What is the name of the supplier");
        supplierName.setMinHeight(30);

        TextField supplierLocation = new TextField();
        supplierLocation.setPromptText("Where is the supplier located");
        supplierLocation.setMinHeight(30);

        TextField supplierTelephone = new TextField();
        supplierTelephone.setPromptText("What is the supplier's telephone number");
        supplierTelephone.setMinHeight(30);

        ComboBox<Product> products;

        if (Main2Controller.productsOnLaunch.size() > ProductController.products.size()) {
            products = new ComboBox<>(Main2Controller.productsOnLaunch);
        } else {
            products = new ComboBox<>(ProductController.products);
        }

        ComboBox<String> productNames = new ComboBox<>();

        for (Product product : products.getItems()) {
            productNames.getItems().add(product.getProductName());
        }

        products.setMinSize(200, 30);

        Button button = new Button("Add");
        button.setMinSize(150, 30);
        button.setOnAction(e -> {
            name = supplierName.getText();
            location = supplierLocation.getText();
            telephone = supplierTelephone.getText();

            if (supplierName.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Supplier Addition Error!", "Please enter a supplier's name");
                return;
            }

            if (supplierLocation.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Supplier Addition Error!", "Where is the supplier located?");
                return;
            }

            if (supplierTelephone.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Supplier Addition Error!", "What is the supplier's telephone number?");
                return;
            }

            System.out.println("The value of name is: " + name);
            System.out.println("The value of description is: " + location);
            System.out.println("The value of price is: " + telephone);

             for (Product product : products.getItems()) {
                 if (productNames.getValue().equals(product.getProductName())) {
                     productName = product.getProductName();
                     productPrice = product.getPricePerUnit();
                 }
             }

             System.out.println("The product name is: " + productName);
             System.out.println("The product price is: " + productPrice);

             Supplier supplier = new Supplier(name, location, productName, productPrice, telephone);

            if (Utility.insertSupplierIntoDatabase(supplier)) {
                Utility.informationDisplay("Supplier successfully added!", null, "New Supplier");
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(supplierName, supplierLocation, productNames , supplierTelephone, button);

        Scene scene = new Scene(vBox, 350, 300);
        stage.setTitle("Add a supplier");
        stage.setScene(scene);
        stage.showAndWait();

    }
}
