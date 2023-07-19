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

public class ProductController implements Initializable {
    public TextField searchTextField;

    public TableView<Product> tableView = new TableView<>();
    public TableColumn<Product, Number> idTableColumn = new TableColumn<>();
    public TableColumn<Product, String> productNameTableColumn = new TableColumn<>();
    public TableColumn<Product, String> productDescriptionTableColumn = new TableColumn<>();
    public TableColumn<Product, Number> pricePerUnitTableColumn = new TableColumn<>();
    public TableColumn<Product, Number> quantityInStockTableColumn = new TableColumn<>();
    public TableColumn<Product, Number> totalAmountTableColumn = new TableColumn<>();

    public static ObservableList<Product> products = FXCollections.observableArrayList(Main2Controller.productsOnLaunch);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        products.clear();

        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().productID());
        productNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().productName());
        productDescriptionTableColumn.setCellValueFactory(cellData -> cellData.getValue().productDescription());
        pricePerUnitTableColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerUnit());
        quantityInStockTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityInStock());
        totalAmountTableColumn.setCellValueFactory(cellData -> cellData.getValue().totalAmount());

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

                products.add(product);
            }

        } catch (SQLException e) {
            Utility.printSQLException(e);
        }

        tableView.getItems().setAll(products);

    }

    public void onAddProductButtonClicked(ActionEvent actionEvent) {
        AddProductDialog.show();
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String searchedProductName = searchTextField.getText();

        if (searchTextField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, null, "Error", "Search bar is empty!");

            tableView.getItems().setAll(products);

            return;
        }

        ObservableList<Product> filteredProduct = FXCollections.observableArrayList();

        for (Product product : products) {
            if (product.getProductName().equals(searchedProductName)) {
                filteredProduct.add(product);
            }
        }

        tableView.getItems().setAll(filteredProduct);
    }
}
