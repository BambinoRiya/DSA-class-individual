package com.propelquantum.stockmandesktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main2Controller implements Initializable {
    public BorderPane borderPane;
    public static ObservableList<Product> productsOnLaunch = FXCollections.observableArrayList();
    public static ObservableList<Customer> totalCustomers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
            borderPane.setCenter(fxml);
        } catch (IOException e) {
            Utility.showAlert(Alert.AlertType.ERROR, null, "Fatal Error!", "Contact application developer");
        }

        try(Connection connection = DriverManager.getConnection(Utility.DATABASE_URL, Utility.DATABASE_USERNAME, Utility.DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_PRODUCT_QUERY)) {

            while (resultSet.next()) {
                Product product = new Product();

                product.setProductID(resultSet.getInt("productID"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductDescription(resultSet.getString("productDescription"));
                product.setPricePerUnit(resultSet.getInt("pricePerUnit"));
                product.setQuantityInStock(resultSet.getInt("quantityInStock"));
                product.setTotalAmount();

                productsOnLaunch.add(product);
            }

        } catch (SQLException e) {
            Utility.printSQLException(e);
        }

        try(Connection connection = DriverManager.getConnection(Utility.DATABASE_URL, Utility.DATABASE_USERNAME, Utility.DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_CUSTOMER_QUERY)) {

            while (resultSet.next()) {
                Customer customer = new Customer();

                customer.setCustomerID(resultSet.getInt("customerID"));
                customer.setCustomerName(resultSet.getString("fullName"));
                customer.setLocationOrCompanyName(resultSet.getString("locationOrCompany"));
                customer.setTotalAmountPurchased(resultSet.getDouble("totalAmountPurchased"));
                customer.setLastPurchasedDate(resultSet.getString("lastPurchasedDate"));
                customer.setTelephone(resultSet.getString("telephone"));

                totalCustomers.add(customer);
            }

        } catch (SQLException e) {
            Utility.printSQLException(e);
        }
    }
    
    
    public void onDashboardButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
        borderPane.setCenter(fxml);
    }

    public void onInventoryButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("inventory.fxml")));
        borderPane.setCenter(fxml);
    }

    public void onProductsButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("products.fxml")));
        borderPane.setCenter(fxml);
    }

    public void onCustomersButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customer.fxml")));
        borderPane.setCenter(fxml);
    }

    public void onSuppliersButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("supplier.fxml")));
        borderPane.setCenter(fxml);
    }

    public void onExpenditureButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("expenditure.fxml")));
        borderPane.setCenter(fxml);
    }

    public void onSettingsButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settings.fxml")));
        borderPane.setCenter(fxml);
    }

    
}
