module com.dam.gestionalmacendam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires com.google.gson;


    opens com.dam.gestionalmacendam to javafx.fxml;
    exports com.dam.gestionalmacendam;
    exports com.dam.gestionalmacendam.controllers;
    opens com.dam.gestionalmacendam.controllers to javafx.fxml;

}