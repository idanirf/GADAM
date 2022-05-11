package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.customer.CutomerRepository;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        CutomerRepository repo = CutomerRepository.getInstance();
        Customer custo = new Customer(UUID.randomUUID().toString(), "pepe","ramos", "1","avaux","3332211",
                "ramos@gmail.com", "x", LocalDateTime.now());

       try {
           repo.save(custo);
       }catch(SQLException e){
           e.printStackTrace();
       }
        //launch();
       //checkServer();
       probarrepositorioAza();
    }

    private static void probarrepositorioAza() {
        probarOrder();



    }

    private static void probarOrder() {
        System.out.println("Probamos el repositorio de aza");
        OrderRepository o = OrderRepository.getInstance(DataBaseManager.getInstance());
        System.out.println("creando order");
        Optional<Order> o1 = Optional.of(new Order(new SimpleStringProperty("customer1"),
                new SimpleDoubleProperty(50.40D)
                ,new SimpleObjectProperty<Pay>( Pay.PAYPAL)));
        System.out.println(o1);

        System.out.println("salvando order");
        try{
            o.save(o1.get());
            System.out.println("salbado");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no salbado");
        }

        System.out.println("find all order");
        try{
            ObservableList<Order> listao = o.findAll();
            System.out.println("encontrados");
        }catch (SQLException e) {
            System.out.println("no encontrados");
        }
        o1.get().setMethodPay(new SimpleObjectProperty<Pay>(Pay.CARD));

        System.out.println("update order");
        try{
            o.update(o1,o1.get().getOIC());
            System.out.println("update realizado order");
            System.out.println(o1);
        }catch (SQLException e) {
            System.out.println("no update realizado");
        }

        System.out.println("find by uuid");
        try{
            Optional<Order> o2 = o.shearchByUuid(o1.get().getOIC());
            System.out.println("find realizado order");
            System.out.println(o2);
        }catch (SQLException e) {
            System.out.println("no find realizado");
        }



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

}