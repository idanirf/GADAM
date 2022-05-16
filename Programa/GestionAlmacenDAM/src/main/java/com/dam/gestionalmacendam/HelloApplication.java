package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.*;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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

        //launch();
       checkServer();
       //probarrepositorioAza();
    }

    private static void probarrepositorioAza() {
        //probarReception();  ok
       // probarOrder();     ok
       // probarArticle(); ok
        //probarLineOrder();    ok
       // probarLineReception(); ok


    }

    private static void probarReception() {
        System.out.println("Probamos el repositorio de aza");
        ReceptionRepository o = ReceptionRepository.getInstance(DataBaseManager.getInstance());
        System.out.println("creando Reception");

        try{

            System.out.println("encontrando todas Reception");
            ObservableList lista = o.findAll();
            System.out.println(lista);

            System.out.println("salvando Reception");
            Optional<Reception> o1 = o.save( new Reception("Reception 1","Reception 1",1D));
            System.out.println(o1);




            System.out.println("todas las pruevas realizadas");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se ha podido hacer algo");
        }
    }

    private static void probarLineReception() {
        System.out.println("Probamos el repositorio de aza");
        LineReceptionRepository o = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
        System.out.println("creando LineOrder");
        LineReception o1 = new LineReception("LineOrderPrueva", 30, 30D, "no pertenece a nada" );

        try{
            System.out.println("salvando LineReception");
            Optional<LineReception> o2 = o.save(o1);
            System.out.println(o2);

            System.out.println("encontrando todas LInereception");
            ObservableList lista = o.findAll();
            System.out.println(lista);

            System.out.println("encontrando una por uuid  belongs ");
            ObservableList o3 = o.SerachByReceptionsBelong(o2.get().getBelongsRecepcion().toString());
            System.out.println(o3);

            System.out.println("update una LineReception");
            System.out.println("antigulo" + o1);
            o1.setLoad(123);
            o1.setBelongsRecepcion(new SimpleStringProperty("tutifruti"));
            Optional<Integer> i = o.update(o1.getRLIC().toString(), o1);
            System.out.println("cargado" + i);
            System.out.println("nuevo" + o.SerachByReceptionsBelong(o1.getBelongsRecepcion().toString()));








            System.out.println("todas las pruevas realizadas");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se ha podido hacer algo");
        }
    }

    private static void probarLineOrder() {
        System.out.println("Probamos el repositorio de aza");
        LineOrderRepository o = LineOrderRepository.getInstance(DataBaseManager.getInstance());
        System.out.println("creando LineOrder");
        LineOrder o1 = new LineOrder("LineOrderPrueva", 3, 30D, "no pertenece a nada" );

        try{
            System.out.println("salvando Lineorder");
            Optional<LineOrder> o2 = o.save(o1);
             System.out.println(o2);

            System.out.println("encontrando todas LIneorder");
            ObservableList lista = o.findAll();
            System.out.println(lista);

            System.out.println("encontrando una por uuid");
            Optional<LineOrder> o3 = o.findByUuid(o2.get().getOLIC().toString());
            System.out.println(o3);

            System.out.println("encontrando una por uuid  belongs ");
            ObservableList o4 = o.searchByUuidOrder(o2.get().getBelongsOrder().toString());
            System.out.println(o4);

            System.out.println("update una LineOrder");
            System.out.println("original");
            System.out.println(o1);
            o1.setLoad(121);
            Optional<Integer> i = o.update(o1.getOLIC().toString(), o1);
            System.out.println("cargado" + i );
            System.out.println(o.findByUuid(o1.getOLIC().toString()));




            System.out.println("todas las pruevas realizadas");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se ha podido hacer algo");
        }
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

            System.out.println("update una Article");
            System.out.println("antiguo");
            System.out.println(o1);
            o1.setArticle(new SimpleStringProperty("si"));
            Optional<Integer> i = o.update(o1.getPIC().toString(), o1);
            System.out.println(o.findByUuid(o1.getPIC().toString()));
            System.out.println("cargado" + o1);



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

            System.out.println("update order una por uuid");
            System.out.println("antiguo" +o3);
            o3.setPrice(new SimpleDoubleProperty(11.1D));
            o.update(o3.getOIC().toString(), o3);
            System.out.println(o.findByUUID(o3.getOIC().toString()));




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