package com.propelquantum.stockmandesktop;

import javafx.beans.property.*;

public class Product {
    private final IntegerProperty productID;
    private final StringProperty productName;
    private final StringProperty productDescription;
    private final DoubleProperty pricePerUnit;
    private final IntegerProperty quantityInStock;
    private final DoubleProperty totalAmount;

    Product() {
        productID = new SimpleIntegerProperty();
        productName = new SimpleStringProperty();
        productDescription = new SimpleStringProperty();
        pricePerUnit = new SimpleDoubleProperty();
        quantityInStock = new SimpleIntegerProperty();
        totalAmount = new SimpleDoubleProperty();
    }

    Product(int id, String name, String describe, double price, int quantity, double amount) {
        productID = new SimpleIntegerProperty(id);
        productName = new SimpleStringProperty(name);
        productDescription = new SimpleStringProperty(describe);
        pricePerUnit = new SimpleDoubleProperty(price);
        quantityInStock = new SimpleIntegerProperty(quantity);
        totalAmount = new SimpleDoubleProperty(amount);
    }

    Product(String name, String describe, double price, int quantity) {
        productID = new SimpleIntegerProperty();
        productName = new SimpleStringProperty(name);
        productDescription = new SimpleStringProperty(describe);
        pricePerUnit = new SimpleDoubleProperty(price);
        quantityInStock = new SimpleIntegerProperty(quantity);

        totalAmount = new SimpleDoubleProperty(pricePerUnit.get() * quantityInStock.get());
    }

    public void setProductID(final int id) {
        productID.set(id);
    }

    public void setProductName(final String name) {
        productName.set(name);
    }

    public void setProductDescription(final String describe) {
        productDescription.set(describe);
    }

    public void setPricePerUnit(final double price) {
        pricePerUnit.set(price);
    }

    public void setQuantityInStock(final int quantity) {
        quantityInStock.set(quantity);
    }

    public void setTotalAmount() {
        totalAmount.set(getTotalAmount());
    }

    public int getProductID() {
        return productID.get();
    }

    public String getProductName() {
        return productName.get();
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public double getPricePerUnit() {
        return pricePerUnit.get();
    }

    public int getQuantityInStock() {
        return quantityInStock.get();
    }

    public double getTotalAmount() {
        double amount = getPricePerUnit() * getQuantityInStock();
        totalAmount.set(amount);

        return amount;
    }

    public IntegerProperty productID() {
        return productID;
    }

    public StringProperty productName() {
        return productName;
    }

    public StringProperty productDescription() {
        return productDescription;
    }

    public DoubleProperty pricePerUnit() {
        return pricePerUnit;
    }

    public IntegerProperty quantityInStock() {
        return quantityInStock;
    }

    public DoubleProperty totalAmount() {
        return totalAmount;
    }
}
