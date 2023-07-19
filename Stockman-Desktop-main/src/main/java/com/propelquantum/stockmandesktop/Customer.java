package com.propelquantum.stockmandesktop;

import javafx.beans.property.*;

public class Customer {
    private final IntegerProperty customerID;
    private final StringProperty customerName;
    private final StringProperty locationOrCompanyName;
    private final DoubleProperty totalAmountPurchased;
    private final StringProperty lastPurchasedDate;
    private final StringProperty telephone;

    Customer() {
        customerID = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty();
        locationOrCompanyName = new SimpleStringProperty();
        totalAmountPurchased = new SimpleDoubleProperty();
        lastPurchasedDate = new SimpleStringProperty();
        telephone = new SimpleStringProperty();
    }

    Customer(String name, String locationOrCompany, String tel) {
        customerID = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty(name);
        locationOrCompanyName = new SimpleStringProperty(locationOrCompany);
        totalAmountPurchased = new SimpleDoubleProperty();
        lastPurchasedDate = new SimpleStringProperty();
        telephone = new SimpleStringProperty(tel);
    }

    Customer(int id, String name, String locationOrCompany, double amount, String date, String tel) {
        customerID = new SimpleIntegerProperty(id);
        customerName = new SimpleStringProperty(name);
        locationOrCompanyName = new SimpleStringProperty(locationOrCompany);
        totalAmountPurchased = new SimpleDoubleProperty(amount);
        lastPurchasedDate = new SimpleStringProperty(date);
        telephone = new SimpleStringProperty(tel);
    }

    Customer(String name, String locationOrCompany, double amount, String date, String tel) {
        customerID = new SimpleIntegerProperty();
        customerName = new SimpleStringProperty(name);
        locationOrCompanyName = new SimpleStringProperty(locationOrCompany);
        totalAmountPurchased = new SimpleDoubleProperty(amount);
        lastPurchasedDate = new SimpleStringProperty(date);
        telephone = new SimpleStringProperty(tel);
    }

    public void setCustomerID(final int id) {
        customerID.set(id);
    }

    public void setCustomerName(final String name) {
        customerName.set(name);
    }

    public void setLocationOrCompanyName(final String locationOrCompany) {
        locationOrCompanyName.set(locationOrCompany);
    }

    public void setTotalAmountPurchased(final double amount) {
        totalAmountPurchased.set(amount);
    }

    public void setLastPurchasedDate(final String date) {
        lastPurchasedDate.set(date);
    }

    public void setTelephone(final String tel) {
        telephone.set(tel);
    }

    public int getCustomerID() { return customerID.get(); }

    public String getCustomerName() { return customerName.get(); }

    public String getLocationOrCompanyName() { return locationOrCompanyName.get(); }

    public double getTotalAmountPurchased() { return totalAmountPurchased.get(); }

    public String getLastPurchasedDate() { return lastPurchasedDate.get(); }

    public String getTelephone() { return telephone.get(); }

    public IntegerProperty customerID() { return customerID; }

    public StringProperty customerName() { return customerName; }

    public StringProperty locationOrCompanyName() { return locationOrCompanyName; }

    public DoubleProperty totalAmountPurchased() { return totalAmountPurchased; }

    public StringProperty lastPurchasedDate() { return lastPurchasedDate; }

    public StringProperty telephone() { return telephone; }


}
