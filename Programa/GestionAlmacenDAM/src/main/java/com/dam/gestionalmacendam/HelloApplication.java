package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.Printers.HtmlPrinterOrder;
import com.dam.gestionalmacendam.Printers.HtmlPrinterReception;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HelloApplication extends Application {
    public static void main(String[] args) {

        OrderRepository o = OrderRepository.getInstance(DataBaseManager.getInstance());
        try{
            Order order = o.findAll().get(0);
            System.out.println(order);
            HtmlPrinterOrder h = new HtmlPrinterOrder(order);

        }catch (Exception e) {
            e.printStackTrace();
        }




        //launch();
        // checkServer();
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
    public void start(Stage stage) throws IOException {

        SceneManager.getInstance(HelloApplication.class);
        SceneManager.get().initPantallaHello();

    }

}