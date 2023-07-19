package com.propelquantum.stockmandesktop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    User() {
        userID = new SimpleIntegerProperty();
        fullName = new SimpleStringProperty();
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    User(String name, String username, String password) {
        userID = new SimpleIntegerProperty();
        fullName = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    User(int id, String name, String username, String password) {
        userID = new SimpleIntegerProperty(id);
        fullName = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public void setUserID(final int id) {
        userID.set(id);
    }

    public void setFullName(final String name) {
        fullName.set(name);
    }

    public void setUsername(final String username) {
        this.username.set(username);
    }

    public void setPassword(final String password) {
        this.password.set(password);
    }

    public int getUserID() {
        return userID.get();
    }

    public String getFullName() {
        return fullName.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public IntegerProperty userID() {
        return userID;
    }

    public StringProperty fullName() {
        return fullName;
    }

    public StringProperty username() {
        return username;
    }

    public StringProperty password() {
        return password;
    }
}
