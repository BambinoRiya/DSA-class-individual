package com.propelquantum.stockmandesktop;

import javafx.beans.property.*;

public class Supplier {
    private final IntegerProperty supplierID;
    private final StringProperty supplierName;
    private final StringProperty supplierLocation;
    private final StringProperty productsSupplied;
    private final DoubleProperty pricePerUnit;
    private final StringProperty telephone;

    Supplier() {
        supplierID = new SimpleIntegerProperty();
        supplierName = new SimpleStringProperty();
        supplierLocation = new SimpleStringProperty();
        productsSupplied = new SimpleStringProperty();
        pricePerUnit = new SimpleDoubleProperty();
        telephone = new SimpleStringProperty();
    }

    Supplier(int id, String name, String location, String product, double price, String tel) {
        supplierID = new SimpleIntegerProperty(id);
        supplierName = new SimpleStringProperty(name);
        supplierLocation = new SimpleStringProperty(location);
        productsSupplied = new SimpleStringProperty(product);
        pricePerUnit = new SimpleDoubleProperty(price);
        telephone = new SimpleStringProperty(tel);
    }

    Supplier(String name, String location, String product, double price, String tel) {
        supplierID = new SimpleIntegerProperty();
        supplierName = new SimpleStringProperty(name);
        supplierLocation = new SimpleStringProperty(location);
        productsSupplied = new SimpleStringProperty(product);
        pricePerUnit = new SimpleDoubleProperty(price);
        telephone = new SimpleStringProperty(tel);
    }

    public void setSupplierID(final int id) {
        supplierID.set(id);
    }

    public void setSupplierName(final String name) {
        supplierName.set(name);
    }

    public void setSupplierLocation(final String location) {
        supplierLocation.set(location);
    }

    public void setProductsSupplied(final String product) {
        productsSupplied.set(product);
    }

    public void setPricePerUnit(final double price) {
        pricePerUnit.set(price);
    }

    public void setTelephone(final String tel) {
        telephone.set(tel);
    }

    public int getSupplierID() {
        return supplierID.get();
    }

    public String getSupplierName() {
        return supplierName.get();
    }

    public String getSupplierLocation() {
        return supplierLocation.get();
    }

    public String getProductsSupplied() {
        return productsSupplied.get();
    }

    public double getPricePerUnit() {
        return pricePerUnit.get();
    }

    public String getTelephone() {
        return telephone.get();
    }

    public IntegerProperty supplierID() {
        return supplierID;
    }

    public StringProperty supplierName() {
        return supplierName;
    }

    public StringProperty supplierLocation() {
        return supplierLocation;
    }

    public StringProperty productsSupplied() {
        return productsSupplied;
    }

    public DoubleProperty pricePerUnit() {
        return pricePerUnit;
    }

    public StringProperty telephone() {
        return telephone;
    }
}
