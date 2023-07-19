package com.propelquantum.stockmandesktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    public TextField searchTextField;
    public TableView<Customer> tableView = new TableView<>();
    public TableColumn<Customer, Number> idTableColumn = new TableColumn<>();
    public TableColumn<Customer, String> fullNameTableColumn = new TableColumn<>();
    public TableColumn<Customer, String> companyOrLocationTableColumn = new TableColumn<>();
    public TableColumn<Customer, Number> totalAmountPurchasedTableColumn = new TableColumn<>();
    public TableColumn<Customer, String> lastPurchasedDateTableColumn = new TableColumn<>();
    public TableColumn<Customer, String> telephoneTableColumn = new TableColumn<>();

    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        System.out.println("Total customers before: " + customers.size());
        customers.clear();

        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().customerID());
        fullNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().customerName());
        companyOrLocationTableColumn.setCellValueFactory(cellData -> cellData.getValue().locationOrCompanyName());
        totalAmountPurchasedTableColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmountPurchased());
        lastPurchasedDateTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastPurchasedDate());
        telephoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().telephone());

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

                customers.add(customer);
            }

        } catch (SQLException e) {
            Utility.printSQLException(e);
        }

        tableView.getItems().setAll(customers);
    }

    public void onAddCustomerButtonClicked(ActionEvent actionEvent) {
        AddCustomerDialog.show();
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String searchedCustomerName = searchTextField.getText();

        if (searchTextField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, null, "Error", "Search bar is empty!");

            tableView.getItems().setAll(customers);

            return;
        }

        ObservableList<Customer> filteredCustomer = FXCollections.observableArrayList();

        for (Customer customer : customers) {
            if (customer.getCustomerName().equals(searchedCustomerName)) {
                filteredCustomer.add(customer);
            }
        }

        tableView.getItems().setAll(filteredCustomer);
    }


}
