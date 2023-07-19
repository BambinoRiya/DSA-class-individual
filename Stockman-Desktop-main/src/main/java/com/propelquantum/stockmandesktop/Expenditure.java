package com.propelquantum.stockmandesktop;

import javafx.beans.property.*;

public class Expenditure {
    private final IntegerProperty expenditureID;
    private final StringProperty expenses;
    private final StringProperty description;
    private final DoubleProperty amount;
    private final StringProperty dateOfExpenditure;
    private final IntegerProperty customerID;

    Expenditure() {
        expenditureID = new SimpleIntegerProperty();
        expenses = new SimpleStringProperty();
        description = new SimpleStringProperty();
        amount = new SimpleDoubleProperty();
        dateOfExpenditure = new SimpleStringProperty();
        customerID = new SimpleIntegerProperty();
    }

    Expenditure(int id, String expense, String describe, double howMuch, String date, int customer) {
        expenditureID = new SimpleIntegerProperty(id);
        expenses = new SimpleStringProperty(expense);
        description = new SimpleStringProperty(describe);
        amount = new SimpleDoubleProperty(howMuch);
        dateOfExpenditure = new SimpleStringProperty(date);
        customerID = new SimpleIntegerProperty(customer);
    }

    Expenditure(String expense, String describe, double howMuch, String date) {
        expenditureID = new SimpleIntegerProperty();
        expenses = new SimpleStringProperty(expense);
        description = new SimpleStringProperty(describe);
        amount = new SimpleDoubleProperty(howMuch);
        dateOfExpenditure = new SimpleStringProperty(date);
        customerID = new SimpleIntegerProperty();
    }

    public void setExpenditureID(final int id) { expenditureID.set(id); }

    public void setExpenses(final String expense) { expenses.set(expense); }

    public void setDescription(final String describe) { description.set(describe); }

    public void setAmount(final double howMuch) { amount.set(howMuch); }

    public void setDateOfExpenditure(final String date) { dateOfExpenditure.set(date); }

    public void setCustomerID(final int customer) {
        customerID.set(customer);
    }

    public int getExpenditureID() { return expenditureID.get(); }

    public String getExpenses() { return expenses.get(); }

    public String getDescription() { return description.get(); }

    public double getAmount() { return amount.get(); }

    public String getDateOfExpenditure() { return dateOfExpenditure.get(); }

    public int getCustomerID() {
        return customerID.get();
    }

    public IntegerProperty expenditureID() { return expenditureID; }

    public StringProperty expenses() { return expenses; }

    public StringProperty description() { return description; }

    public DoubleProperty amount() { return amount; }

    public StringProperty dateOfExpenditure() { return dateOfExpenditure; }

    public IntegerProperty customerID() {
        return customerID;
    }
}
