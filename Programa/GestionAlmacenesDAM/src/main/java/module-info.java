module com.dam.gestionalmacendam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires static lombok;
    requires com.google.gson;

    opens com.dam.gestionalmacendam to javafx.fxml;
    exports com.dam.gestionalmacendam;
}