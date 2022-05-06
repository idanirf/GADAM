module com.dam.gestionalmacenesdam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.mybatis;
    requires java.sql;

    opens com.dam to javafx.fxml;
    exports com.dam;
}