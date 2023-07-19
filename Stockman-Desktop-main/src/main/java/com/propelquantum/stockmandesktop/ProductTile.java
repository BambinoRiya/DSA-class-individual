package com.propelquantum.stockmandesktop;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductTile {
    private final Pane pane;
    private int productID = 0;

    public static int id = 0;

    ProductTile(String nameOfProduct) {
        Label productName = new Label(nameOfProduct);
        JFXButton purchaseButton = new JFXButton("Purchase");
        JFXButton resupplyButton = new JFXButton("Resupply");
        VBox vBox = new VBox();
        Pane pane = new Pane();

        pane.setMinSize(200, 20);

        productName.setMinSize(200, 40);
        productName.setStyle("-fx-background-color: #FFBE0B");
        productName.setPadding(new Insets(5, 10, 10, 5));
        productName.setTextFill(Color.BLACK);
        productName.setAlignment(Pos.CENTER);

        purchaseButton.setMinSize(200, 40);
        purchaseButton.setTextFill(Color.WHITE);
        purchaseButton.setStyle("-fx-background-color: #8338EC");

        resupplyButton.setMinSize(200, 40);
        resupplyButton.setTextFill(Color.WHITE);
        resupplyButton.setStyle("-fx-background-color: #FF006E");

        resupplyButton.setOnAction(e -> {
            ResupplyDialog.show();
            productID = this.getProductID();

            id = productID;

            System.out.println("id while resupplying is " + productID);
        });

        purchaseButton.setOnAction(e -> {
            PurchaseDialog.show();
            productID = this.getProductID();

            id = productID;

            System.out.println("id while purchasing is " + productID);
        });

        vBox.setMinSize(250, 200);
        vBox.setPadding(new Insets(30, 40, 40, 40));
        vBox.setStyle("-fx-background-color: #3A86FF");


        vBox.getChildren().addAll(productName, pane, resupplyButton, purchaseButton);

        this.pane = new Pane();
        this.pane.getChildren().add(vBox);
    }

    public Pane asPane() {
        return pane;
    }

    public void setProductID(final int id) {
        productID = id;
    }

    public int getProductID() {
        return productID;
    }

}
