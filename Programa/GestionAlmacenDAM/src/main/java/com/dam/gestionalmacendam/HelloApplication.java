package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.servicies.StorageCsvFile;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        ArticleRepository repository= ArticleRepository.getInstance(DataBaseManager.getInstance());

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
                System.exit(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/prueba.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 836, 625);
//        stage.setTitle("Login");
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
        SceneManager sceneManager = SceneManager.getInstance(HelloApplication.class);
        sceneManager.initSplash(stage);
    }

}