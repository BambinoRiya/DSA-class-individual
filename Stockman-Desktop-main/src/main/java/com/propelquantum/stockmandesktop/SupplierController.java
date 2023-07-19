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

public class SupplierController implements Initializable {
    public TextField searchTextField;

    public TableView<Supplier> tableView = new TableView<>();
    public TableColumn<Supplier, Number> idTableColumn = new TableColumn<>();
    public TableColumn<Supplier, String> supplierNameTableColumn = new TableColumn<>();
    public TableColumn<Supplier, String> locationTableColumn = new TableColumn<>();
    public TableColumn<Supplier, String> productSuppliedTableColumn = new TableColumn<>();
    public TableColumn<Supplier, Number> pricePerUnitTableColumn = new TableColumn<>();
    public TableColumn<Supplier, String> telephoneTableColumn = new TableColumn<>();

    public static ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        suppliers.clear();

        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().supplierID());
        supplierNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().supplierName());
        locationTableColumn.setCellValueFactory(cellData -> cellData.getValue().supplierLocation());
        productSuppliedTableColumn.setCellValueFactory(cellData -> cellData.getValue().productsSupplied());
        pricePerUnitTableColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerUnit());
        telephoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().telephone());

        try(Connection connection = DriverManager.getConnection(Utility.DATABASE_URL, Utility.DATABASE_USERNAME, Utility.DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_SUPPLIER_QUERY)) {

            while (resultSet.next()) {
                Supplier supplier = new Supplier();

                supplier.setSupplierID(resultSet.getInt("supplierID"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                supplier.setSupplierLocation(resultSet.getString("location"));
                supplier.setProductsSupplied(resultSet.getString("productsSupplied"));
                supplier.setPricePerUnit(resultSet.getInt("pricePerUnit"));
                supplier.setTelephone(resultSet.getString("telephone"));

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            Utility.printSQLException(e);
        }

        tableView.getItems().setAll(suppliers);

    }

    public void onAddSupplierButtonClicked(ActionEvent actionEvent) {
        AddSupplierDialog.show();
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String searchedSupplierName = searchTextField.getText();

        if (searchTextField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, null, "Error", "Search bar is empty!");

            tableView.getItems().setAll(suppliers);

            return;
        }

        ObservableList<Supplier> filteredSupplier = FXCollections.observableArrayList();

        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierName().equals(searchedSupplierName)) {
                filteredSupplier.add(supplier);
            }
        }

        tableView.getItems().setAll(filteredSupplier);
    }
}
