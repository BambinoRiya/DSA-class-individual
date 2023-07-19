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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class PurchaseDialog {
    private static int quantity;
    private static int customerID;

    public static void show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextField quantityToPurchase = new TextField();
        quantityToPurchase.setPromptText("How many quantities?");
        quantityToPurchase.setMinHeight(30);

        ComboBox<String> customerName = new ComboBox<>();
        ComboBox<String> customerTelephone = new ComboBox<>();

        if (CustomerController.customers.size() >= Main2Controller.totalCustomers.size()) {
            for (Customer customer : CustomerController.customers) {
                customerName.getItems().add(customer.getCustomerName());
                customerTelephone.getItems().add(customer.getTelephone());
            }
        } else {
            for (Customer customer : Main2Controller.totalCustomers) {
                customerName.getItems().add(customer.getCustomerName());
                customerTelephone.getItems().add(customer.getTelephone());
            }
        }

        customerName.setMinHeight(30);
        customerName.setPromptText("Pick Customer's name");

        customerTelephone.setMinHeight(30);
        customerTelephone.setPromptText("Pick Customer's telephone");

        Button button = new Button("Make Purchase");
        button.setMinSize(150, 30);
        button.setOnAction(e -> {
            try {
                quantity = Integer.parseInt(quantityToPurchase.getText());
            } catch (NumberFormatException ne) {
                System.out.println("Error parsing quantity string to int: " + ne);

                Utility.showAlert(Alert.AlertType.ERROR, null, "Purchase Failure!", "Quantity is in the wrong format");
                return;
            }

            if (quantityToPurchase.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Purchase Failure!", "Please enter the purchased quantity");
                return;
            }

            if (customerName.getItems().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Purchase Failure!", "Customer Name not selected");
                return;
            }

            if (customerTelephone.getItems().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Purchase Failure!", "Customer Telephone not selected");
                return;
            }

            for (Customer customer : Main2Controller.totalCustomers) {
                if (customerName.getValue().equals(customer.getCustomerName())) {
                    if (customerTelephone.getValue().equals(customer.getTelephone())) {

                    } else {
                        Utility.showAlert(Alert.AlertType.ERROR, null, "Purchase Failure!", "The customer with the selected telephone number cannot be found. Add the customer if you haven't.");
                        return;
                    }

                    customerID = customer.getCustomerID();
                }
            }

            System.out.println("Customer ID is " + customerID);

            String date = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

            if (quantity > Utility.getQuantityInStockFromDatabase(ProductTile.id)) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Purchase Failure!", "Quantity in Stock not enough to complete purchase");
                return;
            } else {
                if (Utility.updateQuantityInStock(-quantity) && Utility.updateCustomerLastPurchaseDate(date, customerID) && Utility.updateCustomerTotalAmount(quantity, ProductTile.id, customerID)); {
                    Utility.informationDisplay("Purchased made", null, "Purchase in progress");
                }
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(quantityToPurchase, customerName, customerTelephone, button);

        Scene scene = new Scene(vBox, 350, 200);
        stage.setTitle("Make a purchase");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
