package com.propelquantum.stockmandesktop;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AddExpenditureDialog {
    private static String expenses;
    private static String description;
    private static double amount;

    private static String date;

    public static void show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextField expensesTextField = new TextField();
        expensesTextField.setPromptText("Enter the general name of the expense");
        expensesTextField.setMinHeight(30);

        TextField expenseDescription = new TextField();
        expenseDescription.setPromptText("Describe the expense");
        expenseDescription.setMinHeight(30);

        TextField expenseAmount = new TextField();
        expenseAmount.setPromptText("What is the cost of the expense");
        expenseAmount.setMinHeight(30);

        Button button = new Button("Add");
        button.setMinSize(150, 30);
        button.setOnAction(e -> {
            expenses = expensesTextField.getText();
            description = expenseDescription.getText();
            date = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

            try {
                amount = Double.parseDouble(expenseAmount.getText());
            } catch (NumberFormatException ne) {
                System.out.println("Error parsing amount string to double: " + ne);

                Utility.showAlert(Alert.AlertType.ERROR, null, "Expense Addition Error!", "The amount is in the wrong format");
                return;
            }


            if (expensesTextField.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Expense Addition Error!", "Please enter an expense");
                return;
            }

            if (expenseDescription.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Expense Addition Error!", "Please describe the expense");
                return;
            }

            if (expenseAmount.getText().isEmpty()) {
                Utility.showAlert(Alert.AlertType.ERROR, null, "Expense Addition Error!", "What is the cost of the expense");
                return;
            }

            System.out.println("The value of name is: " + expenses);
            System.out.println("The value of description is: " + description);
            System.out.println("The value of price is: " + amount);

            Expenditure expenditure = new Expenditure(expenses, description, amount, date);
            expenditure.setCustomerID(Utility.loggedInUser.getUserID());

            if (Utility.insertExpenditureIntoDatabase(expenditure)) {
                Utility.informationDisplay("Expenses successfully added!", null, "New Expenditure");
            }

            stage.close();
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(expensesTextField, expenseDescription, expenseAmount, button);

        Scene scene = new Scene(vBox, 350, 200);
        stage.setTitle("Make an expense");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
