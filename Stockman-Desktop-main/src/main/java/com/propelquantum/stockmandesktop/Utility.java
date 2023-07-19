package com.propelquantum.stockmandesktop;

import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.sql.*;

public class Utility {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/stockman?useSSL=false";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "2024";

    public static final String SELECT_LOGIN_QUERY_FROM_STAFF = "SELECT * FROM stockman.user WHERE username = ? and password = ?";
    public static final String SEARCH_ALL_CUSTOMER_QUERY = "SELECT * FROM stockman.customer";
    public static final String SEARCH_ALL_SUPPLIER_QUERY = "SELECT * FROM stockman.supplier";
    public static final String SEARCH_ALL_PRODUCT_QUERY = "SELECT * FROM stockman.product";
    public static final String SEARCH_ALL_EXPENDITURE_QUERY = "SELECT * FROM stockman.expenditure";

    public static final String INSERT_CUSTOMER_UPDATE = "INSERT INTO stockman.customer(`fullName`, `locationOrCompany`, `totalAmountPurchased`, `lastPurchasedDate`, `telephone`) VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_PRODUCT_UPDATE = "INSERT INTO stockman.product(`productName`, `productDescription`, `pricePerUnit`, `quantityInStock`, `totalAmount`) VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_EXPENDITURE_UPDATE = "INSERT INTO stockman.expenditure(`expenses`, `description`, `amount`, `dateOfExpenditure`, `customerID`) VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_SUPPLIER_UPDATE = "INSERT INTO stockman.supplier(`supplierName`, `location`, `productsSupplied`, `pricePerUnit`, `telephone`) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_QUANTITY_IN_STOCK = "UPDATE stockman.product SET `quantityInStock` = ? WHERE (`productID` = ?)";
    public static final String UPDATE_PURCHASE_DATE_OF_CUSTOMER = "UPDATE stockman.customer SET `lastPurchasedDate` = ? WHERE (`customerID` = ?)";
    public static final String UPDATE_TOTAL_AMOUNT_OF_CUSTOMER = "UPDATE stockman.customer SET `totalAmountPurchased` = ? WHERE (`customerID` = ?)";

    public static User loggedInUser = new User();

    public static boolean validateLoginCredentials(final String username, final String password) {

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_QUERY_FROM_STAFF)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // debug
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loggedInUser.setUserID(resultSet.getInt("userID"));
                loggedInUser.setFullName(resultSet.getString("fullName"));
                loggedInUser.setUsername(resultSet.getString("username"));
                loggedInUser.setPassword(resultSet.getString("password"));

                // debug
                System.out.println(loggedInUser.getFullName() + " with ID " + loggedInUser.getUserID() + " has logged in");

                return true;
            }

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean insertCustomerIntoDatabase(final Customer customer) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_UPDATE)) {

            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getLocationOrCompanyName());
            preparedStatement.setDouble(3, customer.getTotalAmountPurchased());
            preparedStatement.setString(4, customer.getLastPurchasedDate());
            preparedStatement.setString(5, customer.getTelephone());

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean insertProductIntoDatabase(Product product) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_UPDATE)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getPricePerUnit());
            preparedStatement.setInt(4, product.getQuantityInStock());
            preparedStatement.setDouble(5, product.getTotalAmount());

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean insertExpenditureIntoDatabase(Expenditure expenditure) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXPENDITURE_UPDATE)) {

            preparedStatement.setString(1, expenditure.getExpenses());
            preparedStatement.setString(2, expenditure.getDescription());
            preparedStatement.setDouble(3, expenditure.getAmount());
            preparedStatement.setString(4, expenditure.getDateOfExpenditure());
            preparedStatement.setInt(5, expenditure.getCustomerID());

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean insertSupplierIntoDatabase(Supplier supplier) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUPPLIER_UPDATE)) {

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getSupplierLocation());
            preparedStatement.setString(3, supplier.getProductsSupplied());
            preparedStatement.setDouble(4, supplier.getPricePerUnit());
            preparedStatement.setString(5, supplier.getTelephone());

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean updateCustomerLastPurchaseDate(final String lastPurchasedDate, final int customerID) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PURCHASE_DATE_OF_CUSTOMER)) {

            preparedStatement.setString(1, lastPurchasedDate);
            preparedStatement.setInt(2, customerID);

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean updateCustomerTotalAmount(final int quantity, final int productID,final int customerID) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOTAL_AMOUNT_OF_CUSTOMER)) {

            double amount = getCustomerTotalAmountFromDatabase(customerID) + (quantity * getProductPriceFromDatabase(productID));

            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, customerID);

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static boolean updateQuantityInStock(int quantity) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY_IN_STOCK)) {

            int quantityToInsert = getQuantityInStockFromDatabase(ProductTile.id) + quantity;

            preparedStatement.setInt(1, quantityToInsert);
            preparedStatement.setInt(2, ProductTile.id);

            // debug
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            return true;

        } catch(SQLException e) {
            printSQLException(e);
        }

        return false;
    }

    public static double getCustomerTotalAmountFromDatabase(final int customerID) {
        double amount = 0.0;

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_CUSTOMER_QUERY)) {


            while (resultSet.next()) {

                if (customerID == resultSet.getInt("customerID")) {
                    amount = resultSet.getDouble("totalAmountPurchased");
                }
            }


        } catch(SQLException e) {
            printSQLException(e);
        }

        return amount;
    }

    public static int getQuantityInStockFromDatabase(final int productID) {
        int quantity = 0;

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_PRODUCT_QUERY)) {


            while (resultSet.next()) {

                if (productID == resultSet.getInt("productID")) {
                    quantity = resultSet.getInt("quantityInStock");
                }
            }


        } catch(SQLException e) {
            printSQLException(e);
        }

        return quantity;
    }

    public static double getProductPriceFromDatabase(final int productID) {
        double price = 0.0;

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Utility.SEARCH_ALL_PRODUCT_QUERY)) {


            while (resultSet.next()) {

                if (productID == resultSet.getInt("productID")) {
                    System.out.println("Utility check of product id: " + productID);

                    price = resultSet.getDouble("pricePerUnit");
                }
            }


        } catch(SQLException e) {
            printSQLException(e);
        }

        System.out.println("The price is " + price);

        return price;
    }

    public static void informationDisplay(final String message, final String header, final String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, final String title, final String message) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
