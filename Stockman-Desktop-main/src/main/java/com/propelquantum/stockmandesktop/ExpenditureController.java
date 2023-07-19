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

public class ExpenditureController implements Initializable {

    public TextField searchTextField;
    public TableView<Expenditure> tableView = new TableView<>();
    public TableColumn<Expenditure, Number> idTableColumn = new TableColumn<>();
    public TableColumn<Expenditure, String> expensesTableColumn = new TableColumn<>();
    public TableColumn<Expenditure, String> descriptionTableColumn = new TableColumn<>();
    public TableColumn<Expenditure, Number> amountTableColumn = new TableColumn<>();
    public TableColumn<Expenditure, String> dateOfExpenditureTableColumn = new TableColumn<>();

    public static ObservableList<Expenditure> expenditures = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        expenditures.clear();

        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().expenditureID());
        expensesTableColumn.setCellValueFactory(cellData -> cellData.getValue().expenses());
        descriptionTableColumn.setCellValueFactory(cellData -> cellData.getValue().description());
        amountTableColumn.setCellValueFactory(cellData -> cellData.getValue().amount());
        dateOfExpenditureTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateOfExpenditure());

        try(Connection connection = DriverManager.getConnection(Utility.DATABASE_URL, Utility.DATABASE_USERNAME, Utility.DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_EXPENDITURE_QUERY)) {

            while (resultSet.next()) {
                Expenditure expenditure = new Expenditure();

                expenditure.setExpenditureID(resultSet.getInt("expenditureID"));
                expenditure.setExpenses(resultSet.getString("expenses"));
                expenditure.setDescription(resultSet.getString("description"));
                expenditure.setAmount(resultSet.getDouble("amount"));
                expenditure.setDateOfExpenditure(resultSet.getString("dateOfExpenditure"));
                expenditure.setCustomerID(resultSet.getInt("customerID"));

                expenditures.add(expenditure);
            }

        } catch (SQLException e) {
            Utility.printSQLException(e);
        }

        tableView.getItems().setAll(expenditures);
    }

    public void onAddExpenseButtonClicked(ActionEvent actionEvent) {
        AddExpenditureDialog.show();
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String searchedExpenditureName = searchTextField.getText();

        if (searchTextField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, null, "Error", "Search bar is empty!");

            tableView.getItems().setAll(expenditures);

            return;
        }

        ObservableList<Expenditure> filteredExpenditure = FXCollections.observableArrayList();

        for (Expenditure expenditure : expenditures) {
            if (expenditure.getExpenses().equals(searchedExpenditureName)) {
                filteredExpenditure.add(expenditure);
            }
        }

        tableView.getItems().setAll(filteredExpenditure);
    }
}
