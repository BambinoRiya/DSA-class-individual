module com.propelquantum.stockmandesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;


    opens com.propelquantum.stockmandesktop to javafx.fxml;
    exports com.propelquantum.stockmandesktop;
}