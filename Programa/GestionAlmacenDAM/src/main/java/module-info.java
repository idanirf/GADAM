module com.dam.gestionalmacendam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens com.dam.gestionalmacendam to javafx.fxml;
    exports com.dam.gestionalmacendam;
}