package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.controllers.lineaPedidoManagerController;
import com.dam.gestionalmacendam.controllers.pedidoManagerViewController;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Views;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;


public class SceneManager {
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

    public void changeScene(Node node, Views view) throws IOException {
        //logger.info("Loading scene " + view.get());
        Stage stage = (Stage) node.getScene().getWindow();
        //oldStage.hide(); // Oculto la anterior
        Parent root = FXMLLoader.load(Objects.requireNonNull(appClass.getResource(view.get())));
        Scene newScene = new Scene(root, 1280, 720);
       // logger.info("Scene " + view.get() + " loaded");
        stage.setScene(newScene);
        stage.show();
    }

    public void initManagerPedidoView() throws IOException {
        //logger.info("Iniciando Main");
        Platform.setImplicitExit(true);
        //logger.info("Loading scene " + Views.MAIN.get());
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.OrderManager.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        stage.setResizable(true);
        //por el momento no hay iconos
        //stage.getIcons().add(new Image(Resources.get(AgendaApplication.class, Properties.APP_ICON)));
        //stage.setTitle("Pedido");
        //stage.initStyle(StageStyle.DECORATED);
        //logger.info("Scene Main loaded");
        // Por si salimos
        //stage.setOnCloseRequest(event -> {
        //    fxmlLoader.<pedidoManagerViewController>getController().onSalirAction();
        //});
        stage.setScene(scene);
        mainStage = stage;
        stage.show();
    }

    public void initConsultarUno(Order order) throws IOException {
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(Views.OrderDescription.get())));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage); // -importante con windows modal
        stage.setTitle("Order details");
        stage.setResizable(false);
        // Le hacemos los setters a los elementos del controlador
        lineaPedidoManagerController controller = fxmlLoader.getController();
        controller.setOederData(order);
        stage.setScene(scene);
        //logger.info("Scene Estadisticas loaded");
        stage.showAndWait();
    }
}
