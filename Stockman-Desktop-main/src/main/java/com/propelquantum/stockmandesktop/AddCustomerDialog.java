package com.propelquantum.stockmandesktop;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCustomerDialog {
    private static String name;
    private static String location;
    private static String tel;

    public static void show() {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextField customerFullName = new TextField();
        customerFullName.setPromptText("Enter customer's full name");
        customerFullName.setMinHeight(30);

        TextField locationOrCompany = new TextField();
        locationOrCompany.setPromptText("Enter the location or company of the customer");
        locationOrCompany.setMinHeight(30);

        TextField telephone = new TextField();
        telephone.setPromptText("Enter customer's telephone number");
        telephone.setMinHeight(30);

        Button button = new Button("Add");
        button.setMinSize(150, 30);
        button.setOnAction(e -> {
            name = customerFullName.getText();
            location = locationOrCompany.getText();
            tel = telephone.getText();

            if (customerFullName.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Customer Addition Error!", "Please enter a customer's full name");
                return;
            }

            if (locationOrCompany.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Customer Addition Error!", "Please enter the location or company of the customer");
                return;
            }

            if (telephone.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Customer Addition Error!", "Please enter the customer's telephone");
                return;
            }

            System.out.println("The value of name is: " + name);
            System.out.println("The value of location is: " + location);
            System.out.println("The value of telephone is: " + tel);

            Customer customer = new Customer(name, location, tel);
            customer.setLastPurchasedDate("not given");

            System.out.println(customer.getLastPurchasedDate());

            if (Utility.insertCustomerIntoDatabase(customer)) {
                Utility.informationDisplay("Customer successfully added!", null, "New Customer Added");
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(customerFullName, locationOrCompany, telephone, button);

        Scene scene = new Scene(vBox, 350, 200);
        stage.setTitle("Add a customer");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
