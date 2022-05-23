package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;

import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class HelloApplication extends Application {
    public static void main(String[] args) {

//        checkServer();
          launch();
    }

    private static void checkServer() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseManager controller = DataBaseManager.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello world'");
            if (rs.get().next()) {
                controller.close();
                System.out.println("Conexión con la Base de Datos realizada con éxito");

            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("Vistas/receptionview.fxml")));
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("VISTA PRODUCTOS MANAGER-EMPLEADO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}