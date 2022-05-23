package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.HelloApplication;


import com.dam.gestionalmacendam.controllers.LineaPedidoControler;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Supplier;
import javafx.application.Platform;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;



public class SceneManager {
    //clase a la que llamas para iniciar una pantalla llamando a la el master y la pantalla que quieras


    private static SceneManager instance;
    private final Class<?> appClass;
   //System.Logger logger = LogManager.getLogger(SceneManager.class);

    private Stage mainStage;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
        //logger.info("SceneManager created");
        //System.out.println("SceneManager created");
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public static SceneManager get() {
        return instance;
    }



    public void initOrderView() throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pedido.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void initPantallaHello() throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void initLineOrderView(FilteredList<Order> lista) throws IOException {
        Order o = lista.get(0);
        System.out.println(o);
        LineaPedidoControler.createInstance(o);
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lineaPedidoManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void initModificarSuplier(Supplier employee) {
    }

    public void initSuplierView() throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SuplierVistaManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void initNewSuplier() throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("suplierNewView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
