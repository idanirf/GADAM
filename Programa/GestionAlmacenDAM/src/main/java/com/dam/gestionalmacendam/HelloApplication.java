package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
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
       checkServer();
       probarrepositorioAza();
    }

    private static void probarrepositorioAza() {
        probarOrder();
        probarArticle();



    }

    private static void probarArticle() {
        System.out.println("Probamos el repositorio de aza");
        ArticleRepository o = ArticleRepository.getInstance(DataBaseManager.getInstance());
        System.out.println("creando Article");
        Article o1 = new Article("Article 1","descripcion1", "arriva", 8D,8, true);


        try{
            System.out.println("salvando Article");
            Optional<Article> o2 = o.save(o1);
            System.out.println(o2);

            System.out.println("encontrando una por name");
            Optional<Article> o4 = o.findByName(o2.get().getArticle().toString());
            System.out.println(o4);

            System.out.println("encontrando una por uuid");
            Optional<Article> o3 = o.findByUuid(o2.get().getPIC().toString());
            System.out.println(o3);



            System.out.println("encontrando todas Article");
            ObservableList lista = o.findAll();
            System.out.println(lista);






            System.out.println("todas las pruevas realizadas");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se ha podido hacer algo");
        }
    }

    private static void probarOrder() {
        System.out.println("Probamos el repositorio de aza");
        OrderRepository o = OrderRepository.getInstance(DataBaseManager.getInstance());
        System.out.println("creando order");
        Order o1 = new Order("customer1", 50.40D, Pay.PAYPAL);


        try{
            System.out.println("salvando order");
            Optional<Order> o2 = o.save(o1);
            System.out.println(o2);

            System.out.println("encontrando todas order");
            ObservableList lista = o.findAll();
            System.out.println(lista);

            System.out.println("encontrando una por uuid");
            Order o3 = o.findByUUID(o2.get().getOIC());
            System.out.println(o3);




            System.out.println("todas las pruevas realizadas");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se ha podido hacer algo");
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
               // System.exit(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }

    }

}