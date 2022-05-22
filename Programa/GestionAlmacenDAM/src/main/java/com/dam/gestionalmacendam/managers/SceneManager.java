package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.controllers.ProductoController;
import com.dam.gestionalmacendam.controllers.ResusmenController;
import com.dam.gestionalmacendam.models.Article;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;

    private static Stage mainStage;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;

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


    public static boolean initProducto(boolean editarModo, Article producto) throws IOException {
        System.out.println("Iniciando....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Vistas/nuevoProducto.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 561, 507);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setTitle(editarModo ? "Editar Persona" : "Nueva Persona");
        stage.setResizable(false);
        ProductoController controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
        controller.setEditarModo(editarModo);
        controller.setProducto(producto);
        stage.setScene(scene);
        stage.showAndWait();
        return controller.isAceptarClicked();
    }


    public static void initResume(Article producto) throws IOException {
        System.out.println("Iniciando....");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Vistas/verDetalle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.setScene(scene);
        ResusmenController controller = fxmlLoader.getController();
        System.out.println(producto);
        controller.setProducto(producto);
        stage.showAndWait();

    }

}
