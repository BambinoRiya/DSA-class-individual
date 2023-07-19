package com.propelquantum.stockmandesktop;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public TextField usernameTextField;
    public PasswordField passwordField;
    public JFXButton loginButton;

    public void onLoginButtonClicked(ActionEvent actionEvent) {
        Window owner = loginButton.getScene().getWindow();

        boolean flag;

        // debug
        System.out.println(usernameTextField.getText());
        System.out.println(passwordField.getText());

        if (usernameTextField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, owner, "Login Error!", "Please enter your username");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            Utility.showAlert(Alert.AlertType.ERROR, owner, "Login Error!", "Please enter your password");
            return;
        }

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        // flag = Utility.validateLoginCredentials(username, password);

        // debug mode - database unavailable
        flag = true;

        if (!flag) {
            Utility.informationDisplay("Please enter a correct username and a password", null, "Login failed");
        } else {

            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main2.fxml")));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch(IOException e) {

                // debug
                System.out.println("Error switching scenes: " + e.getMessage());

                Utility.showAlert(Alert.AlertType.ERROR, owner, "Error opening application", "Please contact application developer");
            }
        }
    }
}
