module com.dam.gestionalmacendam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires com.google.gson;


    opens com.dam.gestionalmacendam to javafx.fxml;
    exports com.dam.gestionalmacendam;
    opens com.dam.gestionalmacendam.controllers to javafx.fxml;
    exports com.dam.gestionalmacendam.controllers;
    opens com.dam.gestionalmacendam.repositories.Articles to javafx.fxml;
    exports com.dam.gestionalmacendam.repositories.Articles;
    opens com.dam.gestionalmacendam.models to javafx.fxml;
    exports com.dam.gestionalmacendam.models;
    opens com.dam.gestionalmacendam.managers;
    exports com.dam.gestionalmacendam.managers to javafx.fxml;
}