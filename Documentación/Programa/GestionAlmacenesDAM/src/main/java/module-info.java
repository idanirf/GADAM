module com.dam.gestionalmacenesdam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.dam to javafx.fxml;
    exports com.dam;
}