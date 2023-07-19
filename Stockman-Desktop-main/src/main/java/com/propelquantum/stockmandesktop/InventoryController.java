package com.propelquantum.stockmandesktop;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    public FlowPane flowPane = new FlowPane();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flowPane.setHgap(40.0);
        flowPane.setVgap(40.0);

        if (ProductController.products.size() > Main2Controller.productsOnLaunch.size()) {
            for (var i = 0; i < ProductController.products.size(); i++) {
                ProductTile productTile = new ProductTile(ProductController.products.get(i).getProductName());

                productTile.setProductID(ProductController.products.get(i).getProductID());

                flowPane.getChildren().add(productTile.asPane());
            }
        } else {
            for (var i = 0; i < Main2Controller.productsOnLaunch.size(); i++) {
                ProductTile productTile = new ProductTile(Main2Controller.productsOnLaunch.get(i).getProductName());

                productTile.setProductID(Main2Controller.productsOnLaunch.get(i).getProductID());

                flowPane.getChildren().add(productTile.asPane());
            }
        }

    }

    public void onAddResupplyButtonClicked(ActionEvent actionEvent) {
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
    }

}
